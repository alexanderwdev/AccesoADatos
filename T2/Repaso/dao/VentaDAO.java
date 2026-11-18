package Repaso.dao;

import Repaso.cfg.ConexionBD;
import Repaso.model.ResumenVenta;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {
    public void insertarVenta(int idArticulo, int unidades) {
        String sqlConsulta = "SELECT precio, stock FROM articulo " + "WHERE id = ? FOR UPDATE";

        String sqlVenta = "INSERT INTO venta "
                + "(unidades, precio_unitario, articulo_id) "
                + "VALUES (?, ?, ?)";

        String sqlStock = "UPDATE articulo "
                + "SET stock = stock - ? WHERE id = ?";

        try (Connection c = ConexionBD.getConexion()) {
            c.setAutoCommit(false);

            double precioUnitario;
            int stockDisponible;

            try {
                try (PreparedStatement ps = c.prepareStatement(sqlConsulta)) {
                    ps.setInt(1, idArticulo);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            throw new SQLException("El artículo no existe");
                        }
                        precioUnitario=rs.getDouble("precio");
                        stockDisponible=rs.getInt("stock");
                    }

                    if (unidades <= 0) { throw new SQLException("Las unidades deben ser positivas."); }
                    if (stockDisponible < unidades) { throw new SQLException("No hay stock suficiente."); }
                }

                try (PreparedStatement ps = c.prepareStatement(sqlVenta)) {
                    ps.setInt(1, unidades);
                    ps.setDouble(2, precioUnitario);
                    ps.setInt(3, idArticulo);
                    ps.executeUpdate();
                }

                try (PreparedStatement ps = c.prepareStatement(sqlStock)) {
                    ps.setInt(1, unidades);
                    ps.setInt(2, idArticulo);

                    int filas = ps.executeUpdate();

                    if (filas != 1) {
                        throw new SQLException("No se ha podido actualizar el stock");
                    }
                }

                c.commit();

                System.out.println("Venta realizada correctamente.");
            } catch (SQLException e) {
                c.rollback();
                System.out.println("Venta cancelada: " + e.getMessage());
            }
        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<ResumenVenta> obtenerResumenVentas() {
        List<ResumenVenta> resumen = new ArrayList<>();

        String sql = "SELECT c.nombre AS categoria, "
                + "SUM(v.unidades) AS total_unidades, "
                + "SUM(v.unidades * v.precio_unitario) AS importe_total "
                + "FROM categoria c "
                + "INNER JOIN articulo a ON c.id = a.categoria_id "
                + "INNER JOIN venta v ON a.id = v.articulo_id "
                + "GROUP BY c.id, c.nombre "
                + "ORDER BY c.nombre ASC";

        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ResumenVenta r = new ResumenVenta(
                        rs.getString("categoria"),
                        rs.getInt("total_unidades"),
                        rs.getDouble("importe_total")
                );

                resumen.add(r);
            }

        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return resumen;
    }
}
