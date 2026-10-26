package ActividadPractica;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    private static final Path rutaActividad = Path.of("T1/ActividadPractica");
    private static final Path archivoConfig = rutaActividad.resolve("config.properties");
    private static final Path archivoParticipantes = rutaActividad.resolve("Almacenamiento/participantes.dat");

    public static void main(String[] args) throws IOException {
        Properties p = cargarPropiedades();


    }

    public static Properties cargarPropiedades() throws IOException {
        Properties p = new Properties();

        p.setProperty("asociación","castorcas");
        p.setProperty("almacenamiento","T1/ActividadPractica/Almacenamiento");
        p.setProperty("km_min","100");

        if (!Files.exists(archivoConfig)) {
            try (BufferedWriter bw = Files.newBufferedWriter(archivoConfig)){
                p.store(bw, "Configuración");
                return p;
            }
        }

        try (BufferedReader br = Files.newBufferedReader(archivoConfig)) {
            p.load(br);
        }

        System.out.println("Se ha cargado la configuración correctamente.");
        return p;
    }

    public static void guardarPropiedades(Properties p) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(archivoConfig)) {
            p.store(bw, "Configuración");
            System.out.println("Se ha guardado la configuración correctamente.");
        }
    }

    public static List<Participante> recuperarParticipantes() throws IOException {
        List<Participante> lista = new ArrayList<>();

        if (!Files.exists(archivoParticipantes)) {
            return lista;
        }

        try (ObjectInputStream oos = new ObjectInputStream(Files.newInputStream(archivoParticipantes))) {
            Participante par;

            while ((par = (Participante) oos.readObject()) !=null) {
                lista.add(par);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public static void menu() {
        System.out.println("""
                1. Añadir participante
                2. Registrar kilómetros
                3. Añadir rutas
                4. Consultar datos
                5. Modificar la observación provisional
                0. Salir""");
    }
}




