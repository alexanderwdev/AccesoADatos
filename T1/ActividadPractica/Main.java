package ActividadPractica;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class Main {
    private static final Path rutaProperties = Path.of("T1/ActividadPractica");
    private static final Path archivoConfig = rutaProperties.resolve("config.properties");

    public static void main(String[] args) {
        cargarPropiedades();
    }

    public static void cargarPropiedades() {
        Properties p = new Properties();

        p.setProperty("asociación","castorcas");
        p.setProperty("almacenamiento","T1/ActividadPractica/Almacenamiento");
        p.setProperty("km_min","100");

        if (!archivoConfig.toFile().exists()) {
            try (BufferedWriter bos = Files.newBufferedWriter(archivoConfig)){
                p.store(bos, "Configuración");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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




