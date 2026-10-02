package Ejercicio3;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestionarFichero gestionar = new GestionarFichero();

        try (Scanner sc = new Scanner(System.in)) {
            int opcion;

            do {
                mostrarMenu();

                try {
                    System.out.print("Selecciona una opción: ");
                    opcion = sc.nextInt();

                    switch (opcion) {
                        case 1:
                            anadirProducto(sc, gestionar);
                            break;

                        case 2:
                            mostrarTodos(gestionar);
                            break;

                        case 3:
                            buscarProducto(sc, gestionar);
                            break;

                        case 4:
                            consultarPorPosicion(sc, gestionar);
                            break;

                        case 5:
                            modificarPrecio(sc, gestionar);
                            break;

                        case 6:
                            actualizarStock(sc, gestionar);
                            break;

                        case 7:
                            eliminarProducto(sc, gestionar);
                            break;

                        case 8:
                            mostrarActivos(gestionar);
                            break;

                        case 9:
                            mostrarResumen(gestionar);
                            break;

                        case 0:
                            System.out.println("Saliendo del programa...");
                            break;

                        default:
                            System.out.println("Opción no válida.");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Debes introducir un número.");

                    // Limpiar el dato incorrecto
                    sc.nextLine();

                    opcion = -1;

                } catch (IOException e) {
                    System.out.println("Error al trabajar con el fichero: " + e.getMessage());
                    opcion = -1;
                }
                System.out.println();
            } while (opcion != 0);
        }
    }
    // MENÚ
    
    public static void mostrarMenu() {
        System.out.println("""
                --- MENÚ ---
                1. Añadir producto
                2. Mostrar todos los productos
                3. Buscar un producto por su código
                4. Consultar producto por su posición
                5. Modificar precio de un producto
                6. Actualizar stock de un producto
                7. Eliminar producto mediante un id
                8. Mostrar productos activos
                9. Ver resumen de inventario
                0. Salir
                """);
    }

    // OPCIÓN 1 - AÑADIR PRODUCTO

    public static void anadirProducto(Scanner sc, GestionarFichero gestionar) throws IOException {

        System.out.println("--- AÑADIR PRODUCTO ---");

        // Código
        int codigo;

        do {

            System.out.print("Código: ");
            codigo = sc.nextInt();

            if (codigo < 0) {
                System.out.println(
                        "El código no puede ser negativo."
                );
            }

        } while (codigo < 0);

        // Nombre
        sc.nextLine();
        String nombre;

        do {
            System.out.print("Nombre: ");
            nombre = sc.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
            }

        } while (nombre.isEmpty());

        // Precio
        double precio;

        do {
            System.out.print("Precio: ");
            precio = sc.nextDouble();

            if (precio <= 0) {
                System.out.println("El precio debe ser mayor que cero.");
            }

        } while (precio <= 0);

        // Cantidad
        int cantidad;

        do {
            System.out.print("Cantidad disponible: ");
            cantidad = sc.nextInt();

            if (cantidad < 0) {
                System.out.println("La cantidad no puede ser negativa.");
            }

        } while (cantidad < 0);

        // Creamos el producto
        Producto producto = new Producto(codigo, nombre, precio, cantidad, true);

        // Lo guardamos
        if (gestionar.anadirProducto(producto)) {
            System.out.println("Producto añadido correctamente.");

        } else {
            System.out.println("Error: ya existe un producto con ese código.");
        }
    }

    // OPCIÓN 2 - MOSTRAR TODOS

    public static void mostrarTodos(GestionarFichero gestionar) throws IOException {
        System.out.println("--- TODOS LOS PRODUCTOS ---");

        gestionar.mostrarTodos();
    }

    // OPCIÓN 3 - BUSCAR POR CÓDIGO

    public static void buscarProducto(Scanner sc, GestionarFichero gestionar) throws IOException {
        System.out.println("--- BUSCAR PRODUCTO ---");

        int codigo;

        do {

            System.out.print("Introduce el código: ");
            codigo = sc.nextInt();

            if (codigo < 0) {
                System.out.println("El código no puede ser negativo.");
            }

        } while (codigo < 0);

        Producto producto = gestionar.buscarPorCodigo(codigo);

        if (producto != null) {

            System.out.println("Producto encontrado:");
            System.out.println(producto);

        } else {

            System.out.println("No existe ningún producto con ese código.");
        }
    }

    // OPCIÓN 4 - CONSULTAR POR POSICIÓN

    public static void consultarPorPosicion(Scanner sc, GestionarFichero gestionar) throws IOException {
        System.out.println("--- CONSULTAR POR POSICIÓN ---");

        int posicion;

        do {
            System.out.print("Introduce la posición: ");
            posicion = sc.nextInt();

            if (posicion < 0) {
                System.out.println("La posición no puede ser negativa.");
            }

        } while (posicion < 0);

        Producto producto = gestionar.consultarPorPosicion(posicion);

        if (producto != null) {
            System.out.println("Producto encontrado:");
            System.out.println(producto);

        } else {
            System.out.println("No existe ningún producto en esa posición.");
        }
    }

    // OPCIÓN 5 - MODIFICAR PRECIO

    public static void modificarPrecio(Scanner sc, GestionarFichero gestionar) throws IOException {
        System.out.println("--- MODIFICAR PRECIO ---");

        int posicion;

        do {
            System.out.print("Introduce la posición: ");
            posicion = sc.nextInt();

            if (posicion < 0) {
                System.out.println("La posición no puede ser negativa.");
            }

        } while (posicion < 0);

        double nuevoPrecio;

        do {
            System.out.print("Introduce el nuevo precio: ");
            nuevoPrecio = sc.nextDouble();

            if (nuevoPrecio <= 0) {
                System.out.println("El precio debe ser mayor que cero.");
            }

        } while (nuevoPrecio <= 0);

        Producto producto = gestionar.modificarPrecio(posicion, nuevoPrecio);

        if (producto != null) {
            System.out.println("Precio modificado correctamente.");

            System.out.println("Datos actualizados:");

            System.out.println(producto);

        } else {
            System.out.println("No existe ningún producto en esa posición.");
        }
    }

    // OPCIÓN 6 - ACTUALIZAR STOCK

    public static void actualizarStock(Scanner sc, GestionarFichero gestionar) throws IOException {

        System.out.println("--- ACTUALIZAR STOCK ---");

        int posicion;

        do {

            System.out.print("Introduce la posición: ");
            posicion = sc.nextInt();

            if (posicion < 0) {
                System.out.println("La posición no puede ser negativa.");
            }

        } while (posicion < 0);

        int nuevaCantidad;

        do {

            System.out.print("Introduce la nueva cantidad: ");
            nuevaCantidad = sc.nextInt();

            if (nuevaCantidad < 0) {
                System.out.println("La cantidad no puede ser negativa.");
            }

        } while (nuevaCantidad < 0);

        Producto producto = gestionar.actualizarStock(posicion, nuevaCantidad);

        if (producto != null) {
            System.out.println("Stock actualizado correctamente.");

            System.out.println("Datos actualizados:");

            System.out.println(producto);

        } else {

            System.out.println("No existe ningún producto en esa posición.");
        }
    }

    // OPCIÓN 7 - ELIMINAR PRODUCTO

    public static void eliminarProducto(Scanner sc, GestionarFichero gestionar) throws IOException {
        System.out.println("--- ELIMINAR PRODUCTO ---");

        int posicion;

        do {
            System.out.print("Introduce la posición: ");
            posicion = sc.nextInt();

            if (posicion < 0) {
                System.out.println("La posición no puede ser negativa.");
            }

        } while (posicion < 0);

        Producto producto = gestionar.eliminarProducto(posicion);

        if (producto != null) {
            System.out.println("Producto eliminado correctamente.");

            System.out.println("Datos actualizados:");

            System.out.println(producto);

        } else {
            System.out.println("No existe ningún producto en esa posición.");
        }
    }
    
    // OPCIÓN 8 - MOSTRAR ACTIVOS

    public static void mostrarActivos(GestionarFichero gestionar) throws IOException {
        System.out.println("--- PRODUCTOS ACTIVOS ---");
        gestionar.mostrarActivos();
    }

    // OPCIÓN 9 - RESUMEN
    
    public static void mostrarResumen(GestionarFichero gestionar) throws IOException {
        System.out.println("--- RESUMEN DEL INVENTARIO ---");
        gestionar.mostrarResumen();
    }
}