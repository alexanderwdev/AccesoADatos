package Actividad3;

import java.time.LocalDate;

/**
 * Representa un pedido realizado por un cliente.
 *
 * @author Alexander Wheatley
 */
public class Pedido {

    private int id;
    private LocalDate fecha;
    private double total;
    private int clienteId;

    /**
     * Crea un pedido con su fecha y total.
     *
     * @param fecha fecha del pedido
     * @param total importe total del pedido
     */
    public Pedido(LocalDate fecha, double total) {
        this.fecha = fecha;
        this.total = total;
    }

    /**
     * Crea un pedido con todos sus datos.
     *
     * @param id identificador del pedido
     * @param fecha fecha del pedido
     * @param total importe total del pedido
     * @param clienteId identificador del cliente
     */
    public Pedido(int id, LocalDate fecha, double total, int clienteId) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.clienteId = clienteId;
    }

    /**
     * Obtiene el identificador del pedido.
     *
     * @return identificador del pedido
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene la fecha del pedido.
     *
     * @return fecha del pedido
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Obtiene el importe total del pedido.
     *
     * @return importe total
     */
    public double getTotal() {
        return total;
    }

    /**
     * Obtiene el identificador del cliente asociado al pedido.
     *
     * @return identificador del cliente
     */
    public int getClienteId() {
        return clienteId;
    }

    /**
     * Modifica el identificador del cliente asociado al pedido.
     *
     * @param clienteId nuevo identificador del cliente
     */
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }
}