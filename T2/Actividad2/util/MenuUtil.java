package Actividad2.util;

import java.util.Scanner;

public class MenuUtil {
    private static final Scanner scanner = new Scanner(System.in);

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

    public static void mostrarMenuParticipantes() {
        System.out.println("\n--- GESTIÓN DE PARTICIPANTES ---");
        System.out.println("1. Añadir participante");
        System.out.println("2. Modificar participante");
        System.out.println("3. Eliminar participante");
        System.out.println("4. Buscar participante");
        System.out.println("5. Listar participantes");
        System.out.println("0. Volver");
    }

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

    public static void mostrarMenuInscripciones() {
        System.out.println("\n--- GESTIÓN DE INSCRIPCIONES ---");
        System.out.println("1. Inscribir participante");
        System.out.println("2. Cancelar inscripción");
        System.out.println("3. Ver inscripciones de un participante");
        System.out.println("4. Ver participantes de una expedición");
        System.out.println("0. Volver");
    }

    public static int pedirOpcion() {
        System.out.print("Selecciona una opción: ");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Debes introducir un número válido.");
            return -1;
        }
    }

    public static String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

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

    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}