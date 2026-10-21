package ActividadJSON;

import java.io.Serializable;

/**
 * Representa un episodio especial de un anime con su título y duración.
 *
 * @author Alexander Wheatley
 */
public class SpecialEpisode implements Serializable {

    private String titulo;
    private int longitud;

    /**
     * Crea un episodio especial con su título y duración.
     *
     * @param titulo título del episodio especial
     * @param longitud duración del episodio
     */
    public SpecialEpisode(String titulo, int longitud) {
        this.titulo = titulo;
        this.longitud = longitud;
    }

    /**
     * Obtiene el título del episodio.
     *
     * @return título del episodio
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Modifica el título del episodio.
     *
     * @param titulo nuevo título del episodio
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene la duración del episodio.
     *
     * @return duración del episodio
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Modifica la duración del episodio.
     *
     * @param longitud nueva duración del episodio
     */
    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
}