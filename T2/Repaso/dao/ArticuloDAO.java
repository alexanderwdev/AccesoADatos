package Repaso.dao;

import Repaso.cfg.ConexionBD;
import Repaso.model.Articulo;
import Repaso.model.ArticuloCatalogo;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDAO {
    public int insertarArticulo(Articulo a) {
        if (a.getNombre() == null || a.getNombre().isBlank()
                || a.getPrecio() < 0 || a.getStock() < 0) {
            System.out.println("Datos del artículo no válidos");
            return 0;
        }

        String sql = "INSERT INTO articulo "
                + "(nombre,precio,stock,categoria_id) "
                + "VALUES(?,?,?,?)";

        try (Connection c = ConexionBD.getConexion(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, a.getNombre());
            ps.setDouble(2, a.getPrecio());
            ps.setInt(3, a.getStock());
            ps.setInt(4, a.getCategoria_id());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("No se ha generado un ID");
                }
            }
        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return 0;
    }

    public List<ArticuloCatalogo> buscarArticulos(String texto, double precioMaximo) {
        List<ArticuloCatalogo> articulos = new ArrayList<>();

        String sql = "SELECT a.id, a.nombre, c.nombre AS categoria, " +
                "a.precio, a.stock " +
                "FROM articulo a " +
                "JOIN categoria c ON a.categoria_id = c.id " +
                "WHERE a.nombre LIKE ? AND a.precio <= ? " +
                "ORDER BY a.precio ASC";

        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + texto + "%");
            ps.setDouble(2, precioMaximo);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    ArticuloCatalogo a = new ArticuloCatalogo(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("categoria"),
                            rs.getDouble("precio"),
                            rs.getInt("stock")
                    );

                    articulos.add(a);
                }
            }

        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return articulos;
    }

    public String eliminarArticulo(int id) {
        String comprobar = "SELECT id FROM articulo WHERE id = ?";
        String comprobarVentas = "SELECT COUNT(*) FROM venta WHERE articulo_id = ?";
        String eliminar = "DELETE FROM articulo WHERE id = ?";

        try (Connection c = ConexionBD.getConexion()) {

            // Comprobar si existe
            try (PreparedStatement ps = c.prepareStatement(comprobar)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        return "El artículo no existe";
                    }
                }
            }

            // Comprobar si tiene ventas
            try (PreparedStatement ps = c.prepareStatement(comprobarVentas)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();

                    if (rs.getInt(1) > 0) {
                        return "No se puede eliminar: tiene ventas asociadas";
                    }
                }
            }

            // Eliminar
            try (PreparedStatement ps = c.prepareStatement(eliminar)) {
                ps.setInt(1, id);

                if (ps.executeUpdate() > 0) {
                    return "Artículo eliminado correctamente";
                }
            }

        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return "No se ha podido eliminar el artículo";
    }

    public List<Articulo> obtenerArticulosCategoria(int idCategoria) {
        List<Articulo> articulos = new ArrayList<>();

        String sql = "{CALL obtenerArticulosCategoria(?)}";

        try (Connection c = ConexionBD.getConexion();
             CallableStatement cs = c.prepareCall(sql)) {

            cs.setInt(1, idCategoria);

            try (ResultSet rs = cs.executeQuery()) {

                while (rs.next()) {
                    Articulo a = new Articulo(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getInt("stock"),
                            idCategoria
                    );

                    articulos.add(a);
                }
            }

            if (articulos.isEmpty()) {
                System.out.println("No hay artículos disponibles en esta categoría.");
            }

        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return articulos;
    }

    public void obtenerResumenCategoria(int idCategoria) {

        String sql = "{CALL obtenerResumenCategoria(?, ?, ?)}";

        try (Connection c = ConexionBD.getConexion();
             CallableStatement cs = c.prepareCall(sql)) {

            // Parámetro de entrada
            cs.setInt(1, idCategoria);

            // Registrar parámetros de salida
            cs.registerOutParameter(2, Types.INTEGER);
            cs.registerOutParameter(3, Types.DECIMAL);

            // Ejecutar procedimiento
            cs.execute();

            // Recuperar resultados
            int numeroArticulos = cs.getInt(2);
            double valorTotal = cs.getDouble(3);

            // Mostrar resultados
            System.out.println("Número de artículos: " + numeroArticulos);
            System.out.println("Valor total del stock: " + valorTotal);

        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public double calcularValorExistencias(int idArticulo) {

        String sql = "{? = CALL calcularValorExistencias(?)}";

        try (Connection c = ConexionBD.getConexion();
             CallableStatement cs = c.prepareCall(sql)) {

            // Registrar el valor de retorno
            cs.registerOutParameter(1, Types.DECIMAL);

            // Enviar el parámetro de entrada
            cs.setInt(2, idArticulo);

            // Ejecutar la función
            cs.execute();

            // Recuperar el valor devuelto
            double valor = cs.getDouble(1);

            System.out.println("Valor económico de las existencias: " + valor);

            return valor;

        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return 0;
    }

    public boolean insertarArticulosLote(List<Articulo> articulos) {

        String sql = "INSERT INTO articulo (nombre, precio, stock, categoria_id) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection c = ConexionBD.getConexion()) {

            c.setAutoCommit(false);

            try {
                // Comprobar todos los artículos antes de insertarlos
                for (Articulo a : articulos) {

                    if (a.getNombre() == null || a.getNombre().isBlank()) {
                        throw new SQLException("El nombre no puede estar vacío");
                    }

                    if (a.getPrecio() < 0) {
                        throw new SQLException("El precio no puede ser negativo");
                    }

                    if (a.getStock() < 0) {
                        throw new SQLException("El stock no puede ser negativo");
                    }

                    // Comprobar que la categoría existe
                    String sqlCategoria = "SELECT id FROM categoria WHERE id = ?";

                    try (PreparedStatement psCategoria = c.prepareStatement(sqlCategoria)) {
                        psCategoria.setInt(1, a.getCategoria_id());

                        try (ResultSet rs = psCategoria.executeQuery()) {
                            if (!rs.next()) {
                                throw new SQLException("La categoría "
                                        + a.getCategoria_id() + " no existe");
                            }
                        }
                    }
                }

                // Preparar el lote de inserciones
                try (PreparedStatement ps = c.prepareStatement(sql)) {

                    for (Articulo a : articulos) {
                        ps.setString(1, a.getNombre());
                        ps.setDouble(2, a.getPrecio());
                        ps.setInt(3, a.getStock());
                        ps.setInt(4, a.getCategoria_id());

                        ps.addBatch();
                    }

                    // Ejecutar todas las inserciones
                    ps.executeBatch();
                }

                // Confirmar la transacción
                c.commit();

                System.out.println("Todos los artículos se han insertado correctamente.");
                return true;

            } catch (SQLException e) {

                // Deshacer todas las inserciones
                c.rollback();

                System.out.println("Carga cancelada: " + e.getMessage());
                return false;
            }

        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
