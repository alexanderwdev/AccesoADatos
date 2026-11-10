package Actividad2.servicio;

import Actividad2.dao.UsuarioDAO;
import Actividad2.modelo.Usuario;

/**
 * Gestiona la autenticación de los usuarios del sistema.
 *
 * @author Alexander Wheatley
 */
public class AutenticacionServicio {

    private UsuarioDAO usuarioDAO;

    /**
     * Crea el servicio de autenticación e inicializa el DAO de usuarios.
     */
    public AutenticacionServicio() {
        usuarioDAO = new UsuarioDAO();
    }

    /**
     * Comprueba los datos e inicia sesión si son correctos.
     *
     * @param nombreUsuario nombre del usuario
     * @param password contraseña introducida
     * @return usuario autenticado o null si los datos son incorrectos
     */
    public Usuario iniciarSesion(String nombreUsuario, String password) {
        if (nombreUsuario == null || nombreUsuario.isBlank()
                || password == null || password.isBlank()) {
            System.out.println("Introduce el usuario y la contraseña.");
            return null;
        }

        Usuario usuario = usuarioDAO.buscarPorNombreUsuario(nombreUsuario);

        if (usuario == null) {
            System.out.println("El usuario no existe.");
            return null;
        }

        if (!usuario.getPasswordHash().equals(password)) {
            System.out.println("Contraseña incorrecta.");
            return null;
        }

        System.out.println("Inicio de sesión correcto.");
        return usuario;
    }

    /**
     * Comprueba si un usuario tiene permisos de administrador.
     *
     * @param usuario usuario que se quiere comprobar
     * @return true si el usuario es administrador, false en caso contrario
     */
    public boolean esAdministrador(Usuario usuario) {
        return usuario != null && usuario.getRol().equalsIgnoreCase("ADMIN");
    }
}