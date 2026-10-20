package ActividadJSON;

import java.io.Serializable;
import java.util.List;

public class Friend implements Serializable {
    private String nombre;
    private String clan;
    private List<String> habilidades;

    public Friend(String nombre, String clan, List<String> habilidades) {
        this.nombre = nombre;
        this.clan = clan;
        this.habilidades = habilidades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades;
    }
}
