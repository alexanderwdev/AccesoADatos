package Ejercicio2;

import java.io.Serial;
import java.io.Serializable;

public class EquipoInformatico implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int numInv;
    private String marca;
    private String modelo;
    private String empleado;
    private int memoria;
    private Estado estadoActual;

    public enum Estado {
        OPERATIVO("OPERATIVO"),
        REPARACION("EN REPARACIÓN"),
        RETIRADO("RETIRADO");

        private final String estado;

        Estado(String estado) {
            this.estado = estado;
        }

        public String getEstado() {
            return estado;
        }
    }

    public EquipoInformatico(int numInv, String marca, String modelo, String empleado, int memoria, Estado estadoInicial) {
        this.numInv = numInv;
        this.marca = marca;
        this.modelo = modelo;
        this.empleado = empleado;
        this.memoria = memoria;
        this.estadoActual = estadoInicial;
    }

    public int getNumInv() {
        return numInv;
    }

    public void setNumInv(int numInv) {
        this.numInv = numInv;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public int getMemoria() {
        return memoria;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    @Override
    public String toString() {
        return getNumInv() + " - " + getMarca() + " " + getModelo() + " - " + getEmpleado() + " - " + getMemoria() + " - " + getEstadoActual();
    }
}