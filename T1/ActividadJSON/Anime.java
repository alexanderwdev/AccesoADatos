package ActividadJSON;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Anime implements Serializable {
    private String nombreAnime;
    private MainCharacter personajePrincipal;
    private int numTotalEp;
    private boolean terminado;
    private List<Villain> villanos;
    private HashMap<String, Double> puntuaciones;
    private List<Season> temporadas;

    public Anime(String nombreAnime, MainCharacter personajePrincipal, int numTotalEp, boolean terminado, List<Villain> villanos, HashMap<String, Double> puntuaciones, List<Season> temporadas) {
        this.nombreAnime = nombreAnime;
        this.personajePrincipal = personajePrincipal;
        this.numTotalEp = numTotalEp;
        this.terminado = terminado;
        this.villanos = villanos;
        this.puntuaciones = puntuaciones;
        this.temporadas = temporadas;
    }

    public String getNombreAnime() {
        return nombreAnime;
    }

    public void setNombreAnime(String nombreAnime) {
        this.nombreAnime = nombreAnime;
    }

    public MainCharacter getPersonajePrincipal() {
        return personajePrincipal;
    }

    public void setPersonajePrincipal(MainCharacter personajePrincipal) {
        this.personajePrincipal = personajePrincipal;
    }

    public int getNumTotalEp() {
        return numTotalEp;
    }

    public void setNumTotalEp(int numTotalEp) {
        this.numTotalEp = numTotalEp;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public List<Villain> getVillanos() {
        return villanos;
    }

    public void setVillanos(List<Villain> villanos) {
        this.villanos = villanos;
    }

    public HashMap<String, Double> getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(HashMap<String, Double> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    public List<Season> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<Season> temporadas) {
        this.temporadas = temporadas;
    }
}
