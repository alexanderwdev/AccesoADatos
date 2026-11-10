package Actividad2.modelo;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String passwordHash;
    private String rol;

    public Usuario(int id, String nombreUsuario, String passwordHash, String rol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.passwordHash = passwordHash;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return """
                ID: %d
                Nombre: %s
                Rol: %s
                """.formatted(id,nombreUsuario,rol);
    }
}
