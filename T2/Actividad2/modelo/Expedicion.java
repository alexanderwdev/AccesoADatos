package Actividad2.modelo;

import java.time.LocalDate;

/**
 * Representa una expedición con sus datos y estado.
 *
 * @author Alexander Wheatley
 */
public class Expedicion {

    private int id;
    private String nombre;
    private String destino;
    private LocalDate fecha;
    private String dificultad;
    private String estado;
    private int plazasDisponibles;

    /**
     * Crea una expedición y la establece como activa.
     *
     * @param id identificador de la expedición
     * @param nombre nombre de la expedición
     * @param destino destino de la expedición
     * @param fecha fecha de la expedición
     * @param dificultad nivel de dificultad
     * @param plazasDisponibles número de plazas disponibles
     */
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

    /**
     * Obtiene el identificador de la expedición.
     *
     * @return identificador
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica el identificador de la expedición.
     *
     * @param id nuevo identificador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la expedición.
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de la expedición.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el destino de la expedición.
     *
     * @return destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Modifica el destino de la expedición.
     *
     * @param destino nuevo destino
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * Obtiene la fecha de la expedición.
     *
     * @return fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Modifica la fecha de la expedición.
     *
     * @param fecha nueva fecha
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la dificultad de la expedición.
     *
     * @return dificultad
     */
    public String getDificultad() {
        return dificultad;
    }

    /**
     * Modifica la dificultad de la expedición.
     *
     * @param dificultad nueva dificultad
     */
    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    /**
     * Obtiene el estado de la expedición.
     *
     * @return estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Modifica el estado de la expedición.
     *
     * @param estado nuevo estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el número de plazas disponibles.
     *
     * @return plazas disponibles
     */
    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    /**
     * Modifica el número de plazas disponibles.
     *
     * @param plazasDisponibles nuevo número de plazas
     */
    public void setPlazasDisponibles(int plazasDisponibles) {
        this.plazasDisponibles = plazasDisponibles;
    }

    /**
     * Devuelve los datos de la expedición en formato de texto.
     *
     * @return información de la expedición
     */
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