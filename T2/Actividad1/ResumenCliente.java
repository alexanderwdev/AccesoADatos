package Actividad1;

/**
 * Representa un resumen de los pedidos y gastos de un cliente.
 *
 * @author Alexander Wheatley
 */
public class ResumenCliente {

    private String nombre;
    private int numeroPedidos;
    private double totalGastado;

    /**
     * Crea un resumen con los datos del cliente.
     *
     * @param nombre nombre del cliente
     * @param numeroPedidos número de pedidos realizados
     * @param totalGastado cantidad total gastada en euros
     */
    public ResumenCliente(String nombre, int numeroPedidos, double totalGastado) {
        this.nombre = nombre;
        this.numeroPedidos = numeroPedidos;
        this.totalGastado = totalGastado;
    }

    /**
     * Devuelve una descripción del resumen del cliente.
     *
     * @return texto con el nombre, número de pedidos y total gastado
     */
    @Override
    public String toString() {
        return "Cliente: " + nombre + " | Pedidos: " + numeroPedidos + " | Total gastado: " + totalGastado + " euros";
    }
}