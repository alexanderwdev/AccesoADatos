package ActividadJSON;

import java.io.Serializable;

public class SpecialEpisode implements Serializable {
    private String titulo;
    private int longitud;

    public SpecialEpisode(String titulo, int longitud) {
        this.titulo = titulo;
        this.longitud = longitud;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
}
