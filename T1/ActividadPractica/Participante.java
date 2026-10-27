
package ActividadPractica;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Participante implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private double kmAcumulados;
    private List<String> rutas;
    private transient String observacion;

    public Participante(int id, String nombre) {
        this(id, nombre, 0.0, new ArrayList<>());
    }

    public Participante(int id, String nombre, double kmAcumulados,
                        List<String> rutas) {

        if (id < 0) {
            throw new IllegalArgumentException(
                    "El ID no puede ser negativo.");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre no puede estar vacío.");
        }

        if (!Double.isFinite(kmAcumulados) || kmAcumulados < 0) {
            throw new IllegalArgumentException(
                    "Los kilómetros deben ser un número válido y no negativo.");
        }

        this.id = id;
        this.nombre = nombre.trim();
        this.kmAcumulados = kmAcumulados;
        this.rutas = new ArrayList<>();

        if (rutas != null) {
            for (String ruta : rutas) {
                agregarRuta(ruta);
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getKmAcumulados() {
        return kmAcumulados;
    }

    public List<String> getRutas() {
        if (rutas == null) {
            rutas = new ArrayList<>();
        }

        return Collections.unmodifiableList(rutas);
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void sumarKm(double km) {
        if (!Double.isFinite(km) || km < 0) {
            throw new IllegalArgumentException(
                    "Los kilómetros deben ser un número válido y no negativo.");
        }

        double total = kmAcumulados + km;

        if (!Double.isFinite(total)) {
            throw new IllegalArgumentException(
                    "La suma de kilómetros es demasiado grande.");
        }

        kmAcumulados = total;
    }

    public boolean agregarRuta(String ruta) {
        if (ruta == null || ruta.isBlank()) {
            return false;
        }

        if (rutas == null) {
            rutas = new ArrayList<>();
        }

        String rutaLimpia = ruta.trim();

        boolean existe = rutas.stream()
                .anyMatch(r -> r.equalsIgnoreCase(rutaLimpia));

        if (existe) {
            return false;
        }

        rutas.add(rutaLimpia);
        return true;
    }

    @Override
    public String toString() {
        return "Participante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", kmAcumulados=" +
                String.format(Locale.ROOT, "%.2f", kmAcumulados) +
                ", rutas=" + getRutas() +
                ", observacion='" +
                (observacion == null ? "" : observacion) + '\'' +
                '}';
    }
}
