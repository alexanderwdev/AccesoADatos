package Actividades1_4_1_5.Ejercicio2;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Representa un pedido con sus datos y productos.
 *
 * @author Alexander Wheatley
 */
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int numeroPedido;
    private String cliente;
    private double importeTotal;
    private List<String> productos;
    private transient String tokenSesion;

    /**
     * Crea un pedido con sus datos.
     *
     * @param numeroPedido número identificador del pedido
     * @param cliente nombre del cliente
     * @param importeTotal importe total del pedido
     * @param productos lista de productos
     * @param tokenSesion token de la sesión
     */
    public Order(int numeroPedido, String cliente, double importeTotal,
                 List<String> productos, String tokenSesion) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.importeTotal = importeTotal;
        this.productos = productos;
        this.tokenSesion = tokenSesion;
    }

    /**
     * Obtiene el número del pedido.
     *
     * @return número del pedido
     */
    public int getNumeroPedido() {
        return numeroPedido;
    }

    /**
     * Modifica el número del pedido.
     *
     * @param numeroPedido nuevo número del pedido
     */
    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return nombre del cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Modifica el nombre del cliente.
     *
     * @param cliente nuevo nombre del cliente
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    /**
     * Obtiene el importe total del pedido.
     *
     * @return importe total
     */
    public double getImporteTotal() {
        return importeTotal;
    }

    /**
     * Modifica el importe total del pedido.
     *
     * @param importeTotal nuevo importe total
     */
    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    /**
     * Obtiene la lista de productos.
     *
     * @return lista de productos
     */
    public List<String> getProductos() {
        return productos;
    }

    /**
     * Modifica la lista de productos.
     *
     * @param productos nueva lista de productos
     */
    public void setProductos(List<String> productos) {
        this.productos = productos;
    }

    /**
     * Obtiene el token de la sesión.
     *
     * @return token de la sesión
     */
    public String getTokenSesion() {
        return tokenSesion;
    }

    /**
     * Modifica el token de la sesión.
     *
     * @param tokenSesion nuevo token de la sesión
     */
    public void setTokenSesion(String tokenSesion) {
        this.tokenSesion = tokenSesion;
    }

    /**
     * Devuelve los datos del pedido en formato de texto.
     *
     * @return información del pedido
     */
    @Override
    public String toString() {
        return """
                Número de Pedido: %d
                Nombre del Cliente: %s
                Importe Total: %.2f€
                Lista de Productos: %s
                Token de la Sesión: %s
                """.formatted(
                numeroPedido,
                cliente,
                importeTotal,
                productos != null ? productos.toString() : "Ninguno",
                tokenSesion != null ? tokenSesion : "No disponible (transient)"
        );
    }
}