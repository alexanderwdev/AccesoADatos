package Ejercicio1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class RegistroAlumnos {
    public static void main(String[] args) {
        File carpeta = new File("T1/carpetasEjercicios/datos");

        if (!carpeta.exists()) {
            if (carpeta.mkdir()) {
                System.out.println("Carpeta datos creada");
            } else {
                System.out.println("No se ha podido crear la carpeta");
            }
        }
        File fichero = new File(carpeta, "alumnos.txt");
        Path ficheroNio = Path.of("T1/carpetasEjercicios/datos", "alumnos_nio.txt");

        // Parte A - Escritura

        // Datos iniciales
        String[] alumnos = {
                "Ana López;DAM;8.5",
                "Luis Pérez;DAW;6.7",
                "Marta García;DAM;9.1",
                "Carlos Ruiz;DAW;4.8"
        };

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(fichero))) {

            for (String a : alumnos) {
                bf.write(a);
                bf.newLine();
            }

            System.out.println("Datos iniciales guardados");
        } catch (IOException e) {
            System.out.println("Error al escribir en alumnos.txt: " + e.getMessage());
        }

        // Quinto alumno sin borrar los demás
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce los datos del quinto alumno");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ciclo: ");
        String ciclo = sc.nextLine();

        System.out.print("Nota: ");
        String nota = sc.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true))){
            bw.write(nombre + ";" + ciclo + ";" + nota);
            bw.newLine();

            System.out.println("Quinto alumno añadido");
        } catch (IOException e) {
            System.out.println("Error al añadir el quinto alumno: " + e.getMessage());
        }

        // Escribir usando Files (NIO)
        try {
            Files.write(ficheroNio, List.of(alumnos), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("Datos guardados en alumnos.txt");
        } catch (IOException e) {
            System.out.println("Error al escribir en alumnos.txt: " + e.getMessage());
        }

        // Parte B - Lectura

        System.out.println("--- Lectura de alumnos ---");
    }


}
