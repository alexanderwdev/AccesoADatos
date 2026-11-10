package Actividad2.servicio;

import Actividad2.dao.UsuarioDAO;
import Actividad2.modelo.Usuario;

public class AutenticacionServicio {

    private UsuarioDAO usuarioDAO;

    public AutenticacionServicio() {
        usuarioDAO = new UsuarioDAO();
    }

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

    public boolean esAdministrador(Usuario usuario) {
        return usuario != null && usuario.getRol().equalsIgnoreCase("ADMIN");
    }
}