package Actividad2.dao;

import Actividad2.config.ConexionBD;
import Actividad2.modelo.Expedicion;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona las operaciones de acceso a la base de datos relacionadas con las expediciones.
 *
 * @author Alexander Wheatley
 */
public class ExpedicionDAO {

    /**
     * Inserta una nueva expedición en la base de datos con estado ACTIVA.
     *
     * @param e expedición que se desea insertar
     * @return true si se ha insertado correctamente, false en caso contrario
     */
    public boolean insertarExpedicion(Expedicion e) {

        String sql = "INSERT INTO expediciones "
                + "(nombre, destino, fecha, dificultad, estado, plazas_disponibles) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getDestino());
            ps.setDate(3, Date.valueOf(e.getFecha()));
            ps.setString(4, e.getDificultad());
            ps.setString(5, "ACTIVA");
            ps.setInt(6, e.getPlazasDisponibles());

            return ps.executeUpdate() > 0;

        } catch (SQLException | IOException e1) {
            System.out.println("Error al insertar expedición: " + e1.getMessage());
            return false;
        }
    }

    /**
     * Modifica los datos de una expedición existente.
     *
     * @param e expedición con los nuevos datos
     * @return true si se ha modificado correctamente, false en caso contrario
     */
    public boolean modificarExpedicion(Expedicion e) {

        String sql = "UPDATE expediciones SET nombre = ?, destino = ?, "
                + "fecha = ?, dificultad = ?, plazas_disponibles = ? "
                + "WHERE id = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getDestino());
            ps.setDate(3, Date.valueOf(e.getFecha()));
            ps.setString(4, e.getDificultad());
            ps.setInt(5, e.getPlazasDisponibles());
            ps.setInt(6, e.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException | IOException e1) {
            System.out.println("Error al modificar expedición: " + e1.getMessage());
            return false;
        }
    }

    /**
     * Elimina una expedición de la base de datos.
     *
     * @param id identificador de la expedición que se desea eliminar
     * @return true si se ha eliminado correctamente, false en caso contrario
     */
    public boolean eliminarExpedicion(int id) {

        String sql = "DELETE FROM expediciones WHERE id = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException | IOException e1) {
            System.out.println("Error al eliminar expedición: " + e1.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todas las expediciones almacenadas en la base de datos.
     *
     * @return lista con todas las expediciones
     */
    public List<Expedicion> obtenerTodas() {

        List<Expedicion> expediciones = new ArrayList<>();

        String sql = "SELECT * FROM expediciones";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Expedicion e = new Expedicion(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("destino"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getString("dificultad"),
                        rs.getInt("plazas_disponibles")
                );

                expediciones.add(e);
            }

        } catch (SQLException | IOException e) {
            System.out.println("Error al obtener expediciones: " + e.getMessage());
        }

        return expediciones;
    }

    /**
     * Busca una expedición mediante su identificador.
     *
     * @param id identificador de la expedición
     * @return expedición encontrada o null si no existe
     */
    public Expedicion buscarPorId(int id) {

        String sql = "SELECT * FROM expediciones WHERE id = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Expedicion(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("destino"),
                            rs.getDate("fecha").toLocalDate(),
                            rs.getString("dificultad"),
                            rs.getInt("plazas_disponibles")
                    );
                }
            }

        } catch (SQLException | IOException e) {
            System.out.println("Error al buscar expedición: " + e.getMessage());
        }

        return null;
    }

    /**
     * Busca expediciones cuyo destino coincida total o parcialmente con el texto indicado.
     *
     * @param destino destino que se desea buscar
     * @return lista de expediciones que coinciden con la búsqueda
     */
    public List<Expedicion> buscarPorDestino(String destino) {

        List<Expedicion> expediciones = new ArrayList<>();

        String sql = "SELECT * FROM expediciones WHERE destino LIKE ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, "%" + destino + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Expedicion e = new Expedicion(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("destino"),
                            rs.getDate("fecha").toLocalDate(),
                            rs.getString("dificultad"),
                            rs.getInt("plazas_disponibles")
                    );

                    expediciones.add(e);
                }
            }

        } catch (SQLException | IOException e) {
            System.out.println("Error al buscar expediciones: " + e.getMessage());
        }

        return expediciones;
    }

    /**
     * Cambia el estado de una expedición activa a FINALIZADA.
     *
     * @param id identificador de la expedición que se desea finalizar
     * @return true si se ha finalizado correctamente, false en caso contrario
     */
    public boolean finalizarExpedicion(int id) {

        String sql = "UPDATE expediciones SET estado = 'FINALIZADA' "
                + "WHERE id = ? AND estado = 'ACTIVA'";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException | IOException e) {
            System.out.println("Error al finalizar expedición: " + e.getMessage());
            return false;
        }
    }
}