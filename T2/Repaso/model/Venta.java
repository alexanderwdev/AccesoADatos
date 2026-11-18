package Repaso.model;

public class Venta {
    private int id;
    private int unidades;
    private double precio_unitario;
    private int articulo_id;

    public Venta(int id, int unidades, double precio_unitario, int articulo_id) {
        this.id = id;
        this.unidades = unidades;
        this.precio_unitario = precio_unitario;
        this.articulo_id = articulo_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public int getArticulo_id() {
        return articulo_id;
    }

    public void setArticulo_id(int articulo_id) {
        this.articulo_id = articulo_id;
    }
}
