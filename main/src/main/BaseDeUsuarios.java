package main;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La clase BaseDeUsuarios representa una base de datos de usuarios. Permite
 * realizar operaciones relacionadas con los usuarios almacenados en la base de
 * datos.
 * 
 * 
 * @author Luciano Peluso
 * @author Nahuel Olivera
 */
public class BaseDeUsuarios implements Serializable {

    private ArrayList<Usuario> u;
    private ArrayList<Desarrollador> devs;

    /**
     * Crea una nueva instancia de BaseDeUsuarios. Inicializa las listas de
     * desarrolladores y usuarios.
     */
    public BaseDeUsuarios(ArrayList devs) {
        u = new ArrayList<>();
        this.devs = devs;
    }

    /**
     * Verifica si la base de usuarios está vacía.
     *
     * @return true si la base de usuarios está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return u.isEmpty();
    }

    /**
     * Verifica si un usuario está disponible en la base de datos.
     *
     * @param user el nombre de usuario a verificar
     * @return true si el usuario está disponible, false en caso contrario.
     */
    public boolean chequearUsuario(String user) {
        boolean estaDisponible = true;
        Usuario userAComparar;
        if (!estaVacia()) {
            int i = 0;
            do {
                userAComparar = u.get(i);
                i++;
            } while (i < u.size() && !userAComparar.sonIgualesUsers(user));

            if (userAComparar.sonIgualesUsers(user)) {
                estaDisponible = false;
            }
        }
        return estaDisponible;
    }

    /**
     * Busca un usuario en la base de datos por su nombre de usuario.
     *
     * @param username el nombre de usuario a buscar
     * @return el usuario encontrado o null si no se encuentra ningún usuario
     * con ese nombre.
     */
    public Usuario buscarUsuario(String username) {
        Usuario userBuscado = null;
        int i = 0;
        if (!estaVacia()) {
            do {
                userBuscado = u.get(i);
                i++;
            } while (i < u.size() && !userBuscado.sonIgualesUsers(username));

            if (!(i < u.size()) && !userBuscado.sonIgualesUsers(username)) {
                userBuscado = null;
            }
        }
        return userBuscado;
    }

    /**
     * Crea un nuevo usuario administrador y lo agrega a la base de datos.
     *
     * @param user el nombre de usuario del administrador
     * @param password la contraseña del administrador
     */
    public void crearUsuarioAdministrador(String user, String password) {
        Administrador usuarioEntrante = new Administrador(user, password, u, devs, "Administrador");
        if (u.add(usuarioEntrante)) {
            System.out.println("Usuario creado con éxito.");
        }
    }
}
