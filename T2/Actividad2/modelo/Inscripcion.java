package Actividad2.modelo;

import java.time.LocalDate;

public class Inscripcion {
    private int id;
    private int idParticipante;
    private int idExpedicion;
    private LocalDate fechaInscripcion;

    public Inscripcion(int id, int idParticipante, int idExpedicion, LocalDate fechaInscripcion) {
        this.id = id;
        this.idParticipante = idParticipante;
        this.idExpedicion = idExpedicion;
        this.fechaInscripcion = fechaInscripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public int getIdExpedicion() {
        return idExpedicion;
    }

    public void setIdExpedicion(int idExpedicion) {
        this.idExpedicion = idExpedicion;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

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
