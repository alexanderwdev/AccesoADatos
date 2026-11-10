package Actividad2.servicio;

import Actividad2.dao.ExpedicionDAO;
import Actividad2.modelo.Expedicion;

import java.util.List;

public class ExpedicionServicio {
    private final ExpedicionDAO expedicionDAO;

    public ExpedicionServicio() {
        expedicionDAO = new ExpedicionDAO();
    }

    public boolean registrarExpedicion(Expedicion expedicion) {

        if (expedicion == null) {
            System.out.println("La expedición no puede estar vacía.");
            return false;
        }

        if (expedicion.getNombre() == null || expedicion.getNombre().isBlank()
                || expedicion.getDestino() == null || expedicion.getDestino().isBlank()) {
            System.out.println("El nombre y el destino son obligatorios.");
            return false;
        }

        if (expedicion.getFecha() == null || expedicion.getDificultad() == null) {
            System.out.println("La fecha y la dificultad son obligatorias.");
            return false;
        }

        return expedicionDAO.insertarExpedicion(expedicion);
    }

    public boolean modificarExpedicion(Expedicion expedicion) {

        if (expedicion == null || expedicion.getId() <= 0) {
            System.out.println("La expedición no es válida.");
            return false;
        }

        return expedicionDAO.modificarExpedicion(expedicion);
    }

    public boolean eliminarExpedicion(int id) {

        if (id <= 0) {
            System.out.println("El ID debe ser válido.");
            return false;
        }

        return expedicionDAO.eliminarExpedicion(id);
    }

    public List<Expedicion> listarExpediciones() {
        return expedicionDAO.obtenerTodas();
    }

    public Expedicion buscarPorId(int id) {

        if (id <= 0) {
            return null;
        }

        return expedicionDAO.buscarPorId(id);
    }

    public List<Expedicion> buscarPorDestino(String destino) {

        if (destino == null || destino.isBlank()) {
            return List.of();
        }

        return expedicionDAO.buscarPorDestino(destino);
    }

    public boolean finalizarExpedicion(int id) {

        if (id <= 0) {
            System.out.println("El ID debe ser válido.");
            return false;
        }

        return expedicionDAO.finalizarExpedicion(id);
    }
}