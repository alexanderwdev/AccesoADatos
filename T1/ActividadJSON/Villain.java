package ActividadJSON;

import java.io.Serializable;

public class Villain implements Serializable {
    private String nombre;
    private String organizacion;

    public Villain(String nombre, String organizacion) {
        this.nombre = nombre;
        this.organizacion = organizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }
}
