package Actividad2.util;

import java.util.Scanner;

/**
 * Proporciona métodos para mostrar menús y solicitar datos al usuario.
 *
 * @author Alexander Wheatley
 */
public class MenuUtil {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Muestra el menú principal de la aplicación.
     */
    public static void mostrarMenuPrincipal() {
        System.out.println("\n================================");
        System.out.println("     EXPEDICIONES");
        System.out.println("================================");
        System.out.println("1. Gestionar participantes");
        System.out.println("2. Gestionar expediciones");
        System.out.println("3. Gestionar inscripciones");
        System.out.println("4. Listar participantes");
        System.out.println("5. Listar expediciones");
        System.out.println("6. Cerrar sesión");
        System.out.println("0. Salir");
        System.out.println("================================");
    }

    /**
     * Muestra el menú de gestión de participantes.
     */
    public static void mostrarMenuParticipantes() {
        System.out.println("\n--- GESTIÓN DE PARTICIPANTES ---");
        System.out.println("1. Añadir participante");
        System.out.println("2. Modificar participante");
        System.out.println("3. Eliminar participante");
        System.out.println("4. Buscar participante");
        System.out.println("5. Listar participantes");
        System.out.println("0. Volver");
    }

    /**
     * Muestra el menú de gestión de expediciones.
     */
    public static void mostrarMenuExpediciones() {
        System.out.println("\n--- GESTIÓN DE EXPEDICIONES ---");
        System.out.println("1. Añadir expedición");
        System.out.println("2. Modificar expedición");
        System.out.println("3. Eliminar expedición");
        System.out.println("4. Buscar expedición");
        System.out.println("5. Listar expediciones");
        System.out.println("6. Finalizar expedición");
        System.out.println("0. Volver");
    }

    /**
     * Muestra el menú de gestión de inscripciones.
     */
    public static void mostrarMenuInscripciones() {
        System.out.println("\n--- GESTIÓN DE INSCRIPCIONES ---");
        System.out.println("1. Inscribir participante");
        System.out.println("2. Cancelar inscripción");
        System.out.println("3. Ver inscripciones de un participante");
        System.out.println("4. Ver participantes de una expedición");
        System.out.println("0. Volver");
    }

    /**
     * Solicita una opción al usuario y comprueba que sea un número entero.
     *
     * @return opción introducida o -1 si no es válida
     */
    public static int pedirOpcion() {
        System.out.print("Selecciona una opción: ");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Debes introducir un número válido.");
            return -1;
        }
    }

    /**
     * Solicita un texto al usuario.
     *
     * @param mensaje mensaje que se muestra antes de solicitar el texto
     * @return texto introducido
     */
    public static String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    /**
     * Solicita un número entero hasta que se introduzca uno válido.
     *
     * @param mensaje mensaje que se muestra antes de solicitar el número
     * @return número entero introducido
     */
    public static int pedirEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);

            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero válido.");
            }
        }
    }

    /**
     * Solicita un número decimal hasta que se introduzca uno válido.
     *
     * @param mensaje mensaje que se muestra antes de solicitar el número
     * @return número decimal introducido
     */
    public static double pedirDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);

            try {
                return Double.parseDouble(scanner.nextLine().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número decimal válido.");
            }
        }
    }

    /**
     * Muestra un mensaje por pantalla.
     *
     * @param mensaje texto que se quiere mostrar
     */
    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}