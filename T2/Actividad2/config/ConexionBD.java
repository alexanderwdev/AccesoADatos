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

        Path fichero = Path.of("datasource.properties");

        if (!Files.exists(fichero)) fichero = Path.of("T2", "Actividad2", "datasource.properties");

        if (!Files.exists(fichero)) {
            throw new IOException("No se encuentra datasource.properties en: " + fichero.toAbsolutePath());
        }

        try (InputStream is = Files.newInputStream(fichero)) {
            p.load(is);
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

        String url = p.getProperty("db.url");
        String usuario = p.getProperty("db.user");
        String password = p.getProperty("db.password");

        if (url == null || usuario == null || password == null) {
            throw new IOException("Faltan propiedades en datasource.properties");
        }

        return DriverManager.getConnection(url, usuario, password);
    }
}