package Actividad2.dao;

import Actividad2.config.ConexionBD;
import Actividad2.modelo.Participante;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipanteDAO {
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