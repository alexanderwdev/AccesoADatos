package Actividades1_4_1_5.Ejercicio2;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Programa para gestionar pedidos mediante propiedades y serialización.
 *
 * @author Alexander Wheatley
 */
public class Main {

    private static final Path ARCHIVO_PROPIEDADES =
            Path.of("T1/Actividades1_4_1_5/Ejercicio2/app.properties");

    public static void main(String[] args) {
        Properties p = cargarPropiedades();

        if (p.isEmpty()) {
            System.out.println("No se han podido cargar las propiedades.");
            return;
        }

        System.out.println("\n=== " + p.getProperty("app.name", "Gestor de pedidos") + " ===\n");

        ArrayList<Order> pedidos = new ArrayList<>();

        pedidos.add(new Order(1, "Carlos Tormo", 75, List.of("Teclado", "Ratón"), "ABC1"));
        pedidos.add(new Order(2, "Hector Mohammed", 15, List.of("Cable USB"), "ABC2"));
        pedidos.add(new Order(3, "Gil Sergio", 180, List.of("Monitor", "Soporte"), "ABC3"));
        pedidos.add(new Order(4, "Alejandro Tormo", 55, List.of("Auriculares"), "ABC4"));

        System.out.println("--- Pedidos antes de serializar ---\n");

        pedidos.forEach(System.out::println);

        if (!guardarPedidos(pedidos, p)) {
            System.out.println("No se actualizarán las propiedades porque no se guardaron los pedidos.");
            return;
        }

        if (actualizarPropiedades(p, pedidos.size())) {
            comprobarPropiedadGuardada();
        }

        System.out.println("\n--- Pedidos recuperados ---\n");

        ArrayList<Order> pedidosRecuperados = recuperarPedidos(p);
        pedidosRecuperados.forEach(System.out::println);

        double importeMinimo;

        try {
            importeMinimo = Double.parseDouble(p.getProperty("minimum.amount", "0"));
        } catch (NumberFormatException e) {
            System.out.println("El importe mínimo configurado no es válido.");
            return;
        }

        double total = 0;

        System.out.println("--- Pedidos que cumplen el importe mínimo ---\n");

        for (Order pedido : pedidosRecuperados) {
            total += pedido.getImporteTotal();

            if (pedido.getImporteTotal() >= importeMinimo) {
                System.out.println(pedido);
            }
        }

        System.out.println("Importe mínimo: " + importeMinimo);
        System.out.println("Importe total de todos los pedidos: " + total);
    }

    /**
     * Carga las propiedades desde el fichero de configuración.
     *
     * @return propiedades cargadas
     */
    public static Properties cargarPropiedades() {
        Properties p = new Properties();

        try (FileInputStream fis = new FileInputStream(ARCHIVO_PROPIEDADES.toFile())) {
            p.load(fis);

            System.out.println("--- Propiedades cargadas ---\n");
            p.forEach((key, value) -> System.out.println(key + " = " + value));

        } catch (IOException e) {
            System.out.println("Error al cargar las propiedades: " + e.getMessage());
        }

        return p;
    }

    /**
     * Guarda una lista de pedidos en un fichero.
     *
     * @param pedidos lista de pedidos que se guardará
     * @param p propiedades de configuración
     * @return true si los pedidos se guardan correctamente
     */
    public static boolean guardarPedidos(ArrayList<Order> pedidos, Properties p) {
        String archivo = p.getProperty("data.file");

        if (archivo == null || archivo.isBlank()) {
            System.out.println("Error: no se ha configurado data.file.");
            return false;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(pedidos);

            System.out.println("Pedidos guardados en " + archivo);
            return true;

        } catch (IOException e) {
            System.out.println("Error al guardar los pedidos: " + e.getMessage());
            return false;
        }
    }

    /**
     * Recupera los pedidos almacenados en un fichero.
     *
     * @param p propiedades de configuración
     * @return lista de pedidos recuperados
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Order> recuperarPedidos(Properties p) {
        String archivo = p.getProperty("data.file");

        if (archivo == null || archivo.isBlank()) {
            System.out.println("Error: no se ha configurado data.file.");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            ArrayList<Order> recuperados = (ArrayList<Order>) ois.readObject();

            System.out.println("Pedidos recuperados desde " + archivo);
            return recuperados;

        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("Error al recuperar los pedidos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Actualiza las propiedades y guarda la cantidad de pedidos.
     *
     * @param p propiedades que se actualizarán
     * @param cantidad número de pedidos guardados
     * @return true si las propiedades se actualizan correctamente
     */
    public static boolean actualizarPropiedades(Properties p, int cantidad) {
        p.setProperty("orders.saved", String.valueOf(cantidad));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_PROPIEDADES.toFile()))) {
            p.store(bw, "Configuración del gestor de pedidos");

            System.out.println("Propiedades actualizadas correctamente.");
            return true;

        } catch (IOException e) {
            System.out.println("Error al actualizar las propiedades: " + e.getMessage());
            return false;
        }
    }

    /**
     * Comprueba el valor de orders.saved en el fichero de configuración.
     */
    public static void comprobarPropiedadGuardada() {
        Properties propiedades = new Properties();

        try (FileInputStream fis = new FileInputStream(ARCHIVO_PROPIEDADES.toFile())) {
            propiedades.load(fis);

            System.out.println("Valor de orders.saved leído del archivo: "
                    + propiedades.getProperty("orders.saved"));

        } catch (IOException e) {
            System.out.println("Error al comprobar las propiedades guardadas: " + e.getMessage());
        }
    }
}