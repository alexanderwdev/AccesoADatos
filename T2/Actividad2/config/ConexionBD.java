package Actividad2.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Gestiona la carga de propiedades y la conexión con la base de datos.
 *
 * @author Alexander Wheatley
 */
public class ConexionBD {

    private static final Path propiedades = Path.of("T2/Actividad2");
    private static final Path propiedadesFichero = propiedades.resolve("datasource.properties");

    /**
     * Carga las propiedades desde el archivo de configuración.
     *
     * @return propiedades cargadas del archivo
     * @throws IOException si ocurre un error al leer el archivo
     */
    public static Properties cargarPropiedades() throws IOException {
        Properties p = new Properties();

        if (Files.exists(propiedadesFichero)) {
            try (InputStream is = Files.newInputStream(propiedadesFichero)) {
                p.load(is);
            }
        } else {
            System.out.println("No existe el archivo");
        }

        return p;
    }

    /**
     * Establece una conexión con la base de datos utilizando las propiedades cargadas.
     *
     * @return conexión abierta con la base de datos
     * @throws SQLException si ocurre un error al establecer la conexión
     * @throws IOException si ocurre un error al cargar el archivo de configuración
     */
    public static Connection getConexion() throws SQLException, IOException {
        Properties p = cargarPropiedades();

        return DriverManager.getConnection(
                p.getProperty("db.url"),
                p.getProperty("db.user"),
                p.getProperty("db.password")
        );
    }
}