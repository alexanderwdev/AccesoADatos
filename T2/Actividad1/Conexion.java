package Actividad1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexión con la base de datos MySQL.
 *
 * @author Alexander Wheatley
 */
public class Conexion {

    private static final String URL = "jdbc:mysql://127.0.0.1:3307/prueba_jdbc";

    private static final String USER = "alumno";

    private static final String PASSWORD = "Alumno1234";

    /**
     * Establece una conexión con la base de datos.
     *
     * @return conexión abierta con la base de datos
     * @throws SQLException si ocurre un error al establecer la conexión
     */
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}