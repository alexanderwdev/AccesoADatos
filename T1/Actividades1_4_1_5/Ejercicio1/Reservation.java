package Actividades1_4_1_5.Ejercicio1;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Representa una reserva de un recurso.
 *
 * @author Alexander Wheatley
 */
public class Reservation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private String solicitante;
    private String recurso;
    private int duracion;
    private List<String> participantes;
    private transient String codigoAcceso;

    /**
     * Crea una reserva con sus datos.
     *
     * @param id identificador de la reserva
     * @param solicitante persona que realiza la reserva
     * @param recurso recurso reservado
     * @param duracion duración de la reserva en minutos
     * @param participantes lista de participantes
     * @param codigoAcceso código de acceso a la reserva
     */
    public Reservation(int id, String solicitante, String recurso, int duracion,
                       List<String> participantes, String codigoAcceso) {
        this.id = id;
        this.solicitante = solicitante;
        this.recurso = recurso;
        this.duracion = duracion;
        this.participantes = participantes;
        this.codigoAcceso = codigoAcceso;
    }

    /**
     * Obtiene el identificador de la reserva.
     *
     * @return identificador de la reserva
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica el identificador de la reserva.
     *
     * @param id nuevo identificador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el solicitante de la reserva.
     *
     * @return nombre del solicitante
     */
    public String getSolicitante() {
        return solicitante;
    }

    /**
     * Modifica el solicitante de la reserva.
     *
     * @param solicitante nuevo solicitante
     */
    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    /**
     * Obtiene el recurso reservado.
     *
     * @return recurso reservado
     */
    public String getRecurso() {
        return recurso;
    }

    /**
     * Modifica el recurso reservado.
     *
     * @param recurso nuevo recurso
     */
    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    /**
     * Obtiene la duración de la reserva.
     *
     * @return duración en minutos
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Modifica la duración de la reserva.
     *
     * @param duracion nueva duración en minutos
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * Obtiene la lista de participantes.
     *
     * @return lista de participantes
     */
    public List<String> getParticipantes() {
        return participantes;
    }

    /**
     * Modifica la lista de participantes.
     *
     * @param participantes nueva lista de participantes
     */
    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }

    /**
     * Obtiene el código de acceso.
     *
     * @return código de acceso
     */
    public String getCodigoAcceso() {
        return codigoAcceso;
    }

    /**
     * Modifica el código de acceso.
     *
     * @param codigoAcceso nuevo código de acceso
     */
    public void setCodigoAcceso(String codigoAcceso) {
        this.codigoAcceso = codigoAcceso;
    }

    /**
     * Devuelve los datos de la reserva en formato de texto.
     *
     * @return información de la reserva
     */
    @Override
    public String toString() {
        return """
                ID: %d
                Solicitante: %s
                Recurso: %s
                Duración: %d min
                Participantes: %s
                Código de acceso: %s
                """.formatted(
                id,
                solicitante,
                recurso,
                duracion,
                participantes != null ? participantes.toString() : "Ninguno",
                codigoAcceso != null ? codigoAcceso : "No disponible (transient)"
        );
    }
}