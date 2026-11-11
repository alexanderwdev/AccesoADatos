package Actividad3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Gestiona las transacciones de inserción y actualización de clientes y pedidos.
 *
 * @author Alexander Wheatley
 */
public class Transacciones {

    // APARTADO A:
    // Insertar un cliente y todos sus pedidos.

    /**
     * Inserta un cliente y todos sus pedidos en una única transacción.
     * Si ocurre un error, se deshacen todas las operaciones.
     *
     * @param cliente cliente que se quiere insertar
     * @param orders  lista de pedidos asociados al cliente
     * @throws SQLException si ocurre un error durante la transacción
     */
    public static void insertClientAndOrders(Cliente cliente, List<Pedido> orders) throws SQLException {

        String sqlCliente = "INSERT INTO cliente (nombre, email) VALUES (?, ?)";

        String sqlPedido = "INSERT INTO pedido (fecha, total, cliente_id) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.getConexion()) {

            // Desactivamos el autocommit.
            conexion.setAutoCommit(false);

            try {
                // Insertamos el cliente.
                int idCliente;

                try (PreparedStatement ps = conexion.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS)) {

                    ps.setString(1, cliente.getNombre());
                    ps.setString(2, cliente.getEmail());

                    ps.executeUpdate();

                    // Recuperamos el ID generado.
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (!rs.next()) {
                            throw new SQLException("No se ha podido obtener el ID del cliente");
                        }

                        idCliente = rs.getInt(1);
                    }
                }

                // Insertamos todos los pedidos.
                try (PreparedStatement ps = conexion.prepareStatement(sqlPedido)) {

                    for (Pedido pedido : orders) {

                        ps.setDate(1, java.sql.Date.valueOf(pedido.getFecha()));

                        ps.setDouble(2, pedido.getTotal());
                        ps.setInt(3, idCliente);

                        ps.executeUpdate();
                    }
                }

                // Confirmamos todas las operaciones.
                conexion.commit();

                cliente.setId(idCliente);

                System.out.println("Cliente y pedidos insertados correctamente.");

            } catch (SQLException e) {

                // Si algo falla, deshacemos todas las operaciones.
                conexion.rollback();

                System.out.println("Error. Se han deshecho los cambios.");

                throw e;
            }
        }
    }

    // APARTADO B:
    // Actualizar el email del cliente y aumentar sus pedidos un 10%.

    /**
     * Actualiza el email de un cliente e incrementa un 10 % el total
     * de todos sus pedidos dentro de una única transacción.
     * Si ocurre un error, se deshacen los cambios.
     *
     * @param idCliente identificador del cliente
     * @param newEmail  nuevo email del cliente
     * @throws SQLException si ocurre un error durante la transacción
     */
    public static void updateEmailAndIncrementTotal(int idCliente, String newEmail) throws SQLException {

        String sqlCliente = "UPDATE cliente SET email = ? WHERE id = ?";

        String sqlPedidos = "UPDATE pedido SET total = total * 1.10 WHERE cliente_id = ?";

        try (Connection conexion = ConexionBD.getConexion()) {

            // Desactivamos el autocommit.
            conexion.setAutoCommit(false);

            try {

                // Actualizamos el email.
                try (PreparedStatement ps = conexion.prepareStatement(sqlCliente)) {

                    ps.setString(1, newEmail);
                    ps.setInt(2, idCliente);

                    int filas = ps.executeUpdate();

                    if (filas != 1) {
                        throw new SQLException("El cliente no existe.");
                    }
                }

                // Incrementamos el total de sus pedidos un 10%.
                try (PreparedStatement ps = conexion.prepareStatement(sqlPedidos)) {

                    ps.setInt(1, idCliente);

                    ps.executeUpdate();
                }

                // Confirmamos los cambios.
                conexion.commit();

                System.out.println("Cliente y pedidos actualizados correctamente.");

            } catch (SQLException e) {

                // Si algo falla, deshacemos los cambios.
                conexion.rollback();

                System.out.println("Error. Se han deshecho los cambios.");

                throw e;
            }
        }
    }
}