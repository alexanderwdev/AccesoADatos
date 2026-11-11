package Actividad3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejecuta las operaciones de inserción, actualización y consulta
 * de clientes y pedidos en la base de datos.
 *
 * @author Alexander Wheatley
 */
public class Main {

    /**
     * Método principal que comprueba la conexión, registra un cliente
     * con sus pedidos, actualiza sus datos y muestra los resultados.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        try {

            // Comprobamos la conexión.
            try (Connection conexion = ConexionBD.getConexion()) {
                System.out.println("Conexión con MySQL correcta.");
            }

            // APARTADO A: Insertar cliente y pedidos.
            Cliente cliente = new Cliente("Carlos", "carlos@gmail.com");

            List<Pedido> pedidos = new ArrayList<>();

            pedidos.add(new Pedido(LocalDate.now(), 100.0));
            pedidos.add(new Pedido(LocalDate.now(), 50.0));
            pedidos.add(new Pedido(LocalDate.now(), 200.0));

            Transacciones.insertClientAndOrders(cliente, pedidos);

            System.out.println("ID del cliente creado: " + cliente.getId());

            // APARTADO B: Actualizar email e incrementar pedidos.
            Transacciones.updateEmailAndIncrementTotal(cliente.getId(), "carlos.nuevo@gmail.com");

            // Consultamos los resultados.
            mostrarDatos();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Consulta los clientes y sus pedidos en la base de datos
     * y muestra sus datos por pantalla.
     *
     * @throws SQLException si ocurre un error al realizar la consulta
     */
    public static void mostrarDatos() throws SQLException {

        String sql = """
                SELECT c.id, c.nombre, c.email,
                       p.id AS pedido_id, p.fecha, p.total
                FROM cliente c
                LEFT JOIN pedido p ON c.id = p.cliente_id
                ORDER BY c.id, p.id
                """;

        try (Connection conexion = ConexionBD.getConexion(); Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n--- RESULTADO DE LA BASE DE DATOS ---");

            while (rs.next()) {

                System.out.println("Cliente: " + rs.getString("nombre"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Pedido: " + rs.getInt("pedido_id"));
                System.out.println("Fecha: " + rs.getDate("fecha"));
                System.out.println("Total: " + rs.getDouble("total"));
                System.out.println("--------------------------");
            }
        }
    }
}