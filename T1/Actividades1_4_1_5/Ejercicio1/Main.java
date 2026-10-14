package Actividades1_4_1_5.Ejercicio1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Reservation res1 = new Reservation(1, "Carlos Palazón", "Pelota de fútbol", 60, List.of("Lionel Messi", "Cristiano Ronaldo"), "ABC1");
        Reservation res2 = new Reservation(2, "Hector El Baroudi", "Raqueta de tenis", 30, List.of("Rafael Nadal", "Serena Williams"), "ABC2");
        Reservation res3 = new Reservation(3, "Sergio Navalón", "Balón de baloncesto", 45, List.of("LeBron James", "Stephen Curry"), "ABC3");
        Reservation res4 = new Reservation(4, "Ebrahem García", "Bicicleta de montaña", 150, List.of("Tadej Pogačar", "Jonas Vingegaard"), "ABC4");

        System.out.println("--- Reservas Pre-Serialización ---\n");
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);

        ArrayList<Reservation> reservas = new ArrayList<>();
        reservas.add(res1);
        reservas.add(res2);
        reservas.add(res3);
        reservas.add(res4);

        guardarReservas(reservas);
        System.out.println("\n--- Reservas Post-Serialización ---\n");
        ArrayList<Reservation> reservasRecuperadas = recuperarReservas();

        reservasRecuperadas.forEach(System.out::println);
    }

    public static void guardarReservas(ArrayList<Reservation> reservas) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("T1/carpetasEjercicios/reservas/reservas.dat"))) {
            salida.writeObject(reservas);
            System.out.println("Reservas guardadas.");
        } catch (IOException e) {
            System.out.println("Error al guardar las reservas: " + e.getMessage());
        }
    }

    public static ArrayList<Reservation> recuperarReservas() {
        ArrayList<Reservation> reservasRecuperadas = new ArrayList<>();
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("T1/carpetasEjercicios/reservas/reservas.dat"))) {
            reservasRecuperadas = (ArrayList<Reservation>) entrada.readObject();
            System.out.println("Reservas recuperadas correctamente.");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al recuperar las reservas: " + e.getMessage());
        }

        return reservasRecuperadas;
    }
}
