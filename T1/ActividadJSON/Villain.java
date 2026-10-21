package ActividadJSON;

import java.io.Serializable;

/**
 * Representa a un villano de un anime con su nombre y organización.
 *
 * @author Alexander Wheatley
 */
public class Villain implements Serializable {

    private String nombre;
    private String organizacion;

    /**
     * Crea un villano con su nombre y organización.
     *
     * @param nombre nombre del villano
     * @param organizacion organización a la que pertenece
     */
    public Villain(String nombre, String organizacion) {
        this.nombre = nombre;
        this.organizacion = organizacion;
    }

    /**
     * Obtiene el nombre del villano.
     *
     * @return nombre del villano
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del villano.
     *
     * @param nombre nuevo nombre del villano
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la organización del villano.
     *
     * @return organización del villano
     */
    public String getOrganizacion() {
        return organizacion;
    }

    /**
     * Modifica la organización del villano.
     *
     * @param organizacion nueva organización del villano
     */
    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }
}