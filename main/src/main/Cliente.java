package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * La clase Cliente representa un usuario cliente en el sistema. Hereda de la
 * clase Usuario e implementa la interfaz Serializable. Permite realizar
 * operaciones específicas para los clientes.
 * 
 * @author Luciano Peluso
 * @author Nahuel Olivera
 *
 */
public class Cliente extends Usuario implements Serializable {

    private int id;
    private String nombre;
    private ArrayList<Proyecto> proyectos;
    private String correoElectronico;
    private String numTelefono;

    /**
     * Crea una nueva instancia de Cliente con el nombre de usuario, contraseña,
     * id e identificador especificados.
     *
     * @param username el nombre de usuario del cliente
     * @param password la contraseña del cliente
     * @param id el id del cliente
     * @param identificador el identificador del cliente
     */
    public Cliente(String username, String password, int id, String identificador) {
        super(username, password, identificador);
        this.id = id;
        proyectos = new ArrayList<>();
        nombre = "";
        correoElectronico = "";
        numTelefono = "";
    }

    /**
     * Establece el id del cliente.
     *
     * @param id el id del cliente
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Completa los datos del cliente, solicitando al usuario su nombre, correo
     * electrónico y número de teléfono.
     */
    public void completarDatos() {
        Scanner scan = new Scanner(System.in);
        while (nombre.isBlank()) {
            System.out.println("Por favor, completa con tu nombre de pila: ");
            nombre = scan.nextLine();
        }
        while (correoElectronico.isBlank()) {
            System.out.println("Correo electronico: ");
            correoElectronico = scan.nextLine();
        }
        while (numTelefono.isBlank()) {
            System.out.println("Numero de telefono: ");
            numTelefono = scan.nextLine();
        }
    }

    /**
     * Muestra las opciones disponibles para el cliente y realiza la acción
     * correspondiente según la opción seleccionada.
     */
    @Override
    public void verOpciones() {
        Scanner scan = new Scanner(System.in);
        String opcion;
        do {
            System.out.println("1 - Ver proyectos");
            System.out.println("2 - Cambiar password");
            System.out.println("3 - Ver mis datos");
            System.out.println("0 - Cerrar sesion");
            System.out.println("Elige una opcion: ");

            opcion = scan.nextLine();
            hacerOpcion(opcion);
        } while ((estaLogueado()));
    }

    /**
     * Realiza la acción correspondiente según la opción seleccionada por el
     * cliente.
     *
     * @param opcion la opción seleccionada por el cliente
     */
    public void hacerOpcion(String opcion) {
        switch (opcion) {
            case "0":
                System.out.println("Cerrando sesion...");
                seDeslogueo();
                break;
            case "1":
                verProyectos();
                break;
            case "2":
                super.cambiarPassword();
                break;
            case "3":
                if (!datosCompletos()) {
                    verDatos();
                } else {
                    verDatosCompletos();
                }
                break;
            default:
                System.out.println("La opcion ingresada no existe.");
                break;
        }
    }

    /**
     * Muestra los proyectos del cliente.
     */
    public void verProyectos() {
        if (!proyectos.isEmpty()) {
            for (Proyecto p : proyectos) {
                System.out.println("\n========================");
                System.out.println("Titulo: " + p.getNombre());
                System.out.println("Fecha de inicio: " + p.getFecha());
                System.out.println("Descripcion: " + p.getDescripcion());
                System.out.println("Presupuesto: " + p.getPresupuesto());
                if (p.getEstado() == false) {
                    if (p.tieneDesarrolladores()) {
                        p.mostrarDesarrolladores();
                    } else {
                        System.out.println("El proyecto no tiene desarrolladores asignados.\n");
                    }
                    System.out.println("Estado: " + p.estaSinFinalizar());
                } else {
                    System.out.println("Estado: " + p.estaFinalizado());
                    System.out.println("Fecha de finalizacion: " + p.getFechaFinal());
                }
            }
            System.out.println("========================\n");
        } else {
            System.out.println("No tienes proyectos!\n");
        }
    }

    /**
     * Muestra los datos incompletos del cliente.
     */
    private void verDatos() {
        Scanner scan = new Scanner(System.in);
        String opcion = "";
        while (!opcion.equals("0") && !opcion.equals("1")) {
            System.out.println("ID: " + id);
            System.out.println("Proyectos activos: " + proyectos.size());
            System.out.println("\n0 - Salir");
            System.out.println("1 - Completar datos");

            opcion = scan.nextLine();
        }
        if (opcion.equals("1")) {
            completarDatos();
        }
    }

    /**
     * Muestra los datos ya completados del cliente.
     */
    private void verDatosCompletos() {
        Scanner scan = new Scanner(System.in);
        String opcion = "";
        while (!opcion.equals("0")) {
            System.out.println("ID: " + id);
            System.out.println("Proyectos activos: " + proyectos.size());
            System.out.println("Nombre: " + nombre);
            System.out.println("Correo electronico: " + correoElectronico);
            System.out.println("Numero de telefono: " + numTelefono);
            System.out.println("\n0 - Salir");
            opcion = scan.nextLine();
        }
    }

    /**
     * Chequea si los datos del cliente estan completos.
     *
     * @return true si el nombre, numero de telefono y el correo electronico no
     * estan vacios, false en caso contrario.
     */
    private boolean datosCompletos() {
        return (!nombre.isBlank() && !numTelefono.isBlank() && !correoElectronico.isBlank());
    }

    /**
     * Obtiene el id del cliente.
     *
     * @return el id del cliente
     */
    public int getID() {
        return id;
    }

    /**
     * Agrega un nuevo proyecto a la lista de proyectos del cliente.
     */
    public void addProyecto() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el nombre del proyecto: ");
        String titulo = scan.nextLine();
        System.out.println("Ingrese la descripcion del proyecto: ");
        String descripcion = scan.nextLine();
        System.out.println("Ingrese el presupuesto estimado para el proyecto: ");
        String presupuesto = scan.nextLine();
        Proyecto proyecto = new Proyecto(titulo, descripcion, presupuesto);
        proyectos.add(proyecto);
        System.out.println("Proyecto añadido con exito.\n");
    }

    /**
     * Verifica si el cliente tiene proyectos.
     *
     * @return true si el cliente tiene proyectos, false en caso contrario.
     */
    public boolean tieneProyectos() {
        return !proyectos.isEmpty();
    }

    /**
     * Muestra los títulos de los proyectos del cliente.
     */
    public void verTituloProyecto() {
        if (tieneProyectos()) {
            for (Proyecto p : proyectos) {
                System.out.println(proyectos.indexOf(p) + ")  Titulo: " + p.getNombre());
                if (p.tieneDesarrolladores()) {
                    p.mostrarDesarrolladores();
                }
            }
        }
    }

    /**
     * Asigna un desarrollador al proyecto especificado por el número de
     * proyecto.
     *
     * @param numeroProyecto el número del proyecto al que se asignará el
     * desarrollador
     * @param dev el desarrollador a asignar al proyecto
     */
    public void setDesarrollador(int numeroProyecto, Desarrollador dev) {
        Proyecto proyectoABuscar = proyectos.get(numeroProyecto);
        proyectoABuscar.setDesarrollador(dev);
    }

    /**
     * Finaliza los proyectos del cliente, solicitando confirmación para cada
     * proyecto.
     */
    public void finalizarProyecto() {
        Scanner scan = new Scanner(System.in);
        String opcion;

        for (Proyecto p : proyectos) {
            if (p.tieneDesarrolladores()) {
                System.out.println(proyectos.indexOf(p) + ")  Titulo: " + p.getNombre());
                System.out.println("Desea dar por finalizado el proyecto? [1]SI  [2]NO");
                opcion = scan.nextLine();
                switch (opcion) {
                    case "1":
                        p.estaFinalizado();
                        p.liberarDesarrolladores();
                        p.setFechaFinal();
                        break;
                    case "2":
                        break;
                    default:
                        System.out.println("La opcion ingresada no existe.");
                        break;
                }
            } else {
                System.out.println(proyectos.indexOf(p) + ")  Titulo: " + p.getNombre());
                System.out.println("Desea dar por finalizado el proyecto? [1]SI  [2]NO");
                opcion = scan.nextLine();
                switch (opcion) {
                    case "1":
                        p.estaFinalizado();
                        p.setFechaFinal();
                        break;
                    case "2":
                        break;
                    default:
                        System.out.println("La opcion ingresada no existe.");
                        break;
                }
            }
        }
    }
}
