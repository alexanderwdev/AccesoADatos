package Actividad1;

import java.time.LocalDate;

/**
 * Representa un pedido con su identificador, fecha, importe y cliente asociado.
 *
 * @author Alexander Wheatley
 */
public class Pedido {

    private int id;
    private LocalDate fecha;
    private double total;
    private int clienteId;

    /**
     * Crea un pedido con todos sus datos.
     *
     * @param id identificador del pedido
     * @param fecha fecha en la que se realizó el pedido
     * @param total importe total del pedido
     * @param clienteId identificador del cliente que realizó el pedido
     */
    public Pedido(int id, LocalDate fecha, double total, int clienteId) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.clienteId = clienteId;
    }

    /**
     * Devuelve una descripción del pedido con todos sus datos.
     *
     * @return texto con la información del pedido
     */
    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", fecha=" + fecha + ", total=" + total + ", clienteId=" + clienteId + '}';
    }
}