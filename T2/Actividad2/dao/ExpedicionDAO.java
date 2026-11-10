package Actividad2.dao;

import Actividad2.config.ConexionBD;
import Actividad2.modelo.Expedicion;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpedicionDAO {

    // 1. Insertar expedición
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

    // 2. Modificar expedición
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

    // 3. Eliminar expedición
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

    // 4. Obtener todas las expediciones
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

    // 5. Buscar expedición por ID
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

    // 6. Buscar expediciones por destino
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

    // 7. Finalizar expedición
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
