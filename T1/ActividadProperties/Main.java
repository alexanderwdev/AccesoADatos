package ActividadProperties;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Clase principal que inicia el juego y permite modificar sus ajustes.
 *
 * @author Alexander Wheatley
 */
public class Main {

    /**
     * Inicia el juego, carga la configuración y permite al usuario
     * modificar las propiedades y sumar puntos.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {
        System.out.println("Iniciando Severo Game...");
        Properties ajustes = ManejoAjustes.cargarPropiedades();

        if (ajustes.isEmpty()) {
            System.out.println("No se han podido cargar los ajustes.");
            return;
        }

        System.out.println("\nBienvenido, sumando 50 puntos por abrir el juego");
        ManejoAjustes.actualizarPropiedades(ajustes, "app.points", "50");

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("¿Desea cambiar alguna propiedad? [S/N]: ");
            char eleccion = sc.next().charAt(0);

            while (eleccion != 'S' && eleccion != 'N') {
                System.out.print("Escoge una de las dos opciones: ");
                eleccion = sc.next().charAt(0);
            }

            if (eleccion == 'S') {
                System.out.print("Indique el nombre de la propiedad a modificar: ");
                String propiedad = sc.next();

                System.out.print("Ahora el valor: ");
                String valor = sc.next();

                ManejoAjustes.actualizarPropiedades(ajustes, propiedad, valor);
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}