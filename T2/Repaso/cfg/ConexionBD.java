package Repaso.cfg;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {
    private static final Path propiedades = Path.of("T2/Repaso");

    public static Properties cargarAjustes() {
        Properties p = new Properties();

        try {
            Path fichero = Path.of("configuracionBD.properties");

            if (!Files.exists(fichero)) fichero = Path.of("T2", "Repaso", "configuracionBD.properties");

            if (!Files.exists(fichero)) throw new IOException("No se encuentra datasource.properties en: " + fichero.toAbsolutePath());

            try (InputStream is = Files.newInputStream(fichero)) {
                p.load(is);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return p;
    }

    public static Connection getConexion() throws SQLException, IOException {
        Properties p = cargarAjustes();

        String url = p.getProperty("db.url");
        String usuario = p.getProperty("db.user");
        String contrasena = p.getProperty("db.password");

        if (url == null || usuario == null || contrasena == null) {
            throw new IOException("Faltan propiedades en configuracionBD.properties");
        }

        return DriverManager.getConnection(url,usuario,contrasena);
    }
}
