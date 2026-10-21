package ActividadJSON;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Representa un anime con su personaje principal, villanos,
 * puntuaciones y temporadas.
 *
 * @author Alexander Wheatley
 */
public class Anime implements Serializable {

    private String nombreAnime;
    private MainCharacter personajePrincipal;
    private int numTotalEp;
    private boolean terminado;
    private List<Villain> villanos;
    private HashMap<String, Double> puntuaciones;
    private List<Season> temporadas;

    /**
     * Crea un anime con todos sus datos.
     *
     * @param nombreAnime nombre del anime
     * @param personajePrincipal personaje principal del anime
     * @param numTotalEp número total de episodios
     * @param terminado indica si el anime ha terminado
     * @param villanos lista de villanos del anime
     * @param puntuaciones puntuaciones del anime en distintas plataformas
     * @param temporadas lista de temporadas del anime
     */
    public Anime(String nombreAnime, MainCharacter personajePrincipal, int numTotalEp,
                 boolean terminado, List<Villain> villanos,
                 HashMap<String, Double> puntuaciones, List<Season> temporadas) {
        this.nombreAnime = nombreAnime;
        this.personajePrincipal = personajePrincipal;
        this.numTotalEp = numTotalEp;
        this.terminado = terminado;
        this.villanos = villanos;
        this.puntuaciones = puntuaciones;
        this.temporadas = temporadas;
    }

    /**
     * Obtiene el nombre del anime.
     *
     * @return nombre del anime
     */
    public String getNombreAnime() {
        return nombreAnime;
    }

    /**
     * Modifica el nombre del anime.
     *
     * @param nombreAnime nuevo nombre del anime
     */
    public void setNombreAnime(String nombreAnime) {
        this.nombreAnime = nombreAnime;
    }

    /**
     * Obtiene el personaje principal.
     *
     * @return personaje principal del anime
     */
    public MainCharacter getPersonajePrincipal() {
        return personajePrincipal;
    }

    /**
     * Modifica el personaje principal.
     *
     * @param personajePrincipal nuevo personaje principal
     */
    public void setPersonajePrincipal(MainCharacter personajePrincipal) {
        this.personajePrincipal = personajePrincipal;
    }

    /**
     * Obtiene el número total de episodios.
     *
     * @return número total de episodios
     */
    public int getNumTotalEp() {
        return numTotalEp;
    }

    /**
     * Modifica el número total de episodios.
     *
     * @param numTotalEp nuevo número de episodios
     */
    public void setNumTotalEp(int numTotalEp) {
        this.numTotalEp = numTotalEp;
    }

    /**
     * Comprueba si el anime ha terminado.
     *
     * @return true si ha terminado, false en caso contrario
     */
    public boolean isTerminado() {
        return terminado;
    }

    /**
     * Modifica el estado de finalización del anime.
     *
     * @param terminado nuevo estado de finalización
     */
    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    /**
     * Obtiene la lista de villanos.
     *
     * @return lista de villanos del anime
     */
    public List<Villain> getVillanos() {
        return villanos;
    }

    /**
     * Modifica la lista de villanos.
     *
     * @param villanos nueva lista de villanos
     */
    public void setVillanos(List<Villain> villanos) {
        this.villanos = villanos;
    }

    /**
     * Obtiene las puntuaciones del anime.
     *
     * @return mapa con las plataformas y sus puntuaciones
     */
    public HashMap<String, Double> getPuntuaciones() {
        return puntuaciones;
    }

    /**
     * Modifica las puntuaciones del anime.
     *
     * @param puntuaciones nuevo mapa de puntuaciones
     */
    public void setPuntuaciones(HashMap<String, Double> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    /**
     * Obtiene la lista de temporadas.
     *
     * @return lista de temporadas del anime
     */
    public List<Season> getTemporadas() {
        return temporadas;
    }

    /**
     * Modifica la lista de temporadas.
     *
     * @param temporadas nueva lista de temporadas
     */
    public void setTemporadas(List<Season> temporadas) {
        this.temporadas = temporadas;
    }
}