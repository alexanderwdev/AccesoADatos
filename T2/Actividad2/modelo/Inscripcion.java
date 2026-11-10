package Actividad2.modelo;

import java.time.LocalDate;

/**
 * Representa la inscripción de un participante en una expedición.
 *
 * @author Alexander Wheatley
 */
public class Inscripcion {
    private int id;
    private int idParticipante;
    private int idExpedicion;
    private LocalDate fechaInscripcion;

    /**
     * Crea una inscripción con sus datos.
     *
     * @param id identificador de la inscripción
     * @param idParticipante identificador del participante
     * @param idExpedicion identificador de la expedición
     * @param fechaInscripcion fecha en la que se realiza la inscripción
     */
    public Inscripcion(int id, int idParticipante, int idExpedicion, LocalDate fechaInscripcion) {
        this.id = id;
        this.idParticipante = idParticipante;
        this.idExpedicion = idExpedicion;
        this.fechaInscripcion = fechaInscripcion;
    }

    /**
     * Obtiene el identificador de la inscripción.
     *
     * @return identificador de la inscripción
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica el identificador de la inscripción.
     *
     * @param id nuevo identificador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador del participante.
     *
     * @return identificador del participante
     */
    public int getIdParticipante() {
        return idParticipante;
    }

    /**
     * Modifica el identificador del participante.
     *
     * @param idParticipante nuevo identificador del participante
     */
    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    /**
     * Obtiene el identificador de la expedición.
     *
     * @return identificador de la expedición
     */
    public int getIdExpedicion() {
        return idExpedicion;
    }

    /**
     * Modifica el identificador de la expedición.
     *
     * @param idExpedicion nuevo identificador de la expedición
     */
    public void setIdExpedicion(int idExpedicion) {
        this.idExpedicion = idExpedicion;
    }

    /**
     * Obtiene la fecha de inscripción.
     *
     * @return fecha de inscripción
     */
    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    /**
     * Modifica la fecha de inscripción.
     *
     * @param fechaInscripcion nueva fecha de inscripción
     */
    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    /**
     * Devuelve los datos de la inscripción en formato de texto.
     *
     * @return información de la inscripción
     */
    @Override
    public String toString() {
        return """
                ID de Inscripción: %d
                ID de Participante: %d
                ID de Expedicion: %d
                Fecha de inscripción: %s
                """.formatted(id,idParticipante,idExpedicion,fechaInscripcion);
    }
}