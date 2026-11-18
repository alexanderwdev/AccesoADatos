package Repaso.main;

import Repaso.dao.ArticuloDAO;
import Repaso.dao.VentaDAO;
import Repaso.model.Articulo;
import Repaso.model.ArticuloCatalogo;
import Repaso.model.ResumenVenta;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Repaso.cfg.ConexionBD;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArticuloDAO articuloDAO = new ArticuloDAO();
        VentaDAO ventaDAO = new VentaDAO();

        int idArticulo;

        // =========================================
        // 1. PREPARACIÓN DE LOS DATOS
        // =========================================

        System.out.println("\n========== EJERCICIO 1 ==========");
        System.out.println("Preparación de los datos");
        System.out.println("Las tablas y los datos iniciales se crean en MySQL.");

        // =========================================
        // 2. CONFIGURACIÓN DE LA CONEXIÓN
        // =========================================

        System.out.println("\n========== EJERCICIO 2 ==========");
        System.out.println("Comprobando conexión...");

        try (Connection c = ConexionBD.getConexion()) {
            System.out.println("Conexión realizada correctamente.");
        } catch (SQLException | IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            sc.close();
            return;
        }

        // =========================================
        // 3. INCORPORACIÓN DE UN ARTÍCULO
        // =========================================

        System.out.println("\n========== EJERCICIO 3 ==========");
        System.out.println("Introducir un nuevo artículo");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Precio: ");
        double precio = sc.nextDouble();

        System.out.print("Stock: ");
        int stock = sc.nextInt();

        System.out.print("ID de categoría: ");
        int categoriaId = sc.nextInt();
        sc.nextLine();

        Articulo nuevo = new Articulo(
                0, nombre, precio, stock, categoriaId
        );

        int idGenerado = articuloDAO.insertarArticulo(nuevo);

        if (idGenerado > 0) {
            System.out.println("Artículo insertado. ID generado: " + idGenerado);
        }

        // =========================================
        // 4. CONSULTA DEL CATÁLOGO
        // =========================================

        System.out.println("\n========== EJERCICIO 4 ==========");
        System.out.println("Buscar artículos");

        System.out.print("Texto que contiene el nombre: ");
        String texto = sc.nextLine();

        System.out.print("Precio máximo: ");
        double precioMaximo = sc.nextDouble();
        sc.nextLine();

        List<ArticuloCatalogo> catalogo =
                articuloDAO.buscarArticulos(texto, precioMaximo);

        if (catalogo.isEmpty()) {
            System.out.println("No se han encontrado artículos.");
        } else {
            System.out.println("Artículos encontrados:");

            for (ArticuloCatalogo a : catalogo) {
                System.out.println(a);
            }
        }

        // =========================================
        // 5. REGISTRO DE UNA VENTA
        // =========================================

        System.out.println("\n========== EJERCICIO 5 ==========");
        System.out.println("Registrar una venta");

        System.out.print("ID del artículo: ");
        idArticulo = sc.nextInt();

        System.out.print("Unidades que desea comprar: ");
        int unidades = sc.nextInt();
        sc.nextLine();

        ventaDAO.insertarVenta(idArticulo, unidades);

        // =========================================
        // 6. RETIRADA DE ARTÍCULOS
        // =========================================

        System.out.println("\n========== EJERCICIO 6 ==========");
        System.out.println("Eliminar un artículo");

        System.out.print("ID del artículo que desea eliminar: ");
        int idEliminar = sc.nextInt();
        sc.nextLine();

        String resultado = articuloDAO.eliminarArticulo(idEliminar);

        System.out.println(resultado);

        // =========================================
        // 7. RESUMEN DE VENTAS
        // =========================================

        System.out.println("\n========== EJERCICIO 7 ==========");
        System.out.println("Resumen de ventas");

        List<ResumenVenta> resumen =
                ventaDAO.obtenerResumenVentas();

        if (resumen.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            for (ResumenVenta r : resumen) {
                System.out.println(r);
            }
        }

        // =========================================
        // 8. CONSULTA MEDIANTE PROCEDIMIENTO
        // =========================================

        System.out.println("\n========== EJERCICIO 8 ==========");
        System.out.println("Consultar artículos por categoría");

        System.out.print("ID de categoría: ");
        int idCategoria = sc.nextInt();
        sc.nextLine();

        List<Articulo> articulosCategoria =
                articuloDAO.obtenerArticulosCategoria(idCategoria);

        if (articulosCategoria.isEmpty()) {
            System.out.println("No hay artículos disponibles en esa categoría.");
        } else {
            for (Articulo a : articulosCategoria) {
                System.out.println(a);
            }
        }

        // =========================================
        // 9. PROCEDIMIENTO CON PARÁMETROS OUT
        // =========================================

        System.out.println("\n========== EJERCICIO 9 ==========");
        System.out.println("Resumen de una categoría");

        System.out.print("ID de categoría: ");
        int idCategoriaResumen = sc.nextInt();
        sc.nextLine();

        articuloDAO.obtenerResumenCategoria(idCategoriaResumen);

        // =========================================
        // 10. FUNCIÓN ALMACENADA
        // =========================================

        System.out.println("\n========== EJERCICIO 10 ==========");
        System.out.println("Calcular valor de existencias");

        System.out.print("ID del artículo: ");
        int idArticuloValor = sc.nextInt();
        sc.nextLine();

        double valor =
                articuloDAO.calcularValorExistencias(idArticuloValor);

        System.out.println("Valor económico de las existencias: " + valor);

        // =========================================
        // 11. INCORPORACIÓN DE ARTÍCULOS POR LOTES
        // =========================================

        System.out.println("\n========== EJERCICIO 11 ==========");
        System.out.println("Prueba de inserción por lotes");

        // PRUEBA CORRECTA

        List<Articulo> correctos = new ArrayList<>();

        correctos.add(new Articulo(
                0, "Tablet Lenovo", 180.00, 5, 1
        ));

        correctos.add(new Articulo(
                0, "Auriculares Sony", 45.50, 10, 1
        ));

        correctos.add(new Articulo(
                0, "Libro Java", 22.00, 8, 2
        ));

        System.out.println("\nPrueba de carga correcta:");

        boolean cargaCorrecta =
                articuloDAO.insertarArticulosLote(correctos);

        System.out.println("Resultado de la carga: " + cargaCorrecta);

        // PRUEBA INCORRECTA

        List<Articulo> incorrectos = new ArrayList<>();

        incorrectos.add(new Articulo(
                0, "Ratón Logitech", 20.00, 5, 1
        ));

        incorrectos.add(new Articulo(
                0, "", 15.00, 3, 1
        ));

        incorrectos.add(new Articulo(
                0, "Libro SQL", 18.00, 4, 2
        ));

        System.out.println("\nPrueba de carga incorrecta:");

        boolean cargaIncorrecta =
                articuloDAO.insertarArticulosLote(incorrectos);

        System.out.println("Resultado de la carga: " + cargaIncorrecta);

        // =========================================
        // 12. EJECUCIÓN DE DDL DESDE JAVA
        // =========================================

        System.out.println("\n========== EJERCICIO 12 ==========");
        System.out.println("Crear, modificar y eliminar tabla auxiliar");

        try (Connection c = ConexionBD.getConexion();
             Statement st = c.createStatement()) {

            // Crear tabla
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS prueba_jdbc ("
                            + "id INT PRIMARY KEY AUTO_INCREMENT, "
                            + "descripcion VARCHAR(100) NOT NULL)"
            );

            System.out.println("Tabla prueba_jdbc creada.");

            // Añadir columna
            st.executeUpdate(
                    "ALTER TABLE prueba_jdbc "
                            + "ADD COLUMN observaciones VARCHAR(200)"
            );

            System.out.println("Columna observaciones añadida.");

            // Eliminar tabla
            st.executeUpdate(
                    "DROP TABLE prueba_jdbc"
            );

            System.out.println("Tabla prueba_jdbc eliminada.");

        } catch (SQLException | IOException e) {
            System.out.println("Error al ejecutar DDL: " + e.getMessage());
        }

        // =========================================
        // FIN DEL PROGRAMA
        // =========================================

        System.out.println("\n========== FIN DEL EJERCICIO ==========");
        System.out.println("Todas las pruebas han terminado.");

        sc.close();
    }
}
