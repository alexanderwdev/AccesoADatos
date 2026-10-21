package ActividadJSON;

import java.io.Serializable;
import java.util.List;

/**
 * Representa a un amigo del personaje principal con su clan y habilidades.
 *
 * @author Alexander Wheatley
 */
public class Friend implements Serializable {

    private String nombre;
    private String clan;
    private List<String> habilidades;

    /**
     * Crea un amigo con su nombre, clan y habilidades.
     *
     * @param nombre nombre del amigo
     * @param clan clan al que pertenece
     * @param habilidades lista de habilidades del amigo
     */
    public Friend(String nombre, String clan, List<String> habilidades) {
        this.nombre = nombre;
        this.clan = clan;
        this.habilidades = habilidades;
    }

    /**
     * Obtiene el nombre del amigo.
     *
     * @return nombre del amigo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del amigo.
     *
     * @param nombre nuevo nombre del amigo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el clan del amigo.
     *
     * @return clan del amigo
     */
    public String getClan() {
        return clan;
    }

    /**
     * Modifica el clan del amigo.
     *
     * @param clan nuevo clan del amigo
     */
    public void setClan(String clan) {
        this.clan = clan;
    }

    /**
     * Obtiene las habilidades del amigo.
     *
     * @return lista de habilidades
     */
    public List<String> getHabilidades() {
        return habilidades;
    }

    /**
     * Modifica las habilidades del amigo.
     *
     * @param habilidades nueva lista de habilidades
     */
    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades;
    }
}