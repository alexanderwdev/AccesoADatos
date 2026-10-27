package ActividadPractica;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Participante implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private double kmAcumulados;
    private List<String> rutas;

    private transient String observacion;

    public Participante(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.kmAcumulados = 0;
        this.rutas = new ArrayList<>();
        this.observacion = "";
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

    public double getKmAcumulados() {
        return kmAcumulados;
    }

    public void setKmAcumulados(double kmAcumulados) {
        this.kmAcumulados = kmAcumulados;
    }

    public List<String> getRutas() {
        return rutas;
    }

    public void setRutas(List<String> rutas) {
        this.rutas = rutas;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean agregarRutas(String ruta) {
        if (ruta == null || ruta.isBlank()) {
            System.out.println("Ruta inválida");
            return false;
        }

        ruta = ruta.trim();

        for (String r : rutas) {
            if (r.equalsIgnoreCase(ruta)) {
                System.out.println("Esta ruta ya existe.");
                return false;
            }
        }

        rutas.add(ruta);
        System.out.println("Ruta añadida correctamente");
        return true;
    }

    public void sumarKm(double km) {
        if (Double.isNaN(km) || km < 0) throw new InputMismatchException("Número de kilómetros inválido.");

        kmAcumulados+=km;
        System.out.println("Kilómetros sumados correctamente");
    }

    @Override
    public String toString() {
        return """
                ID: %d
                Nombre: %s
                Kilómetros acumulados: %.2f
                Rutas: %s"""
                .formatted(
                        id,
                        nombre,
                        kmAcumulados,
                        rutas
                );
    }
}
