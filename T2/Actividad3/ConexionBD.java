package Actividad3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexión con la base de datos MySQL.
 *
 * @author Alexander Wheatley
 */
public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3308/tienda";

    private static final String USER = "root";
    private static final String PASSWORD = "root";

    /**
     * Establece y devuelve una conexión con la base de datos.
     *
     * @return conexión con la base de datos
     * @throws SQLException si ocurre un error al establecer la conexión
     */
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}