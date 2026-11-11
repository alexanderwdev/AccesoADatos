package Actividad3;

/**
 * Representa a un cliente con su identificador, nombre y correo electrónico.
 *
 * @author Alexander Wheatley
 */
public class Cliente {

    private int id;
    private String nombre;
    private String email;

    /**
     * Crea un cliente con todos sus datos.
     *
     * @param id     identificador del cliente
     * @param nombre nombre del cliente
     * @param email  correo electrónico del cliente
     */
    public Cliente(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    /**
     * Crea un cliente indicando su nombre y correo electrónico.
     *
     * @param nombre nombre del cliente
     * @param email  correo electrónico del cliente
     */
    public Cliente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    /**
     * Obtiene el identificador del cliente.
     *
     * @return identificador del cliente
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica el identificador del cliente.
     *
     * @param id nuevo identificador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     *
     * @return correo electrónico del cliente
     */
    public String getEmail() {
        return email;
    }
}