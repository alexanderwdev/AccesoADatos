package Ejercicio1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

// @author: Alexander Wheatley Neagu

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

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true))) {
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

        int contador = 0;
        int aprobados = 0;
        double sumaNotas = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;

            // Lectura secuencial hasta llegar al final del fichero
            while ((linea = br.readLine()) != null) {
                // Ignorar las líneas vacías
                if (linea.trim().isEmpty()) {
                    continue;
                }

                // Separar los campos
                String[] datos = linea.split(";");

                // Comprobar que están los tres campos
                if (datos.length != 3) {
                    System.out.println("Registro incorrecto: " + linea);
                    continue;
                }

                String nombreAlumno = datos[0];
                String cicloAlumno = datos[1];
                double notaAlumno;

                try {
                    notaAlumno = Double.parseDouble(datos[2]);
                } catch (NumberFormatException e) {
                    System.out.println("Nota incorrecta en el registro: " + linea);
                    continue;
                }

                contador++;
                sumaNotas += notaAlumno;

                // Mostrar info alumno
                System.out.println("Nombre: " + nombreAlumno + " | Ciclo: " + cicloAlumno + " | Nota: " + notaAlumno);

                // Contar alumnos aprobados
                if (notaAlumno >= 5) {
                    aprobados++;
                }
            }

            // Mostrar número de registros
            System.out.println("Número de registros: " + contador);

            // Mostrar alumnos aprobados
            System.out.println("Alumnos con nota igual o superior a 5:");

            // Leer solo los aprobados
            try (BufferedReader bwAprobados = new BufferedReader(new FileReader(fichero))) {
                while ((linea = bwAprobados.readLine()) != null) {
                    if (linea.trim().isEmpty()) {
                        continue;
                    }

                    String[] datos = linea.split(";");

                    if (datos.length != 3) {
                        continue;
                    }

                    try {
                        double notaAlumno = Double.parseDouble(datos[2]);

                        if (notaAlumno >= 5) {
                            System.out.println(datos[0] + " - " + datos[1] + " - " + notaAlumno);
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Registro con nota incorrecta: " + linea);
                    }
                }
            }

            // Calcular la nota media
            if (contador > 0) {
                double media = sumaNotas / contador;

                System.out.println("Nota media del grupo: " + media);
            } else {
                System.out.println("No hay registros válidos para calcular la media.");
            }
        } catch (IOException e) {
            System.out.println("Error durante la lectura de alumnos.txt: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("No tienes permisos para acceder al fichero");
        }
        sc.close();
    }
}
