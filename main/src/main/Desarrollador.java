package main;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La clase Desarrollador representa a un desarrollador en el sistema. Contiene
 * información sobre sus habilidades, nombre y disponibilidad.
 * 
 * 
 * @author Luciano Peluso
 * @author Nahuel Olivera
 */
public class Desarrollador implements Serializable {

    private ArrayList<String> habilidades;
    private String nombre;
    private boolean estaDisponible;

    /**
     * Crea una nueva instancia de la clase Desarrollador con el nombre y las
     * habilidades especificadas.
     *
     * @param nombre el nombre del desarrollador
     * @param habilidades la lista de habilidades del desarrollador
     */
    public Desarrollador(String nombre, ArrayList<String> habilidades) {
        this.nombre = nombre;
        this.habilidades = habilidades;
        estaDisponible = true;
    }

    /**
     * Obtiene el estado de disponibilidad del desarrollador.
     *
     * @return true si el desarrollador está disponible, false en caso contrario
     */
    public boolean getEstaDisponible() {
        return estaDisponible;
    }

    /**
     * Establece el estado de disponibilidad del desarrollador.
     *
     * @param estaDisponible el estado de disponibilidad del desarrollador
     */
    public void setEstaDisponible(boolean estaDisponible) {
        this.estaDisponible = estaDisponible;
    }

    /**
     * Obtiene la lista de habilidades del desarrollador.
     *
     */
    public void getHabilidades() {
        if (habilidades.isEmpty()) {
            System.out.println("- Desarrollador");
        } else {
            for (int i = 0; i < habilidades.size(); i++) {
                System.out.println("- Desarrollador");

                System.out.println("- " + habilidades.get(i));
            }
        }
    }

    /**
     * Obtiene el nombre del desarrollador.
     *
     * @return el nombre del desarrollador
     */
    public String getNombre() {
        return nombre;
    }
}
