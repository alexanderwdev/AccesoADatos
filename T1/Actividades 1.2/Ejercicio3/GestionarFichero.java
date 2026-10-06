package Ejercicio3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

// @author: Alexander Wheatley Neagu

public class GestionarFichero {
    private static final String fichero = "T1/Apartado 1.2/Ejercicio3/productos.dat";
    private static final int longitud_nombre = 40;
    private static final int tam_registro = 97;

    // Escribir producto en la posición actual del fichero
    private void escribirProducto(RandomAccessFile fichero, Producto p) throws IOException {
        fichero.writeInt(p.getCodigo());
        String nombre = p.getNombre();

        if (nombre.length() > longitud_nombre) {
            nombre = nombre.substring(0, longitud_nombre);
        }

        StringBuilder sb = new StringBuilder(nombre);

        while (sb.length() < longitud_nombre) {
            sb.append(' ');
        }

        fichero.writeChars(sb.toString());

        fichero.writeDouble(p.getPrecio());
        fichero.writeInt(p.getCantidad());
        fichero.writeBoolean(p.isActivo());
    }

    // Leer un producto desde la posición actual
    private Producto leerProducto(RandomAccessFile fichero) throws IOException {
        int codigo = fichero.readInt();
        StringBuilder nombre = new StringBuilder();

        for (int i = 0; i < longitud_nombre; i++) {
            nombre.append(fichero.readChar());
        }

        double precio = fichero.readDouble();
        int cantidad = fichero.readInt();
        boolean activo = fichero.readBoolean();

        return new Producto(codigo, nombre.toString(), precio, cantidad, activo);
    }

    // Comprobar si fichero existe
    public boolean existeFichero() {
        return new File(fichero).exists();
    }

    // Número total de productos
    public int numeroProductos() throws IOException {
        File f = new File(fichero);

        if (!f.exists()) {
            return 0;
        }

        return (int) (f.length() / tam_registro);
    }

    // Buscar producto por código (acceso secuencial)
    public Producto buscarPorCodigo(int codigo) throws IOException {
        File archivo = new File(fichero);

        if (!archivo.exists()) {
            return null;
        }

        try (RandomAccessFile raf = new RandomAccessFile(archivo, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                Producto p = leerProducto(raf);

                if (p.getCodigo() == codigo) {
                    return p;
                }
            }
        }

        return null;
    }

    // Añadir producto
    public boolean anadirProducto(Producto prod) throws IOException {
        if (buscarPorCodigo(prod.getCodigo()) != null) {
            return false;
        }

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "rw")) {
            raf.seek(raf.length());
            escribirProducto(raf, prod);
        }

        return true;
    }

    // Mostrar todos los productos
    public void mostrarTodos() throws IOException {
        File archivo = new File(fichero);

        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("No hay productos almacenados.");
            return;
        }

        try (RandomAccessFile raf = new RandomAccessFile(archivo, "r")) {
            int pos = 0;
            while (raf.getFilePointer() < raf.length()) {
                Producto p = leerProducto(raf);

                System.out.println("[" + pos + "] " + p);
                pos++;
            }
        }
    }

    // Consultar producto por posición (acceso directo)
    public Producto consultarPorPosicion(int pos) throws IOException {
        if (pos < 0 || pos >= numeroProductos()) {
            return null;
        }

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "r")) {
            long desplazamiento = (long) pos * tam_registro;
            raf.seek(desplazamiento);
            return leerProducto(raf);
        }
    }

    // Modificar precio (acceso directo)
    public Producto modificarPrecio(int pos, double nuevoPrecio) throws IOException {
        if (pos < 0 || pos >= numeroProductos()) {
            return null;
        }

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "rw")) {
            long desplazamiento = (long) pos * tam_registro;
            raf.seek(desplazamiento);
            Producto p = leerProducto(raf);
            p.setPrecio(nuevoPrecio);

            raf.seek(desplazamiento);
            escribirProducto(raf, p);

            return p;
        }
    }

    // Actualizar stock (acceso directo)
    public Producto actualizarStock(int pos, int nuevaCantidad) throws IOException {
        if (pos < 0 || pos >= numeroProductos()) {
            return null;
        }

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "rw")) {
            long desplazamiento = (long) pos * tam_registro;
            raf.seek(desplazamiento);
            Producto p = leerProducto(raf);
            p.setCantidad(nuevaCantidad);
            raf.seek(desplazamiento);
            escribirProducto(raf, p);
            return p;
        }
    }

    // Eliminación lógica
    public Producto eliminarProducto(int pos) throws IOException {
        if (pos < 0 || pos >= numeroProductos()) {
            return null;
        }

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "rw")) {
            long desplazamiento = (long) pos * tam_registro;
            raf.seek(desplazamiento);
            Producto p = leerProducto(raf);
            p.setActivo(false);
            raf.seek(desplazamiento);
            escribirProducto(raf, p);
            return p;
        }
    }

    // Mostrar solo productos activos
    public void mostrarActivos() throws IOException {
        File archivo = new File(fichero);

        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("No hay productos almacenados.");
            return;
        }

        try (RandomAccessFile raf = new RandomAccessFile(archivo, "r")) {
            boolean encontrado = false;
            int pos = 0;

            while (raf.getFilePointer() < raf.length()) {
                Producto p = leerProducto(raf);

                if (p.isActivo()) {
                    System.out.println("[" + pos + "] " + p);
                    encontrado = true;
                }

                pos++;
            }

            if (!encontrado) {
                System.out.println("No hay productos activos.");
            }
        }
    }

    // Mostrar resumen del inventario
    public void mostrarResumen() throws IOException {
        File archivo = new File(fichero);

        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("No hay productos almacenados.");
            return;
        }

        int totalProductos = 0;
        int productosActivos = 0;
        double valorTotal = 0;

        Producto mayorCantidad = null;

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                Producto p = leerProducto(raf);
                totalProductos++;
                if (p.isActivo()) {
                    productosActivos++;
                    valorTotal += p.getPrecio() * p.getCantidad();

                    if (mayorCantidad == null || p.getCantidad() > mayorCantidad.getCantidad())
                    {
                        mayorCantidad = p;
                    }
                }
            }
        }

        System.out.println("--- Resumen del inventario ---");
        System.out.println("Total de productos: " + totalProductos);
        System.out.println("Productos activos: " + productosActivos);
        System.out.printf("Valor total de existencias: %.2f €%n", valorTotal);

        if (mayorCantidad != null) {
            System.out.println("Producto con mayor cantidad disponible:");
            System.out.println(mayorCantidad);
        } else {
            System.out.println("No hay productos activos.");
        }
    }
}
