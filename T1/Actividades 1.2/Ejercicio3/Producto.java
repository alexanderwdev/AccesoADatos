package Ejercicio3;

// @author: Alexander Wheatley Neagu

public class Producto {

    // Atributos del producto
    private int codigo;
    private String nombre;
    private double precio;
    private int cantidad;
    private boolean activo;

    // Constructor
    public Producto(int codigo, String nombre, double precio, int cantidad, boolean activo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.activo = activo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // Mostrar los datos de un producto
    @Override
    public String toString() {
        return "Código: " + codigo +
                " - Nombre: " + nombre +
                " - Precio: " + String.format("%.2f", precio) + " €" +
                " - Stock: " + cantidad +
                " - Estado: " + (activo ? "ACTIVO" : "ELIMINADO");
    }
}