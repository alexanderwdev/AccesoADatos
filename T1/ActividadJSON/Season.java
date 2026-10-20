package ActividadJSON;

import java.io.Serializable;
import java.util.List;

public class Season implements Serializable {
    private int numero;
    private int episodios;
    private List<SpecialEpisode> episodiosEspeciales;

    public Season(int numero, int episodios, List<SpecialEpisode> episodiosEspeciales) {
        this.numero = numero;
        this.episodios = episodios;
        this.episodiosEspeciales = episodiosEspeciales;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getEpisodios() {
        return episodios;
    }

    public void setEpisodios(int episodios) {
        this.episodios = episodios;
    }

    public List<SpecialEpisode> getEpisodiosEspeciales() {
        return episodiosEspeciales;
    }

    public void setEpisodiosEspeciales(List<SpecialEpisode> episodiosEspeciales) {
        this.episodiosEspeciales = episodiosEspeciales;
    }
}
