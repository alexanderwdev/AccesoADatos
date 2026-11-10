package Actividad2.modelo;

import java.time.LocalDate;

public class Expedicion {

    private int id;
    private String nombre;
    private String destino;
    private LocalDate fecha;
    private String dificultad;
    private String estado;
    private int plazasDisponibles;

    public Expedicion(int id, String nombre, String destino,
                      LocalDate fecha, String dificultad,
                      int plazasDisponibles) {

        this.id = id;
        this.nombre = nombre;
        this.destino = destino;
        this.fecha = fecha;
        this.dificultad = dificultad;
        this.estado = "ACTIVA";
        this.plazasDisponibles = plazasDisponibles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    public void setPlazasDisponibles(int plazasDisponibles) {
        this.plazasDisponibles = plazasDisponibles;
    }

    @Override
    public String toString() {
        return """
                ID: %d
                Nombre: %s
                Destino: %s
                Fecha: %s
                Dificultad: %s
                Estado: %s
                Plazas disponibles: %d
                """.formatted(id, nombre, destino, fecha,
                dificultad, estado, plazasDisponibles);
    }
}