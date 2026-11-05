package Actividad1;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase principal que ejecuta las operaciones de gestión de clientes y pedidos.
 *
 * @author Alexander Wheatley
 */
public class App {

    /**
     * Ejecuta las distintas operaciones sobre la base de datos:
     * actualizar clientes, consultar pedidos, obtener resúmenes y eliminar clientes.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {
        ClienteDAO dao = new ClienteDAO();

        try {
            // APARTADO A

            System.out.println("===== APARTADO A =====");

            int modificados = dao.updateNombreCliente("Juan Actualizado", 1);

            System.out.println("Clientes modificados: " + modificados);

            // APARTADO B

            System.out.println("\n===== APARTADO B =====");

            List<Pedido> pedidos = dao.getPedidosByCliente(1);

            for (Pedido pedido : pedidos) System.out.println(pedido);

            // APARTADO D

            System.out.println("\n===== APARTADO D =====");

            List<ResumenCliente> resumenes = dao.getTotalPedidosPorCliente(
                    LocalDate.of(2026, 1, 1),
                    LocalDate.of(2026, 2, 28)
            );

            for (ResumenCliente resumen : resumenes) System.out.println(resumen);

            // APARTADO C

            System.out.println("\n===== APARTADO C =====");

            int eliminados = dao.delete(3);

            System.out.println("Clientes eliminados: " + eliminados);

        } catch (SQLException e) {
            System.out.println("Error en la base de datos:");
            System.out.println(e.getMessage());
        }
    }
}