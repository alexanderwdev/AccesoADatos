package ActividadJSON;

import java.io.Serializable;
import java.util.List;

public class MainCharacter implements Serializable {
    private String nombre;
    private int edad;
    private List<String> abilidades;
    private boolean isGenin;
    private List<Friend> amigos;

    public MainCharacter(String nombre, int edad, List<String> abilidades, boolean isGenin, List<Friend> amigos) {
        this.nombre = nombre;
        this.edad = edad;
        this.abilidades = abilidades;
        this.isGenin = isGenin;
        this.amigos = amigos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<String> getAbilidades() {
        return abilidades;
    }

    public void setAbilidades(List<String> abilidades) {
        this.abilidades = abilidades;
    }

    public boolean isGenin() {
        return isGenin;
    }

    public void setGenin(boolean genin) {
        isGenin = genin;
    }

    public List<Friend> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Friend> amigos) {
        this.amigos = amigos;
    }
}
