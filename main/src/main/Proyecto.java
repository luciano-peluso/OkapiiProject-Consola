package main;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * La clase Proyecto representa un proyecto del cliente en el sistema.
 * Implementa la interfaz Serializable.
 * 
 * 
 * @author Luciano Peluso
 * @author Nahuel Olivera
 */
class Proyecto implements Serializable {

    private String estado;
    private String presupuesto;
    private boolean estaFinalizado;
    private String fecha;
    private String fechaFinal;
    private String nombre;
    private String descripcion;
    private ArrayList<Desarrollador> desarrolladores;

    /**
     * Crea una nueva instancia de Proyecto con el nombre, descripción y
     * presupuesto especificados.
     *
     * @param nombre el nombre del proyecto
     * @param descripcion la descripción del proyecto
     * @param presupuesto el presupuesto del proyecto
     */
    public Proyecto(String nombre, String descripcion, String presupuesto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.presupuesto = presupuesto;
        estaFinalizado = false;
        fecha = asignarFecha();
        fechaFinal = "";
        desarrolladores = new ArrayList<>();
    }

    /**
     * Asigna la fecha actual al proyecto.
     *
     * @return la fecha en formato de cadena de texto
     */
    private String asignarFecha() {
        LocalDate fecha = LocalDate.now();
        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return fecha.format(formatObj);
    }

    /**
     * Establece la fecha de finalización del proyecto como la fecha actual.
     *
     * @return la fecha de finalización en formato de cadena de texto
     */
    public void setFechaFinal() {
        fechaFinal = asignarFecha();
    }

    /**
     * Obtiene el estado del proyecto.
     *
     * @return true si el proyecto está finalizado, false si no lo está
     */
    public boolean getEstado() {
        return estaFinalizado;
    }

    /**
     * Establece el estado del proyecto.
     *
     * @param estado el estado del proyecto
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Establece el estado del proyecto como "Sin finalizar" y marca el proyecto
     * como no finalizado.
     *
     * @return el estado del proyecto
     */
    public String estaSinFinalizar() {
        setEstado("Sin finalizar");
        estaFinalizado = false;
        return estado;
    }

    /**
     * Establece el estado del proyecto como "Finalizado" y marca el proyecto
     * como finalizado.
     *
     * @return el estado del proyecto
     */
    public String estaFinalizado() {
        setEstado("Finalizado");
        estaFinalizado = true;
        return estado;
    }

    /**
     * Obtiene el nombre del proyecto.
     *
     * @return el nombre del proyecto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la descripción del proyecto.
     *
     * @return la descripción del proyecto
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene el presupuesto del proyecto.
     *
     * @return el presupuesto del proyecto
     */
    public String getPresupuesto() {
        return presupuesto;
    }

    /**
     * Obtiene la fecha del proyecto.
     *
     * @return la fecha del proyecto
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Verifica si el proyecto tiene desarrolladores asignados.
     *
     * @return true si el proyecto tiene desarrolladores asignados, false si no
     * los tiene
     */
    public boolean tieneDesarrolladores() {
        return !desarrolladores.isEmpty();
    }

    /**
     * Asigna un desarrollador al proyecto. Marca al desarrollador como no
     * disponible.
     *
     * @param dev el desarrollador a asignar al proyecto
     */
    public void setDesarrollador(Desarrollador dev) {
        dev.setEstaDisponible(false);
        desarrolladores.add(dev);
    }

    /**
     * Libera a los desarrolladores asignados al proyecto, marcándolos como
     * disponibles.
     */
    public void liberarDesarrolladores() {
        for (Desarrollador dev : desarrolladores) {
            dev.setEstaDisponible(true);
        }
        desarrolladores.clear();
    }

    /**
     * Muestra los desarrolladores asignados al proyecto.
     */
    public void mostrarDesarrolladores() {
        System.out.println("Los desarrolladores a cargo del proyecto son: ");
        for (Desarrollador dev : desarrolladores) {
            System.out.println(desarrolladores.indexOf(dev) + ") " + dev.getNombre());
        }
    }
    
    
    
    /**
     * @return la fecha de finalizacion del proyecto.
     */
    public String getFechaFinal() {
        return fechaFinal;
    }

}
