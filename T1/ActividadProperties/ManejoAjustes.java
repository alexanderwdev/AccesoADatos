package ActividadProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

/**
 * Gestiona la carga y actualización de las propiedades de configuración del juego.
 *
 * @author Alexander Wheatley
 */
public class ManejoAjustes {

    private static final Path ARCHIVO_PROPIEDADES =
            Path.of("T1/ActividadProperties/game-settings.properties");

    /**
     * Carga las propiedades del archivo de configuración y las muestra por pantalla.
     *
     * @return propiedades cargadas del archivo
     */
    public static Properties cargarPropiedades() {

        Properties p = new Properties();

        try (FileInputStream fis = new FileInputStream(ARCHIVO_PROPIEDADES.toFile())) {
            p.load(fis);

            System.out.println("\n--- Configuración actual del juego ---\n");
            p.forEach((key, value) -> System.out.println(key + " = " + value));

        } catch (IOException e) {
            System.out.println("Error al cargar las propiedades: " + e.getMessage());
        }

        return p;
    }

    /**
     * Actualiza una propiedad del archivo de configuración.
     * Si la propiedad es app.points, suma el nuevo valor a los puntos actuales.
     *
     * @param p propiedades que se van a actualizar
     * @param propiedad nombre de la propiedad que se desea modificar
     * @param valorNuevo nuevo valor de la propiedad o cantidad de puntos que se sumarán
     */
    public static void actualizarPropiedades(Properties p, String propiedad, String valorNuevo) {
        try {
            List<String> lineas = Files.readAllLines(ARCHIVO_PROPIEDADES);
            String valorActualizado = valorNuevo;

            if (propiedad.equals("app.points")) {
                int puntosActuales = Integer.parseInt(p.getProperty("app.points", "0"));
                int cantidad = Integer.parseInt(valorNuevo);

                valorActualizado = String.valueOf(puntosActuales + cantidad);
                p.setProperty("app.points", valorActualizado);

            } else {
                p.setProperty(propiedad, valorNuevo);
            }

            System.out.println("Propiedad cambiada con éxito.");

            for (int i = 0; i < lineas.size(); i++) {
                String linea = lineas.get(i);

                if (linea.startsWith(propiedad + "=")) {
                    lineas.set(i, propiedad + "=" + valorActualizado);
                    break;
                }
            }

            Files.write(ARCHIVO_PROPIEDADES, lineas);

        } catch (IOException e) {
            System.out.println("Error al cambiar la configuración: " + e.getMessage());
        }
    }
}