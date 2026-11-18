package Repaso.model;

public class ResumenVenta {
    private String categoria;
    private int totalVendido;
    private double importeTotal;

    public ResumenVenta(String categoria, int totalVendido, double importeTotal) {
        this.categoria = categoria;
        this.totalVendido = totalVendido;
        this.importeTotal = importeTotal;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(int totalVendido) {
        this.totalVendido = totalVendido;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    @Override
    public String toString() {
        return "Categoría: " + categoria
                + " | Unidades vendidas: " + totalVendido
                + " | Importe total: " + importeTotal + " €";
    }
}
