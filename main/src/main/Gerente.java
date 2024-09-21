package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * La clase Gerente representa a un gerente en el sistema. Hereda de la clase
 * Usuario e implementa la interfaz Serializable. Permite realizar operaciones
 * específicas para los gerentes.
 * 
 * 
 * @author Luciano Peluso
 * @author Nahuel Olivera
 */
public class Gerente extends Usuario implements Serializable {

    private ArrayList<Usuario> clientes;

    /**
     * Crea una nueva instancia de Gerente con el nombre de usuario, contraseña,
     * identificador y lista de clientes especificados.
     *
     * @param username el nombre de usuario del gerente
     * @param password la contraseña del gerente
     * @param identificador el identificador del gerente
     * @param clientes la lista de clientes del gerente
     */
    public Gerente(String username, String password, String identificador, ArrayList<Usuario> clientes) {
        super(username, password, identificador);
        this.clientes = clientes;
    }

    /**
     * Muestra las opciones disponibles para el gerente y realiza la acción
     * correspondiente según la opción seleccionada.
     */
    @Override
    public void verOpciones() {
        Scanner scan = new Scanner(System.in);
        String opcion;
        do {
            System.out.println("1 - Administrar clientes.");
            System.out.println("0 - Cerrar sesion.");
            System.out.println("Elige una opcion: ");

            opcion = scan.nextLine();
            hacerOpcion(opcion);
        } while ((estaLogueado()));
    }

    /**
     * Realiza la acción correspondiente según la opción seleccionada por el
     * gerente.
     *
     * @param opcion la opción seleccionada por el gerente
     */
    public void hacerOpcion(String opcion) {
        Scanner scan = new Scanner(System.in);
        String idString = "-1";
        switch (opcion) {
            case "0":
                System.out.println("Cerrando sesion...");
                seDeslogueo();
                break;
            case "1":
                System.out.println("Cargando clientes...");
                for (Usuario u : clientes) {
                    if (u instanceof Cliente) {
                        Cliente cliente = (Cliente) u;
                        System.out.println("[" + cliente.getID() + "]  " + cliente.getUsername());
                    }
                }
                System.out.println("Ingrese un ID de cliente: ");
                opcion = scan.nextLine();
                for (Usuario u : clientes) {
                    if (idString.equalsIgnoreCase("-1")) {
                        if (u instanceof Cliente) {
                            Cliente cliente = (Cliente) u;
                            String num = Integer.toString(cliente.getID());
                            if (opcion.equalsIgnoreCase(num)) {
                                idString = num;
                                System.out.println("\nID encontrado, nombre del cliente: " + cliente.getUsername());
                                if (cliente.tieneProyectos()) {
                                    System.out.println("\n1 - Agregar proyectos.");
                                    System.out.println("2 - Finalizar proyectos.");
                                    opcion = scan.nextLine();
                                    switch (opcion) {
                                        case "1":
                                            cliente.addProyecto();
                                            break;
                                        case "2":
                                            cliente.finalizarProyecto();
                                            break;
                                        default:
                                            System.out.println("La opcion ingresada no existe.");
                                            break;
                                    }
                                } else {
                                    System.out.println("\nDesea añadirle un proyecto al cliente? [1]SI  [2]NO");
                                    opcion = scan.nextLine();
                                    switch (opcion) {
                                        case "1":
                                            cliente.addProyecto();
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
                }
                if (idString.equalsIgnoreCase("-1")) {
                    System.out.println("El ID de cliente ingresado no existe.");
                }
                break;
            default:
                System.out.println("La opcion ingresada no existe.");
                verOpciones();
                break;
        }
    }
}
