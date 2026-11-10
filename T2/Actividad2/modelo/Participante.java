package Actividad2.modelo;

public class Participante {
    private int id;
    private String nombre;
    private int edad;
    private double kmAcumulados;

    public Participante(int id, String nombre, int edad, double kmAcumulados) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.kmAcumulados = kmAcumulados;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getKmAcumulados() {
        return kmAcumulados;
    }

    public void setKmAcumulados(double kmAcumulados) {
        this.kmAcumulados = kmAcumulados;
    }

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
