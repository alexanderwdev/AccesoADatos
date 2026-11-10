package Actividad2.dao;

import Actividad2.config.ConexionBD;
import Actividad2.modelo.Inscripcion;
import Actividad2.modelo.Participante;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAO {
    public boolean insertarInscripcion(Inscripcion i) {

        String sql = "INSERT INTO inscripciones "
                + "(id_participante, id_expedicion, fecha_inscripcion) "
                + "VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, i.getIdParticipante());
            ps.setInt(2, i.getIdExpedicion());
            ps.setDate(3, Date.valueOf(i.getFechaInscripcion()));

            return ps.executeUpdate() > 0;

        } catch (SQLException | IOException e) {
            System.out.println("Error al registrar inscripción: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarInscripcion(int id) {

        String sql = "DELETE FROM inscripciones WHERE id = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException | IOException e) {
            System.out.println("Error al eliminar inscripción: " + e.getMessage());
            return false;
        }
    }

    public List<Inscripcion> obtenerTodas() {

        List<Inscripcion> inscripciones = new ArrayList<>();

        String sql = "SELECT * FROM inscripciones";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Inscripcion i = new Inscripcion(
                        rs.getInt("id"),
                        rs.getInt("id_participante"),
                        rs.getInt("id_expedicion"),
                        rs.getDate("fecha_inscripcion").toLocalDate()
                );

                inscripciones.add(i);
            }

        } catch (SQLException | IOException e) {
            System.out.println("Error al obtener inscripciones: " + e.getMessage());
        }

        return inscripciones;
    }

    public List<Inscripcion> buscarPorParticipante(int idParticipante) {

        List<Inscripcion> inscripciones = new ArrayList<>();

        String sql = "SELECT * FROM inscripciones WHERE id_participante = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idParticipante);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Inscripcion i = new Inscripcion(
                            rs.getInt("id"),
                            rs.getInt("id_participante"),
                            rs.getInt("id_expedicion"),
                            rs.getDate("fecha_inscripcion").toLocalDate()
                    );

                    inscripciones.add(i);
                }
            }

        } catch (SQLException | IOException e) {
            System.out.println("Error al buscar inscripciones: " + e.getMessage());
        }

        return inscripciones;
    }

    public List<Participante> buscarPorExpedicion(int idExpedicion) {

        List<Participante> participantes = new ArrayList<>();

        String sql = "SELECT p.* FROM participantes p "
                + "INNER JOIN inscripciones i "
                + "ON p.id = i.id_participante "
                + "WHERE i.id_expedicion = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idExpedicion);

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
            System.out.println("Error al buscar participantes de la expedición: "
                    + e.getMessage());
        }

        return participantes;
    }

    public boolean existeInscripcion(int idParticipante, int idExpedicion) {

        String sql = "SELECT 1 FROM inscripciones "
                + "WHERE id_participante = ? AND id_expedicion = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idParticipante);
            ps.setInt(2, idExpedicion);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException | IOException e) {
            System.out.println("Error al comprobar inscripción: " + e.getMessage());
            return false;
        }
    }
}