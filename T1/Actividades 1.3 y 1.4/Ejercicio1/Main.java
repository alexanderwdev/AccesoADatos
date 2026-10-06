package Ejercicio1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Introduce la ruta a analizar: ");
            String user = sc.nextLine();
            Path ruta = Path.of(user);
            if (validarRuta(ruta)) {
                System.out.println("Ruta encontrada");
                System.out.println("--- Primer nivel ---");
                mostrarPrimerNivel(ruta);
                System.out.println("--- Recorrido completo ---");
                analizarEstructura(ruta);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    public static void mostrarPrimerNivel(Path ruta) throws IOException {
        try (Stream<Path> primerNivel = Files.list(ruta)) {
            primerNivel.forEach(System.out::println);
        }
    }

    public static void analizarEstructura(Path ruta) throws IOException {
        try (Stream<Path> estructura = Files.walk(ruta)) {
            ArrayList<Path> elementos = new ArrayList<>(estructura.toList());
            long numDir = elementos.stream().filter(Files::isDirectory).count();
            long numFic = elementos.stream().filter(Files::isRegularFile).count();
            long numJava = elementos.stream().filter(e -> e.toString().endsWith(".java")).count();
            long maxSize = 0;
            Path ficheroMax = null;
            long size = elementos.stream().mapToLong(e -> {
                try {
                    return Files.size(e);
                } catch (IOException ex) {
                    return 0;
                }
            }).sum();

            elementos.forEach(System.out::println);
            System.out.println("--- Cálculo de estadísticas");
            System.out.println("Número de directorios: " + numDir);
            System.out.println("Número de archivos: " + numFic);
            System.out.println("Número de archivos .java: " + numJava);
            System.out.println("Tamaño total ocupado: " + size + " bytes");

        }
    }

    public void guardarEnFichero(String resumen, Path destino) {

    }
}
