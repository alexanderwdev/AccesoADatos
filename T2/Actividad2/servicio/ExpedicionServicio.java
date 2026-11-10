package Actividad2.servicio;

import Actividad2.dao.ExpedicionDAO;
import Actividad2.modelo.Expedicion;

import java.util.List;

/**
 * Gestiona las operaciones y validaciones de las expediciones.
 *
 * @author Alexander Wheatley
 */
public class ExpedicionServicio {
    private final ExpedicionDAO expedicionDAO;

    /**
     * Crea el servicio e inicializa el DAO de expediciones.
     */
    public ExpedicionServicio() {
        expedicionDAO = new ExpedicionDAO();
    }

    /**
     * Valida y registra una nueva expedición.
     *
     * @param expedicion expedición que se quiere registrar
     * @return true si se registra correctamente, false en caso contrario
     */
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

    /**
     * Valida y modifica los datos de una expedición.
     *
     * @param expedicion expedición con los datos actualizados
     * @return true si se modifica correctamente, false en caso contrario
     */
    public boolean modificarExpedicion(Expedicion expedicion) {

        if (expedicion == null || expedicion.getId() <= 0) {
            System.out.println("La expedición no es válida.");
            return false;
        }

        return expedicionDAO.modificarExpedicion(expedicion);
    }

    /**
     * Elimina una expedición según su identificador.
     *
     * @param id identificador de la expedición
     * @return true si se elimina correctamente, false en caso contrario
     */
    public boolean eliminarExpedicion(int id) {

        if (id <= 0) {
            System.out.println("El ID debe ser válido.");
            return false;
        }

        return expedicionDAO.eliminarExpedicion(id);
    }

    /**
     * Obtiene todas las expediciones registradas.
     *
     * @return lista de expediciones
     */
    public List<Expedicion> listarExpediciones() {
        return expedicionDAO.obtenerTodas();
    }

    /**
     * Busca una expedición por su identificador.
     *
     * @param id identificador de la expedición
     * @return expedición encontrada o null si el ID no es válido o no existe
     */
    public Expedicion buscarPorId(int id) {

        if (id <= 0) {
            return null;
        }

        return expedicionDAO.buscarPorId(id);
    }

    /**
     * Busca expediciones por su destino.
     *
     * @param destino destino que se quiere buscar
     * @return lista de expediciones encontradas o lista vacía si el destino no es válido
     */
    public List<Expedicion> buscarPorDestino(String destino) {

        if (destino == null || destino.isBlank()) {
            return List.of();
        }

        return expedicionDAO.buscarPorDestino(destino);
    }

    /**
     * Finaliza una expedición según su identificador.
     *
     * @param id identificador de la expedición
     * @return true si se finaliza correctamente, false en caso contrario
     */
    public boolean finalizarExpedicion(int id) {

        if (id <= 0) {
            System.out.println("El ID debe ser válido.");
            return false;
        }

        return expedicionDAO.finalizarExpedicion(id);
    }
}