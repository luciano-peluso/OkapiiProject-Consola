package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que representa a un administrador.
 * 
 * 
 * @author Luciano Peluso
 * @author Nahuel Olivera
 */
public class Administrador extends Usuario implements Serializable {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Desarrollador> devs;

    /**
     * Constructor de la clase Administrador.
     *
     * @param username El nombre de usuario del administrador.
     * @param password La contraseña del administrador.
     * @param usuarios La lista de usuarios existentes.
     * @param devs La lista de desarrolladores disponibles.
     * @param identificador El identificador del administrador.
     */
    public Administrador(String username, String password, ArrayList usuarios, ArrayList devs, String identificador) {
        super(username, password, identificador);
        this.usuarios = usuarios;
        this.devs = devs;
    }

    /**
     * Muestra las opciones disponibles para el administrador.
     */
    public void verOpciones() {
        Scanner scan = new Scanner(System.in);
        String opcion;
        do {
            System.out.println("\n1 - Registrar cliente.");
            System.out.println("2 - Registrar gerente.");
            System.out.println("3 - Registrar desarrollador.");
            System.out.println("4 - Ver desarrolladores disponibles.");
            System.out.println("5 - Asignar desarrolladores.");
            System.out.println("6 - Ver todos los usuarios.");
            System.out.println("0 - Cerrar sesion.");
            System.out.println("Elige una opcion: ");
            opcion = scan.nextLine();
            hacerOpcion(opcion);
        } while ((estaLogueado()));
    }

    /**
     * Ejecuta la opción seleccionada por el administrador.
     *
     * @param opcion La opción seleccionada.
     */
    public void hacerOpcion(String opcion) {
        switch (opcion) {
            case "0":
                super.seDeslogueo();
                break;
            case "1":
                registrarUsuarioCliente();
                break;
            case "2":
                registrarUsuarioGerente();
                break;
            case "3":
                registrarUsuarioDesarrollador();
                break;
            case "4":
                if (!devs.isEmpty()) {
                    if (hayDesarrolladoresDisponibles()) {
                        mostrarDesarrolladoresDisponibles();
                    } else {
                        System.out.println("No hay desarrolladores disponibles.");
                    }
                } else {
                    System.out.println("No hay desarrolladores cargados en el sistema.");
                }
                break;
            case "5":
                if (!devs.isEmpty()) {
                    asignarDesarrolladores();
                } else {
                    System.out.println("No hay desarrolladores cargados en el sistema.");
                }
                break;
            case "6":
                verUsuarios();
                break;
            default:
                System.out.println("La opcion ingresada no existe.\n");
                verOpciones();
                break;
        }
    }

    /**
     * Muestra todos los usuarios registrados.
     */
    private void verUsuarios() {
        for (Usuario u : usuarios) {
            System.out.println("\n=========================");
            System.out.println("\nNombre: " + u.getUsername());
            System.out.println("Clave: " + u.getPassword());
            System.out.println("Tipo: " + u.getIdentificador());
        }
        System.out.println("\n=========================\n");
    }

    /**
     * Verifica si un usuario ya existe en la lista de usuarios.
     *
     * @param user El nombre de usuario a verificar.
     * @return true si el usuario está disponible, false en caso contrario.
     */
    public boolean chequearUsuario(String user) {
        boolean estaDisponible = true;
        Usuario userAComparar;
        if (!usuarios.isEmpty()) {
            int i = 0;
            do {
                userAComparar = usuarios.get(i);
                i++;
            } while (i < usuarios.size() && !userAComparar.sonIgualesUsers(user));

            if (userAComparar.sonIgualesUsers(user)) {
                estaDisponible = false;
            }
        }
        return estaDisponible;
    }

    /**
     * Busca un usuario en la lista de usuarios.
     *
     * @param username El nombre de usuario a buscar.
     * @return El usuario encontrado o null si no se encontró.
     */
    public Usuario buscarUsuario(String username) {
        Usuario userBuscado = null;
        int i = 0;
        if (!usuarios.isEmpty()) {
            do {
                userBuscado = usuarios.get(i);
                i++;
            } while (i < usuarios.size() && !userBuscado.sonIgualesUsers(username));

            if (!(i < usuarios.size()) && !userBuscado.sonIgualesUsers(username)) {
                userBuscado = null;
            }
        }
        return userBuscado;
    }

    /**
     * Crea un nuevo usuario de tipo Cliente y lo agrega a la lista de usuarios.
     *
     * @param user El nombre de usuario del cliente.
     * @param password La contraseña del cliente.
     */
    public void crearUsuarioCliente(String user, String password) {
        Cliente usuarioEntrante = new Cliente(user, password, usuarios.size() + 1, "Cliente");
        if (usuarios.add(usuarioEntrante)) {
            System.out.println("\nUsuario creado con exito.\n");
        }
    }

    /**
     * Crea un nuevo usuario de tipo Gerente y lo agrega a la lista de usuarios.
     *
     * @param user El nombre de usuario del gerente.
     * @param password La contraseña del gerente.
     */
    public void crearUsuarioGerente(String user, String password) {
        Gerente usuarioEntrante = new Gerente(user, password, "Gerente", usuarios);
        if (usuarios.add(usuarioEntrante)) {
            System.out.println("\nUsuario creado con exito.\n");
        }
    }

    /**
     * Registra un nuevo usuario de tipo Cliente.
     */
    public void registrarUsuarioCliente() {
        String user;
        String password;
        Scanner scan = new Scanner(System.in);

        System.out.println("\nIngrese un nombre de usuario: ");
        user = scan.nextLine();
        while (user.isBlank()) {
            System.out.println("El campo de nombre de usuario no puede estar vacio, escriba su nombre de nuevo a continuacion: ");
            user = scan.nextLine();
        }

        System.out.println("Ingrese una clave: ");
        password = scan.nextLine();
        while (password.isBlank()) {
            System.out.println("El campo de la clave no puede estar vacio, escriba su clave de nuevo a continuacion: ");
            password = scan.nextLine();
        }

        if (chequearUsuario(user)) {
            crearUsuarioCliente(user, password);
        } else {
            System.out.println("Ese usuario ya existe!");
            System.out.println("Volviendo al menu principal...\n");
        }
    }

    /**
     * Registra un nuevo usuario de tipo Gerente.
     */
    private void registrarUsuarioGerente() {
        String user;
        String password;
        Scanner scan = new Scanner(System.in);

        System.out.println("\nIngrese un nombre de usuario: ");
        user = scan.nextLine();
        while (user.isBlank()) {
            System.out.println("El campo de nombre de usuario no puede estar vacio, escriba su nombre de nuevo a continuacion: ");
            user = scan.nextLine();
        }

        System.out.println("Ingrese una clave: ");
        password = scan.nextLine();
        while (password.isBlank()) {
            System.out.println("El campo de la clave no puede estar vacio, escriba su clave de nuevo a continuacion: ");
            password = scan.nextLine();
        }

        if (chequearUsuario(user)) {
            crearUsuarioGerente(user, password);
        } else {
            System.out.println("Ese usuario ya existe!");
            System.out.println("Volviendo al menu principal...\n");
        }
    }

    /**
     * Registra un nuevo usuario de tipo Desarrollador.
     */
    private void registrarUsuarioDesarrollador() {
        String user;
        String habilidad = "";
        ArrayList<String> habilidades = new ArrayList<>();

        Scanner scan = new Scanner(System.in);

        System.out.println("Ingrese el nombre completo del desarrollador: ");
        user = scan.nextLine();
        while (user.isBlank()) {
            System.out.println("El campo de nombre del desarrollador no puede estar vacio, escriba el nombre de nuevo a continuacion: ");
            user = scan.nextLine();
        }

        if (chequearUsuario(user)) {
            while (!habilidad.equalsIgnoreCase("0")) {
                System.out.println("Ingrese una habilidad del desarrollador (Escriba '0' para detener el ingreso de habilidades)");
                habilidad = scan.nextLine();
                if (!habilidad.equalsIgnoreCase("0")) {
                    habilidades.add(habilidad);
                }
            }
            crearUsuarioDesarrollador(user, habilidades);
        } else {
            System.out.println("Ese desarrollador ya existe!");
            System.out.println("Volviendo al menu principal...\n");
        }
    }

    /**
     * Crea un nuevo usuario de tipo Desarrollador y lo agrega a la lista de
     * desarrolladores.
     *
     * @param user El nombre de usuario del desarrollador.
     * @param habilidades La lista de habilidades del desarrollador.
     */
    private void crearUsuarioDesarrollador(String user, ArrayList<String> habilidades) {
        Desarrollador dev = new Desarrollador(user, habilidades);
        if (devs.add(dev)) {
            System.out.println("Desarrollador añadido con exito.\n");
        }
    }

    /**
     * Muestra los desarrolladores disponibles.
     */
    public void mostrarDesarrolladoresDisponibles() {
        for (Desarrollador dev : devs) {
            if (dev.getEstaDisponible()) {
                System.out.println("\nID [" + devs.indexOf(dev) + "]   " + dev.getNombre());
                System.out.println("Habilidades:");
                dev.getHabilidades();
            }
        }
    }

    /**
     * Verifica si hay desarrolladores disponibles.
     *
     * @return true si hay desarrolladores disponibles, false en caso contrario.
     */
    public boolean hayDesarrolladoresDisponibles() {

        boolean flag = false;
        int i = 0;
        Desarrollador dev;
        do {
            dev = devs.get(i);
            i++;
        } while (!dev.getEstaDisponible() && i < devs.size());

        if (dev.getEstaDisponible()) {
            flag = true;
        }
        return flag;
    }

    /**
     * Asigna desarrolladores a los proyectos de los clientes.
     */
    private void asignarDesarrolladores() {
        if (hayDesarrolladoresDisponibles()) {
            int numProyecto, numDev;
            String opcion;
            Desarrollador dev;

            Scanner scan = new Scanner(System.in);
            for (Usuario u : usuarios) {
                if (u instanceof Cliente) {
                    Cliente cliente = (Cliente) u;
                    System.out.println("Cliente: " + cliente.getUsername());

                    if (cliente.tieneProyectos()) {
                        try {
                            cliente.verTituloProyecto();
                            System.out.println("Seleccione el ID del proyecto al que quiere asignar un desarrollador ");
                            opcion = scan.nextLine();
                            numProyecto = Integer.parseInt(opcion);
                            mostrarDesarrolladoresDisponibles();
                            System.out.println("Ingrese el ID del desarrollador a asignar: ");
                            opcion = scan.nextLine();
                            numDev = Integer.parseInt(opcion);
                            dev = devs.get(numDev);
                            cliente.setDesarrollador(numProyecto, dev);
                            System.out.println("Desarrollador asignado con exito.");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Ingresaste un ID fuera de parametro.");
                            System.out.println("Volviendo al menu principal...\n");
                        }
                    } else {
                        System.out.println("El cliente no tiene proyectos.\n");
                    }
                }
            }
        } else {
            System.out.println("No hay desarrolladores disponibles!\n");
        }
    }

}
