package Actividades1_3_1_4.Ejercicio1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Programa para analizar la estructura de un directorio.
 *
 * @author Alexander Wheatley
 */
public class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Introduce la ruta a analizar: ");
            String user = sc.nextLine();
            Path ruta = Path.of(user);

            if (validarRuta(ruta)) {
                System.out.println("Ruta encontrada");
                System.out.println("\n--- Contenido del primer nivel ---");
                mostrarPrimerNivel(ruta);

                System.out.println("\n --- Resumen del análisis ---");
                String datos = analizarEstructura(ruta);
                System.out.println(datos);

                System.out.println("Guardando resumen...");
                guardarEnFichero(datos,
                        Path.of("T1", "carpetasEjercicios", "analisisDirectorios", "resumen.txt"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Comprueba que la ruta existe y es un directorio.
     *
     * @param ruta ruta a validar
     * @return true si la ruta es válida
     */
    public static boolean validarRuta(Path ruta) {
        if (ruta == null) {
            System.out.println("La ruta no puede ser nula");
            return false;
        }

        if (Files.notExists(ruta)) {
            System.out.println("La ruta no existe");
            return false;
        }

        if (!Files.isDirectory(ruta)) {
            System.out.println("Esta ruta no es un directorio.");
            return false;
        }

        return true;
    }

    /**
     * Muestra el contenido del primer nivel del directorio.
     *
     * @param ruta directorio a mostrar
     * @throws IOException si ocurre un error al leer el directorio
     */
    public static void mostrarPrimerNivel(Path ruta) throws IOException {
        try (Stream<Path> primerNivel = Files.list(ruta)) {
            primerNivel.forEach(e -> System.out.println(ruta.relativize(e)));
        }
    }

    /**
     * Analiza la estructura del directorio y obtiene estadísticas.
     *
     * @param ruta directorio a analizar
     * @return resumen del análisis
     * @throws IOException si ocurre un error al leer los archivos
     */
    public static String analizarEstructura(Path ruta) throws IOException {
        try (Stream<Path> estructura = Files.walk(ruta)) {
            ArrayList<Path> elementos = new ArrayList<>(estructura.toList());

            long numDir = elementos.stream()
                    .filter(Files::isDirectory)
                    .count();

            long numFic = elementos.stream()
                    .filter(Files::isRegularFile)
                    .count();

            long numJava = elementos.stream()
                    .filter(Files::isRegularFile)
                    .filter(e -> e.toString().endsWith(".java"))
                    .count();

            long maxSize = 0;
            Path ficheroMax = null;

            for (Path el : elementos) {
                if (Files.isRegularFile(el)) {
                    long tamano = Files.size(el);

                    if (tamano > maxSize) {
                        maxSize = tamano;
                        ficheroMax = el;
                    }
                }
            }

            long size = 0;

            for (Path el : elementos) {
                if (Files.isRegularFile(el)) {
                    size += Files.size(el);
                }
            }

            StringBuilder resumen = new StringBuilder();
            resumen.append("FICHEROS ENCONTRADOS\n");

            for (Path el : elementos) {
                if (Files.isRegularFile(el)) {
                    long tamano = Files.size(el);

                    size += tamano;

                    resumen.append(ruta.relativize(el))
                            .append("\n");

                    if (tamano > maxSize) {
                        maxSize = tamano;
                        ficheroMax = el;
                    }
                }
            }

            resumen.append("\n");
            resumen.append("--- Cálculo de estadísticas ---\n");

            resumen.append("Número de directorios: ").append(numDir).append("\n");
            resumen.append("Número de archivos: ").append(numFic).append("\n");
            resumen.append("Número de archivos .java: ").append(numJava).append("\n");
            resumen.append("Tamaño total ocupado: ").append(size).append(" bytes\n");

            if (ficheroMax != null) {
                resumen.append("Fichero de mayor tamaño: ")
                        .append(ruta.relativize(ficheroMax))
                        .append("\n");
                resumen.append("Tamaño del fichero mayor: ")
                        .append(maxSize)
                        .append(" bytes\n");
            } else {
                resumen.append("No hay ficheros en la carpeta.\n");
            }

            return resumen.toString();
        }
    }

    /**
     * Guarda el resumen en un fichero.
     *
     * @param resumen texto que se guardará
     * @param destino ruta del fichero de destino
     * @throws IOException si ocurre un error al escribir el fichero
     */
    public static void guardarEnFichero(String resumen, Path destino) throws IOException {
        Files.writeString(destino, resumen);
    }
}