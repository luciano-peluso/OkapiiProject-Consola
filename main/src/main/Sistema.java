package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * La clase Sistema representa el sistema principal del programa. Implementa la
 * interfaz Serializable.
 * 
 * @author Luciano Peluso
 * @author Nahuel Olivera 
 */
public class Sistema implements Serializable {

    private BaseDeUsuarios users;
    private ArrayList<Desarrollador> devs;

    /**
     * Crea una nueva instancia de Sistema e inicializa la base de usuarios.
     */
    public Sistema() {
        devs = new ArrayList<Desarrollador>();
        users = new BaseDeUsuarios(devs);

    }

    /**
     * Inicia el sistema y muestra las opciones disponibles.
     */
    public void arrancar() throws IOException {
        String opcion;
        Usuario usuarioLocal;
        do {
            Scanner scan = new Scanner(System.in);
            mostrarOpciones();
            opcion = scan.nextLine();
            while (!opcion.equalsIgnoreCase("0") && !opcion.equalsIgnoreCase("1") && !opcion.equalsIgnoreCase("2")) {
                System.out.println("La opcion ingresada no existe.");
                mostrarOpciones();
                opcion = scan.nextLine();
            }
            switch (opcion) {
                case "1":
                    if (users.estaVacia()) {
                        System.out.println("No hay usuarios registrados en el sistema.");
                        System.out.println("Volviendo al menu principal...");
                    } else {
                        try {
                            usuarioLocal = loguearUsuario();
                            usuarioLocal.verOpciones();
                        } catch (NullPointerException e) {
                            System.out.println("Usuario o clave incorrecto.");
                        }
                    }
                    break;
                case "2":
                    registrarUsuarioAdmin();
                    break;
            }
        } while (!opcion.equalsIgnoreCase("0"));
        serializar("Sistema.txt");
    }

    /**
     * Muestra las opciones disponibles en el sistema.
     */
    public void mostrarOpciones() {
        System.out.println("\n1 - Loguear usuario.");
        System.out.println("2 - Registrarse como Administrador.");
        System.out.println("0 - Salir");
    }

    /**
     * Permite al usuario loguearse al sistema.
     *
     * @return el usuario logueado
     * @throws java.io.IOException
     */
    public Usuario loguearUsuario() throws IOException {
        String user;
        String password;
        Usuario userABuscar = null;
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese un nombre de usuario: ");
        user = scan.nextLine();
        System.out.println("Ingrese una clave: ");
        password = scan.nextLine();
        userABuscar = users.buscarUsuario(user);

        if (userABuscar != null) {
            if (userABuscar.sonIgualesPasswords(password)) {
                userABuscar.seLogueo();
                System.out.println("Iniciaste sesion con exito.\n");
            } else {
                userABuscar = null;
            }
        }
        return userABuscar;
    }

    /**
     * Permite registrar un nuevo usuario administrador en el sistema.
     */
    private void registrarUsuarioAdmin() {
        String user;
        String password;
        String laClave = "@Admin2603";
        Scanner scan = new Scanner(System.in);

        System.out.println("Ingrese la clave de ADMINISTRADOR: ");
        if (laClave.equals(scan.nextLine())) {
            System.out.println("Ingrese un nombre de usuario: ");
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
            if (users.chequearUsuario(user)) {
                users.crearUsuarioAdministrador(user, password);
            } else {
                System.out.println("Ese usuario ya existe!");
                System.out.println("Volviendo al menu principal...\n");
            }
        } else {
            System.out.println("La clave ingresada es incorrecta.");
            System.out.println("Volviendo al menu principal...\n");
        }
    }

    /**
     * Serializa el objeto actual y lo guarda en un archivo con el nombre
     * especificado.
     *
     * @param nombre El nombre del archivo donde se guardará el objeto
     * serializado.
     * @throws FileNotFoundException Si no se encuentra el archivo especificado.
     * @throws IOException Si ocurre un error durante la escritura del objeto
     * serializado en el archivo.
     */
    public void serializar(String nombre) throws FileNotFoundException, IOException {
        FileOutputStream f = new FileOutputStream(nombre);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();
    }

    /**
     * Deserializa un objeto de tipo Sistema desde un archivo con el nombre
     * especificado y lo devuelve. Si el archivo no existe, se crea un nuevo
     * objeto Sistema.
     *
     * @param nombre El nombre del archivo desde el cual se deserializará el
     * objeto.
     * @return Un objeto Sistema deserializado o un nuevo objeto Sistema si el
     * archivo no existe.
     * @throws IOException Si ocurre un error durante la lectura del archivo.
     * @throws ClassNotFoundException Si la clase Sistema no se encuentra en el
     * archivo.
     */
    public Sistema deSerializar(String nombre) throws IOException, ClassNotFoundException {
        Sistema completo = new Sistema();
        try {
            FileInputStream f = new FileInputStream(nombre);
            ObjectInputStream o = new ObjectInputStream(f);
            completo = (Sistema) o.readObject();
            o.close();
            f.close();
            System.out.println("Arranque desde archivo exitoso.");
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado. Iniciando de 0 el sistema.");
        }
        return completo;
    }
}
