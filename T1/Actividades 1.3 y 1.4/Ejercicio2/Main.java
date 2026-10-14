package Ejercicio2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Programa para guardar y recuperar equipos informáticos.
 *
 * @author Alexander Wheatley
 */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Path carpeta = Path.of("T1", "carpetasEjercicios", "datosEquiposInformaticos");
        Path rutaFichero = carpeta.resolve("equipos.dat");

        EquipoInformatico eq1 = new EquipoInformatico(1001, "Lenovo", "ThinkPad E15", "Ana López", 16, EquipoInformatico.Estado.OPERATIVO);
        EquipoInformatico eq2 = new EquipoInformatico(1002, "HP", "ProBook 450", "Luis Pérez", 8, EquipoInformatico.Estado.REPARACION);
        EquipoInformatico eq3 = new EquipoInformatico(1003, "Dell", "Latitude 5540", "Marta García", 16, EquipoInformatico.Estado.OPERATIVO);
        EquipoInformatico eq4 = new EquipoInformatico(1004, "Acer", "TravelMate", "Carlos Ruiz", 8, EquipoInformatico.Estado.RETIRADO);

        ArrayList<EquipoInformatico> equipos = new ArrayList<>();
        equipos.add(eq1);
        equipos.add(eq2);
        equipos.add(eq3);
        equipos.add(eq4);

        guardarEquipos(rutaFichero, equipos);

        System.out.println("Se han guardado " + equipos.size() + " equipos en equipos.dat");

        ArrayList<EquipoInformatico> equiposRecuperados = recuperarEquipos(rutaFichero);

        System.out.println("Equipos recuperados");
        for (EquipoInformatico equipo : equiposRecuperados) System.out.println(equipo);

        int operativos = 0;

        for (EquipoInformatico equipo : equiposRecuperados) {
            if (equipo.getEstadoActual() == EquipoInformatico.Estado.OPERATIVO) operativos++;
        }

        int con16GB = 0;
        for (EquipoInformatico equipo : equipos) {
            if (equipo.getMemoria() >= 16) con16GB++;
        }

        System.out.println("Equipos operativos: " + operativos);
        System.out.println("Equipos con al menos 16 GB de RAM: " + con16GB);
    }

    /**
     * Guarda una lista de equipos en un fichero.
     *
     * @param ruta ruta del fichero
     * @param equipos lista de equipos a guardar
     * @throws IOException si ocurre un error al guardar
     */
    public static void guardarEquipos(Path ruta, ArrayList<EquipoInformatico> equipos) throws IOException {
        Files.createDirectories(ruta.getParent());

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta.toFile()))) {
            oos.writeObject(equipos);
        }
    }

    /**
     * Recupera una lista de equipos desde un fichero.
     *
     * @param ruta ruta del fichero
     * @return lista de equipos recuperados
     * @throws IOException si ocurre un error al leer
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static ArrayList<EquipoInformatico> recuperarEquipos(Path ruta) throws IOException, ClassNotFoundException {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta.toFile()))) {
            return (ArrayList<EquipoInformatico>) ois.readObject();
        }
    }
}