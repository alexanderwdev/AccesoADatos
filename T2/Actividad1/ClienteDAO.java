package Actividad1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona las operaciones de acceso a la base de datos relacionadas con los clientes y sus pedidos.
 *
 * @author Alexander Wheatley
 */
public class ClienteDAO {

    /**
     * Actualiza el nombre de un cliente según su identificador.
     *
     * @param nuevoNombre nuevo nombre del cliente
     * @param id identificador del cliente
     * @return número de filas modificadas
     * @throws SQLException si ocurre un error al ejecutar la consulta
     */
    public int updateNombreCliente(String nuevoNombre, int id) throws SQLException {
        String sql = "UPDATE cliente SET nombre = ? WHERE id = ?";

        try (Connection conexion = Conexion.conectar(); PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, nuevoNombre);
            sentencia.setInt(2, id);

            return sentencia.executeUpdate();
        }
    }

    /**
     * Obtiene todos los pedidos realizados por un cliente.
     *
     * @param idCliente identificador del cliente
     * @return lista de pedidos del cliente
     * @throws SQLException si ocurre un error al consultar la base de datos
     */
    public List<Pedido> getPedidosByCliente(int idCliente) throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT id, fecha, total, cliente_id " + "FROM pedido WHERE cliente_id = ?";

        try (Connection conexion = Conexion.conectar(); PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idCliente);

            try (ResultSet resultado = sentencia.executeQuery()) {

                while (resultado.next()) {

                    Pedido pedido = new Pedido(
                            resultado.getInt("id"),
                            resultado.getDate("fecha").toLocalDate(),
                            resultado.getDouble("total"),
                            resultado.getInt("cliente_id")
                    );

                    pedidos.add(pedido);
                }
            }
        }

        return pedidos;
    }

    /**
     * Elimina un cliente y todos sus pedidos asociados.
     * Utiliza una transacción para asegurar que ambas operaciones se realicen correctamente.
     *
     * @param id identificador del cliente que se desea eliminar
     * @return número de clientes eliminados
     * @throws SQLException si ocurre un error durante la eliminación
     */
    public int delete(int id) throws SQLException {
        String sqlPedidos = "DELETE FROM pedido WHERE cliente_id = ?";
        String sqlCliente = "DELETE FROM cliente WHERE id = ?";

        try (Connection conexion = Conexion.conectar()) {

            conexion.setAutoCommit(false);

            try {
                try (PreparedStatement sentencia = conexion.prepareStatement(sqlPedidos)) {
                    sentencia.setInt(1, id);
                    sentencia.executeUpdate();
                }

                int eliminados;

                try (PreparedStatement sentencia = conexion.prepareStatement(sqlCliente)) {
                    sentencia.setInt(1, id);
                    eliminados = sentencia.executeUpdate();
                }

                conexion.commit();

                return eliminados;

            } catch (SQLException e) {

                conexion.rollback();
                throw e;
            }
        }
    }

    /**
     * Calcula el número de pedidos y el gasto total de cada cliente
     * dentro de un intervalo de fechas.
     *
     * @param fechaInicio fecha inicial del intervalo
     * @param fechaFin fecha final del intervalo
     * @return lista de resúmenes de clientes ordenados por gasto total descendente
     * @throws SQLException si ocurre un error al ejecutar la consulta
     */
    public List<ResumenCliente> getTotalPedidosPorCliente(LocalDate fechaInicio, LocalDate fechaFin) throws SQLException {
        List<ResumenCliente> resumenes = new ArrayList<>();

        String sql = "SELECT c.nombre, " +
                "COUNT(p.id) AS numero_pedidos, " +
                "SUM(p.total) AS total_gastado " +
                "FROM cliente c " +
                "INNER JOIN pedido p ON c.id = p.cliente_id " +
                "WHERE p.fecha BETWEEN ? AND ? " +
                "GROUP BY c.id, c.nombre " +
                "ORDER BY total_gastado DESC";

        try (Connection conexion = Conexion.conectar(); PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setDate(1, java.sql.Date.valueOf(fechaInicio));

            sentencia.setDate(2, java.sql.Date.valueOf(fechaFin));

            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {

                    ResumenCliente resumen = new ResumenCliente(
                            resultado.getString("nombre"),
                            resultado.getInt("numero_pedidos"),
                            resultado.getDouble("total_gastado")
                    );

                    resumenes.add(resumen);
                }
            }
        }

        return resumenes;
    }
}