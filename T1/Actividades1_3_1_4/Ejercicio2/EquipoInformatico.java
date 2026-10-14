package Actividades1_3_1_4.Ejercicio2;

import java.io.Serial;
import java.io.Serializable;

/**
 * Representa un equipo informático y sus datos.
 *
 * @author Alexander Wheatley
 */
public class EquipoInformatico implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int numInv;
    private String marca;
    private String modelo;
    private String empleado;
    private int memoria;
    private Estado estadoActual;

    /**
     * Estados posibles de un equipo informático.
     */
    public enum Estado {

        OPERATIVO("OPERATIVO"),
        REPARACION("EN REPARACIÓN"),
        RETIRADO("RETIRADO");

        private final String estado;

        /**
         * Crea un estado con su descripción.
         *
         * @param estado descripción del estado
         */
        Estado(String estado) {
            this.estado = estado;
        }

        /**
         * Obtiene la descripción del estado.
         *
         * @return descripción del estado
         */
        public String getEstado() {
            return estado;
        }
    }

    /**
     * Crea un equipo informático.
     *
     * @param numInv número de inventario
     * @param marca marca del equipo
     * @param modelo modelo del equipo
     * @param empleado empleado asignado
     * @param memoria memoria RAM en GB
     * @param estadoInicial estado inicial del equipo
     */
    public EquipoInformatico(int numInv, String marca, String modelo,
                             String empleado, int memoria, Estado estadoInicial) {
        this.numInv = numInv;
        this.marca = marca;
        this.modelo = modelo;
        this.empleado = empleado;
        this.memoria = memoria;
        this.estadoActual = estadoInicial;
    }

    /**
     * Obtiene el número de inventario.
     *
     * @return número de inventario
     */
    public int getNumInv() {
        return numInv;
    }

    /**
     * Modifica el número de inventario.
     *
     * @param numInv nuevo número de inventario
     */
    public void setNumInv(int numInv) {
        this.numInv = numInv;
    }

    /**
     * Obtiene la marca del equipo.
     *
     * @return marca del equipo
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Modifica la marca del equipo.
     *
     * @param marca nueva marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtiene el modelo del equipo.
     *
     * @return modelo del equipo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Modifica el modelo del equipo.
     *
     * @param modelo nuevo modelo
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtiene el empleado asignado.
     *
     * @return empleado asignado
     */
    public String getEmpleado() {
        return empleado;
    }

    /**
     * Modifica el empleado asignado.
     *
     * @param empleado nuevo empleado
     */
    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    /**
     * Obtiene la memoria RAM.
     *
     * @return memoria en GB
     */
    public int getMemoria() {
        return memoria;
    }

    /**
     * Modifica la memoria RAM.
     *
     * @param memoria nueva memoria en GB
     */
    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    /**
     * Obtiene el estado actual del equipo.
     *
     * @return estado actual
     */
    public Estado getEstadoActual() {
        return estadoActual;
    }

    /**
     * Modifica el estado actual del equipo.
     *
     * @param estadoActual nuevo estado
     */
    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    /**
     * Devuelve los datos del equipo en formato de texto.
     *
     * @return datos del equipo
     */
    @Override
    public String toString() {
        return getNumInv() + " - " + getMarca() + " " + getModelo() + " - " + getEmpleado() + " - " + getMemoria() + " - " + getEstadoActual();
    }
}
