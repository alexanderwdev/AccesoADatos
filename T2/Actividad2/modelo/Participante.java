package Actividad2.modelo;

/**
 * Representa a un participante con sus datos y kilómetros acumulados.
 *
 * @author Alexander Wheatley
 */
public class Participante {
    private int id;
    private String nombre;
    private int edad;
    private double kmAcumulados;

    /**
     * Crea un participante con sus datos.
     *
     * @param id identificador del participante
     * @param nombre nombre del participante
     * @param edad edad del participante
     * @param kmAcumulados kilómetros acumulados
     */
    public Participante(int id, String nombre, int edad, double kmAcumulados) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.kmAcumulados = kmAcumulados;
    }

    /**
     * Obtiene el identificador del participante.
     *
     * @return identificador
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica el identificador del participante.
     *
     * @param id nuevo identificador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del participante.
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del participante.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la edad del participante.
     *
     * @return edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Modifica la edad del participante.
     *
     * @param edad nueva edad
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene los kilómetros acumulados.
     *
     * @return kilómetros acumulados
     */
    public double getKmAcumulados() {
        return kmAcumulados;
    }

    /**
     * Modifica los kilómetros acumulados.
     *
     * @param kmAcumulados nuevos kilómetros acumulados
     */
    public void setKmAcumulados(double kmAcumulados) {
        this.kmAcumulados = kmAcumulados;
    }

    /**
     * Devuelve los datos del participante en formato de texto.
     *
     * @return información del participante
     */
    @Override
    public String toString() {
        return """
                ID: %d
                Nombre: %s
                Edad: %d
                Kilómetros acumulados: %.2f
                """.formatted(id,nombre,edad,kmAcumulados);
    }
}