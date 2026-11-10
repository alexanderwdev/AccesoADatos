package Actividad2.dao;

import Actividad2.config.ConexionBD;
import Actividad2.modelo.Usuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona las operaciones de acceso a la base de datos relacionadas con los usuarios.
 *
 * @author Alexander Wheatley
 */
public class UsuarioDAO {

    /**
     * Busca un usuario mediante su nombre de usuario.
     *
     * @param nombreUsuario nombre de usuario que se desea buscar
     * @return usuario encontrado o null si no existe
     */
    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ?";

        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("nombre_usuario"),
                            rs.getString("password_hash"),
                            rs.getString("rol")
                    );
                }
            }

        } catch (SQLException | IOException e) {
            System.out.println("Error al buscar el usuario: " + e.getMessage());
        }

        return null;
    }

    /**
     * Obtiene todos los usuarios almacenados en la base de datos.
     *
     * @return lista con todos los usuarios
     */
    public List<Usuario> obtenerTodos() {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";

        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre_usuario"),
                        rs.getString("password_hash"),
                        rs.getString("rol")
                );

                usuarios.add(u);
            }

        } catch (SQLException | IOException e) {
            System.out.println("Error al obtener usuarios: " + e.getMessage());
        }

        return usuarios;
    }
}