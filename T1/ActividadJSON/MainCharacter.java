package ActividadJSON;

import java.io.Serializable;
import java.util.List;

/**
 * Representa al personaje principal de un anime con sus datos,
 * habilidades y amigos.
 *
 * @author Alexander Wheatley
 */
public class MainCharacter implements Serializable {

    private String nombre;
    private int edad;
    private List<String> abilidades;
    private boolean isGenin;
    private List<Friend> amigos;

    /**
     * Crea un personaje principal con todos sus datos.
     *
     * @param nombre nombre del personaje
     * @param edad edad del personaje
     * @param abilidades lista de habilidades del personaje
     * @param isGenin indica si el personaje es un genin
     * @param amigos lista de amigos del personaje
     */
    public MainCharacter(String nombre, int edad, List<String> abilidades,
                         boolean isGenin, List<Friend> amigos) {
        this.nombre = nombre;
        this.edad = edad;
        this.abilidades = abilidades;
        this.isGenin = isGenin;
        this.amigos = amigos;
    }

    /**
     * Obtiene el nombre del personaje.
     *
     * @return nombre del personaje
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del personaje.
     *
     * @param nombre nuevo nombre del personaje
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la edad del personaje.
     *
     * @return edad del personaje
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Modifica la edad del personaje.
     *
     * @param edad nueva edad del personaje
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene las habilidades del personaje.
     *
     * @return lista de habilidades
     */
    public List<String> getAbilidades() {
        return abilidades;
    }

    /**
     * Modifica las habilidades del personaje.
     *
     * @param abilidades nueva lista de habilidades
     */
    public void setAbilidades(List<String> abilidades) {
        this.abilidades = abilidades;
    }

    /**
     * Comprueba si el personaje es un genin.
     *
     * @return true si es genin, false en caso contrario
     */
    public boolean isGenin() {
        return isGenin;
    }

    /**
     * Modifica el estado de genin del personaje.
     *
     * @param genin nuevo estado de genin
     */
    public void setGenin(boolean genin) {
        isGenin = genin;
    }

    /**
     * Obtiene la lista de amigos del personaje.
     *
     * @return lista de amigos
     */
    public List<Friend> getAmigos() {
        return amigos;
    }

    /**
     * Modifica la lista de amigos del personaje.
     *
     * @param amigos nueva lista de amigos
     */
    public void setAmigos(List<Friend> amigos) {
        this.amigos = amigos;
    }
}