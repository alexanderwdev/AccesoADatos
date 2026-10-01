package Ejercicio2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CopiaBinaria {
    public static void main(String[] args) {
        Path carpetaEntrada = Path.of("carpetaEntrada");
        Path carpetaSalida = Path.of("carpetaSalida");

        Path origen = carpetaEntrada.resolve("origen.pdf");

        try {
            Files.createDirectories(carpetaSalida);

            if (!Files.exists(origen)) {
                System.out.println("El archivo origen no existe.");
            }

            System.out.println("Archivo origen: " + origen);
            System.out.println("Tamaño original: " + Files.size(origen) + " bytes");

            Path destino1 = carpetaSalida.resolve("copia_stream.pdf");

            // FIS/FOS
            try (FileInputStream entrada = new FileInputStream(origen.toFile()); FileOutputStream salida = new FileOutputStream(destino1.toFile())) {
                int dato;
                while ((dato = entrada.read()) != -1) {
                    salida.write(dato);
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }


            // BIS/BOS
            Path destino2 = carpetaSalida.resolve("copia_buffer.pdf");

            try (BufferedInputStream entrada = new BufferedInputStream(new FileInputStream(origen.toFile())); BufferedOutputStream salida = new BufferedOutputStream(new FileOutputStream(destino2.toFile()))) {
                int dato;

                while ((dato = entrada.read()) != -1) {
                    salida.write(dato);
                }

            } catch (IOException e) {
                System.out.println("Error en la segunda copia: " + e.getMessage());
            }

            // Files.copy()
            Path destino3 = carpetaSalida.resolve("copia_files.pdf");

            try {
                Files.copy(origen, destino3, StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {
                System.out.println("Error en la tercera copia: " + e.getMessage());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

// Explicación

// La copia byte a byte con FIS y FOS es la más sencilla de entender por qué muestra directamente cómo se lee y se escribe cada byte.

// La versión con BIS y BOS evita más operaciones físicas de lectura y escritura porque utiliza buffers en memoria. Agrupa las operaciones.

// Files.copy() es la opción más sencilla para realizar una copia cuando no necesitamos controlar manualmente el proceso.