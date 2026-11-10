package Actividad2.dao;

import Actividad2.config.ConexionBD;
import Actividad2.modelo.Participante;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona las operaciones de acceso a la base de datos relacionadas con los participantes.
 *
 * @author Alexander Wheatley
 */
public class ParticipanteDAO {

    /**
     * Inserta un nuevo participante en la base de datos.
     *
     * @param p participante que se desea insertar
     * @return true si se ha insertado correctamente, false en caso contrario
     */
    public boolean insertarParticipante(Participante p) {

        String sql = "INSERT INTO participantes (nombre, edad, km_acumulados) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getEdad());
            ps.setDouble(3, p.getKmAcumulados());

            return ps.executeUpdate() > 0;

        } catch (SQLException | IOException e) {
            System.out.println("Error al insertar participante: " + e.getMessage());
            return false;
        }
    }

    /**
     * Modifica los datos de un participante existente.
     *
     * @param p participante con los nuevos datos
     * @return true si se ha modificado correctamente, false en caso contrario
     */
    public boolean modificarParticipante(Participante p) {

        String sql = "UPDATE participantes SET nombre = ?, edad = ?, km_acumulados = ? WHERE id = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getEdad());
            ps.setDouble(3, p.getKmAcumulados());
            ps.setInt(4, p.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException | IOException e) {
            System.out.println("Error al modificar participante: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un participante de la base de datos mediante su identificador.
     *
     * @param id identificador del participante que se desea eliminar
     * @return true si se ha eliminado correctamente, false en caso contrario
     */
    public boolean eliminarParticipante(int id) {

        String sql = "DELETE FROM participantes WHERE id = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException | IOException e) {
            System.out.println("Error al eliminar participante: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todos los participantes almacenados en la base de datos.
     *
     * @return lista con todos los participantes
     */
    public List<Participante> obtenerTodos() {

        List<Participante> participantes = new ArrayList<>();

        String sql = "SELECT * FROM participantes";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Participante p = new Participante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getDouble("km_acumulados")
                );

                participantes.add(p);
            }

        } catch (SQLException | IOException e) {
            System.out.println("Error al obtener participantes: " + e.getMessage());
        }

        return participantes;
    }

    /**
     * Busca un participante mediante su identificador.
     *
     * @param id identificador del participante
     * @return participante encontrado o null si no existe
     */
    public Participante buscarPorId(int id) {

        String sql = "SELECT * FROM participantes WHERE id = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Participante(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getInt("edad"),
                            rs.getDouble("km_acumulados")
                    );
                }
            }

        } catch (SQLException | IOException e) {
            System.out.println("Error al buscar participante: " + e.getMessage());
        }

        return null;
    }

    /**
     * Busca participantes cuyo nombre coincida total o parcialmente con el texto indicado.
     *
     * @param nombre nombre o parte del nombre que se desea buscar
     * @return lista de participantes que coinciden con la búsqueda
     */
    public List<Participante> buscarPorNombre(String nombre) {

        List<Participante> participantes = new ArrayList<>();

        String sql = "SELECT * FROM participantes WHERE nombre LIKE ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Participante p = new Participante(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getInt("edad"),
                            rs.getDouble("km_acumulados")
                    );

                    participantes.add(p);
                }
            }

        } catch (SQLException | IOException e) {
            System.out.println("Error al buscar participantes: " + e.getMessage());
        }

        return participantes;
    }
}