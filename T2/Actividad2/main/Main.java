package Actividad2.main;

import Actividad2.modelo.Usuario;
import Actividad2.modelo.Participante;
import Actividad2.modelo.Expedicion;
import Actividad2.modelo.Inscripcion;

import Actividad2.dao.ParticipanteDAO;
import Actividad2.dao.InscripcionDAO;

import Actividad2.servicio.AutenticacionServicio;
import Actividad2.servicio.ExpedicionServicio;

import Actividad2.util.MenuUtil;
import Actividad2.config.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    private static final AutenticacionServicio autenticacion = new AutenticacionServicio();
    private static final ExpedicionServicio expedicionServicio = new ExpedicionServicio();
    private static final ParticipanteDAO participanteDAO = new ParticipanteDAO();
    private static final InscripcionDAO inscripcionDAO = new InscripcionDAO();

    public static void main(String[] args) {
        System.out.println("EXPEDICIONES");

        try (Connection conexion = ConexionBD.getConexion()) {
            System.out.println("Conexión con MySQL establecida.");
        } catch (SQLException | IOException e) {
            System.out.println("No se ha podido conectar con la base de datos.");
            System.out.println(e.getMessage());
            return;
        }

        Usuario usuario = iniciarSesion();

        if (usuario == null) {
            return;
        }

        boolean salir = false;

        while (!salir) {

            MenuUtil.mostrarMenuPrincipal();

            int opcion = MenuUtil.pedirOpcion();

            switch (opcion) {

                case 1:
                    menuParticipantes();
                    break;

                case 2:
                    menuExpediciones();
                    break;

                case 3:
                    menuInscripciones();
                    break;

                case 4:
                    listarParticipantes();
                    break;

                case 5:
                    listarExpediciones();
                    break;

                case 6:
                    System.out.println("Cerrando sesión...");
                    usuario = iniciarSesion();

                    if (usuario == null) {
                        salir = true;
                    }
                    break;

                case 0:
                    System.out.println("Hasta luego.");
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static Usuario iniciarSesion() {

        Usuario usuario = null;

        System.out.println("\n========== INICIO DE SESIÓN ==========");

        while (usuario == null) {

            String nombre = MenuUtil.pedirTexto("Usuario: ");
            String password = MenuUtil.pedirTexto("Contraseña: ");

            usuario = autenticacion.iniciarSesion(nombre, password);
        }

        System.out.println("Bienvenido, " + usuario.getNombreUsuario());

        return usuario;
    }

    private static void menuParticipantes() {

        int opcion;

        do {

            MenuUtil.mostrarMenuParticipantes();
            opcion = MenuUtil.pedirOpcion();

            switch (opcion) {

                case 1:
                    anadirParticipante();
                    break;

                case 2:
                    modificarParticipante();
                    break;

                case 3:
                    eliminarParticipante();
                    break;

                case 4:
                    buscarParticipante();
                    break;

                case 5:
                    listarParticipantes();
                    break;

                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private static void anadirParticipante() {
        System.out.println("\n--- AÑADIR PARTICIPANTE ---");

        String nombre = MenuUtil.pedirTexto("Nombre: ");
        int edad = MenuUtil.pedirEntero("Edad: ");
        double km = MenuUtil.pedirDecimal("Kilómetros acumulados: ");

        if (edad <= 0 || km < 0) {
            System.out.println("La edad debe ser positiva y los kilómetros no pueden ser negativos.");
            return;
        }

        Participante participante = new Participante(0, nombre, edad, km);

        if (participanteDAO.insertarParticipante(participante)) {
            System.out.println("Participante añadido correctamente.");
        } else {
            System.out.println("No se pudo añadir el participante.");
        }
    }

    private static void modificarParticipante() {
        System.out.println("\n--- MODIFICAR PARTICIPANTE ---");

        int id = MenuUtil.pedirEntero("ID del participante: ");

        Participante participante = participanteDAO.buscarPorId(id);

        if (participante == null) {
            System.out.println("No existe ese participante.");
            return;
        }

        String nombre = MenuUtil.pedirTexto("Nuevo nombre: ");
        int edad = MenuUtil.pedirEntero("Nueva edad: ");
        double km = MenuUtil.pedirDecimal("Nuevos kilómetros acumulados: ");

        if (edad <= 0 || km < 0) {
            System.out.println("Datos no válidos.");
            return;
        }

        participante.setNombre(nombre);
        participante.setEdad(edad);
        participante.setKmAcumulados(km);

        if (participanteDAO.modificarParticipante(participante)) {
            System.out.println("Participante modificado correctamente.");
        } else {
            System.out.println("No se pudo modificar el participante.");
        }
    }

    private static void eliminarParticipante() {

        System.out.println("\n--- ELIMINAR PARTICIPANTE ---");

        int id = MenuUtil.pedirEntero("ID del participante: ");

        if (participanteDAO.eliminarParticipante(id)) {
            System.out.println("Participante eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar. Comprueba que existe y que no tiene inscripciones.");
        }
    }

    private static void buscarParticipante() {

        System.out.println("\n--- BUSCAR PARTICIPANTE ---");

        String nombre = MenuUtil.pedirTexto("Nombre que quieres buscar: ");

        List<Participante> participantes =
                participanteDAO.buscarPorNombre(nombre);

        if (participantes.isEmpty()) {
            System.out.println("No se encontraron participantes.");
        } else {
            for (Participante p : participantes) {
                System.out.println(p);
            }
        }
    }

    private static void listarParticipantes() {

        System.out.println("\n--- LISTADO DE PARTICIPANTES ---");

        List<Participante> participantes =
                participanteDAO.obtenerTodos();

        if (participantes.isEmpty()) {
            System.out.println("No hay participantes registrados.");
        } else {
            for (Participante p : participantes) {
                System.out.println(p);
            }
        }
    }

    private static void menuExpediciones() {
        int opcion;

        do {
            MenuUtil.mostrarMenuExpediciones();
            opcion = MenuUtil.pedirOpcion();

            switch (opcion) {

                case 1:
                    anadirExpedicion();
                    break;

                case 2:
                    modificarExpedicion();
                    break;

                case 3:
                    eliminarExpedicion();
                    break;

                case 4:
                    buscarExpedicion();
                    break;

                case 5:
                    listarExpediciones();
                    break;

                case 6:
                    finalizarExpedicion();
                    break;

                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private static void anadirExpedicion() {
        System.out.println("\n--- AÑADIR EXPEDICIÓN ---");

        String nombre = MenuUtil.pedirTexto("Nombre: ");
        String destino = MenuUtil.pedirTexto("Destino: ");
        LocalDate fecha = pedirFecha();
        String dificultad = MenuUtil.pedirTexto("Dificultad (FACIL, MEDIA, DIFICIL): ");
        int plazas = MenuUtil.pedirEntero("Plazas disponibles: ");

        if (plazas < 0) {
            System.out.println("El número de plazas no puede ser negativo.");
            return;
        }

        Expedicion expedicion = new Expedicion(0, nombre, destino, fecha, dificultad, plazas);

        if (expedicionServicio.registrarExpedicion(expedicion)) {
            System.out.println("Expedición añadida correctamente.");
        } else {
            System.out.println("No se pudo añadir la expedición.");
        }
    }

    private static void modificarExpedicion() {
        System.out.println("\n--- MODIFICAR EXPEDICIÓN ---");

        int id = MenuUtil.pedirEntero("ID de la expedición: ");

        Expedicion expedicion = expedicionServicio.buscarPorId(id);

        if (expedicion == null) {
            System.out.println("No existe esa expedición.");
            return;
        }

        String nombre = MenuUtil.pedirTexto("Nuevo nombre: ");
        String destino = MenuUtil.pedirTexto("Nuevo destino: ");
        LocalDate fecha = pedirFecha();
        String dificultad = MenuUtil.pedirTexto("Nueva dificultad (FACIL, MEDIA, DIFICIL): ");
        int plazas = MenuUtil.pedirEntero("Nuevas plazas disponibles: ");

        if (plazas < 0) {
            System.out.println("El número de plazas no puede ser negativo.");
            return;
        }

        expedicion.setNombre(nombre);
        expedicion.setDestino(destino);
        expedicion.setFecha(fecha);
        expedicion.setDificultad(dificultad);
        expedicion.setPlazasDisponibles(plazas);

        if (expedicionServicio.modificarExpedicion(expedicion)) {
            System.out.println("Expedición modificada correctamente.");
        } else {
            System.out.println("No se pudo modificar la expedición.");
        }
    }

    private static void eliminarExpedicion() {
        System.out.println("\n--- ELIMINAR EXPEDICIÓN ---");

        int id = MenuUtil.pedirEntero("ID de la expedición: ");

        if (expedicionServicio.eliminarExpedicion(id)) {
            System.out.println("Expedición eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar. Comprueba que existe y que no tiene inscripciones.");
        }
    }

    private static void buscarExpedicion() {
        System.out.println("\n--- BUSCAR EXPEDICIÓN ---");

        String destino = MenuUtil.pedirTexto("Destino que quieres buscar: ");

        List<Expedicion> expediciones =
                expedicionServicio.buscarPorDestino(destino);

        if (expediciones.isEmpty()) {
            System.out.println("No se encontraron expediciones.");
        } else {
            for (Expedicion e : expediciones) {
                System.out.println(e);
            }
        }
    }

    private static void listarExpediciones() {
        System.out.println("\n--- LISTADO DE EXPEDICIONES ---");

        List<Expedicion> expediciones = expedicionServicio.listarExpediciones();

        if (expediciones.isEmpty()) {
            System.out.println("No hay expediciones registradas.");
        } else {
            for (Expedicion e : expediciones) {
                System.out.println(e);
            }
        }
    }

    private static void finalizarExpedicion() {
        System.out.println("\n--- FINALIZAR EXPEDICIÓN ---");

        int id = MenuUtil.pedirEntero("ID de la expedición: ");

        if (expedicionServicio.finalizarExpedicion(id)) {
            System.out.println("Expedición finalizada correctamente.");
        } else {
            System.out.println("No se pudo finalizar la expedición.");
        }
    }

    private static void menuInscripciones() {
        int opcion;

        do {
            MenuUtil.mostrarMenuInscripciones();
            opcion = MenuUtil.pedirOpcion();

            switch (opcion) {

                case 1:
                    anadirInscripcion();
                    break;

                case 2:
                    cancelarInscripcion();
                    break;

                case 3:
                    verInscripcionesParticipante();
                    break;

                case 4:
                    verParticipantesExpedicion();
                    break;

                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private static void anadirInscripcion() {
        System.out.println("\n--- NUEVA INSCRIPCIÓN ---");

        int idParticipante = MenuUtil.pedirEntero("ID del participante: ");

        int idExpedicion = MenuUtil.pedirEntero("ID de la expedición: ");

        if (inscripcionDAO.existeInscripcion(idParticipante, idExpedicion)) {
            System.out.println("El participante ya está inscrito en esta expedición.");
            return;
        }

        Inscripcion inscripcion = new Inscripcion(
                0,
                idParticipante,
                idExpedicion,
                LocalDate.now()
        );

        if (inscripcionDAO.insertarInscripcion(inscripcion)) {
            System.out.println("Inscripción realizada correctamente.");
        } else {
            System.out.println("No se pudo realizar la inscripción.");
        }
    }

    private static void cancelarInscripcion() {
        System.out.println("\n--- CANCELAR INSCRIPCIÓN ---");

        int id = MenuUtil.pedirEntero("ID de la inscripción: ");

        if (inscripcionDAO.eliminarInscripcion(id)) {
            System.out.println("Inscripción cancelada correctamente.");
        } else {
            System.out.println("No se pudo cancelar la inscripción.");
        }
    }

    private static void verInscripcionesParticipante() {
        System.out.println("\n--- INSCRIPCIONES DE UN PARTICIPANTE ---");

        int id = MenuUtil.pedirEntero("ID del participante: ");

        List<Inscripcion> inscripciones =
                inscripcionDAO.buscarPorParticipante(id);

        if (inscripciones.isEmpty()) {
            System.out.println("Este participante no tiene inscripciones.");
        } else {
            for (Inscripcion i : inscripciones) {
                System.out.println(i);
            }
        }
    }

    private static void verParticipantesExpedicion() {
        System.out.println("\n--- PARTICIPANTES DE UNA EXPEDICIÓN ---");

        int id = MenuUtil.pedirEntero("ID de la expedición: ");

        List<Participante> participantes =
                inscripcionDAO.buscarPorExpedicion(id);

        if (participantes.isEmpty()) {
            System.out.println("No hay participantes inscritos.");
        } else {
            for (Participante p : participantes) {
                System.out.println(p);
            }
        }
    }

    private static LocalDate pedirFecha() {

        while (true) {
            String fechaTexto =
                    MenuUtil.pedirTexto("Fecha (AAAA-MM-DD): ");

            try {
                return LocalDate.parse(fechaTexto);
            } catch (Exception e) {
                System.out.println("Formato incorrecto. Ejemplo: 2026-12-25");
            }
        }
    }
}