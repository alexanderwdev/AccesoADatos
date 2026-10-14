package Actividades1_4_1_5.Ejercicio1;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Reservation implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private String solicitante;
    private String recurso;
    private int duracion;
    private List<String> participantes;
    private transient String codigoAcceso;


    public Reservation(int id, String solicitante, String recurso, int duracion, List<String> participantes, String codigoAcceso) {
        this.id = id;
        this.solicitante = solicitante;
        this.recurso = recurso;
        this.duracion = duracion;
        this.participantes = participantes;
        this.codigoAcceso = codigoAcceso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public List<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }

    public String getCodigoAcceso() {
        return codigoAcceso;
    }

    public void setCodigoAcceso(String codigoAcceso) {
        this.codigoAcceso = codigoAcceso;
    }

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