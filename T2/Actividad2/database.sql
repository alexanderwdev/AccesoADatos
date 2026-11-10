-- ==========================================
-- EXPEDICIONES ADVENTURE
-- Script de creación de la base de datos
-- MySQL 8.0
-- ==========================================

CREATE DATABASE IF NOT EXISTS expediciones;

USE expediciones;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    rol ENUM('ADMIN', 'USUARIO') NOT NULL DEFAULT 'USUARIO'
    );

CREATE TABLE IF NOT EXISTS participantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    edad INT NOT NULL,
    km_acumulados DOUBLE NOT NULL DEFAULT 0,

    CONSTRAINT chk_edad CHECK (edad > 0),
    CONSTRAINT chk_km CHECK (km_acumulados >= 0)
    );

CREATE TABLE IF NOT EXISTS expediciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    destino VARCHAR(100) NOT NULL,
    fecha DATE NOT NULL,
    dificultad ENUM('FACIL', 'MEDIA', 'DIFICIL') NOT NULL,
    estado ENUM('ACTIVA', 'FINALIZADA') NOT NULL DEFAULT 'ACTIVA',
    plazas_disponibles INT NOT NULL,

    CONSTRAINT chk_plazas CHECK (plazas_disponibles >= 0)
    );

CREATE TABLE IF NOT EXISTS inscripciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_participante INT NOT NULL,
    id_expedicion INT NOT NULL,
    fecha_inscripcion DATE NOT NULL,

    CONSTRAINT fk_inscripcion_participante
    FOREIGN KEY (id_participante)

    REFERENCES participantes(id)
    ON DELETE RESTRICT,

    CONSTRAINT fk_inscripcion_expedicion
    FOREIGN KEY (id_expedicion)
    REFERENCES expediciones(id)
    ON DELETE RESTRICT,

    CONSTRAINT uq_inscripcion
    UNIQUE (id_participante, id_expedicion)
    );


CREATE INDEX idx_participante_nombre
    ON participantes(nombre);

CREATE INDEX idx_expedicion_destino
    ON expediciones(destino);

CREATE INDEX idx_expedicion_estado
    ON expediciones(estado);

CREATE INDEX idx_inscripcion_expedicion
    ON inscripciones(id_expedicion);

DELIMITER //

CREATE PROCEDURE sp_finalizar_expedicion(
    IN p_id_expedicion INT
)
BEGIN

UPDATE expediciones
SET estado = 'FINALIZADA'
WHERE id = p_id_expedicion
  AND estado = 'ACTIVA';

END //

DELIMITER ;