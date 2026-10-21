package ActividadJSON;

import java.io.Serializable;
import java.util.List;

/**
 * Representa una temporada de un anime con su número,
 * cantidad de episodios y episodios especiales.
 *
 * @author Alexander Wheatley
 */
public class Season implements Serializable {

    private int numero;
    private int episodios;
    private List<SpecialEpisode> episodiosEspeciales;

    /**
     * Crea una temporada con sus datos.
     *
     * @param numero número de la temporada
     * @param episodios cantidad de episodios de la temporada
     * @param episodiosEspeciales lista de episodios especiales
     */
    public Season(int numero, int episodios, List<SpecialEpisode> episodiosEspeciales) {
        this.numero = numero;
        this.episodios = episodios;
        this.episodiosEspeciales = episodiosEspeciales;
    }

    /**
     * Obtiene el número de la temporada.
     *
     * @return número de la temporada
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Modifica el número de la temporada.
     *
     * @param numero nuevo número de la temporada
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Obtiene la cantidad de episodios de la temporada.
     *
     * @return cantidad de episodios
     */
    public int getEpisodios() {
        return episodios;
    }

    /**
     * Modifica la cantidad de episodios de la temporada.
     *
     * @param episodios nueva cantidad de episodios
     */
    public void setEpisodios(int episodios) {
        this.episodios = episodios;
    }

    /**
     * Obtiene la lista de episodios especiales.
     *
     * @return lista de episodios especiales
     */
    public List<SpecialEpisode> getEpisodiosEspeciales() {
        return episodiosEspeciales;
    }

    /**
     * Modifica la lista de episodios especiales.
     *
     * @param episodiosEspeciales nueva lista de episodios especiales
     */
    public void setEpisodiosEspeciales(List<SpecialEpisode> episodiosEspeciales) {
        this.episodiosEspeciales = episodiosEspeciales;
    }
}