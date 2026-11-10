package Actividad2.modelo;

/**
 * Representa a un usuario del sistema con sus datos de acceso y rol.
 *
 * @author Alexander Wheatley
 */
public class Usuario {
    private int id;
    private String nombreUsuario;
    private String passwordHash;
    private String rol;

    /**
     * Crea un usuario con sus datos.
     *
     * @param id identificador del usuario
     * @param nombreUsuario nombre de usuario
     * @param passwordHash contraseña almacenada mediante un hash
     * @param rol rol asignado al usuario
     */
    public Usuario(int id, String nombreUsuario, String passwordHash, String rol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.passwordHash = passwordHash;
        this.rol = rol;
    }

    /**
     * Obtiene el identificador del usuario.
     *
     * @return identificador
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica el identificador del usuario.
     *
     * @param id nuevo identificador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return nombre de usuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Modifica el nombre de usuario.
     *
     * @param nombreUsuario nuevo nombre de usuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene el hash de la contraseña.
     *
     * @return hash de la contraseña
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Modifica el hash de la contraseña.
     *
     * @param passwordHash nuevo hash de la contraseña
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return rol del usuario
     */
    public String getRol() {
        return rol;
    }

    /**
     * Modifica el rol del usuario.
     *
     * @param rol nuevo rol
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Devuelve los datos del usuario en formato de texto.
     *
     * @return información del usuario, sin incluir el hash de la contraseña
     */
    @Override
    public String toString() {
        return """
                ID: %d
                Nombre: %s
                Rol: %s
                """.formatted(id,nombreUsuario,rol);
    }
}