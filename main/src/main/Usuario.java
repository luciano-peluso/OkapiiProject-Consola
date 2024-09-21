package main;

import java.io.Serializable;
import java.util.Scanner;

/**
 * La clase Usuario es una clase abstracta que representa a un usuario genérico
 * del sistema. Implementa la interfaz Serializable.
 * 
 * 
 * @author Luciano Peluso
 * @author Nahuel Olivera 
 */
public abstract class Usuario implements Serializable {

    private String username;
    private String password;
    private boolean estaLogueado;
    private String identificador;

    /**
     * Crea una nueva instancia de Usuario con el nombre de usuario, la
     * contraseña y el identificador especificados.
     *
     * @param username el nombre de usuario
     * @param password la contraseña
     * @param identificador el identificador
     */
    public Usuario(String username, String password, String identificador) {
        this.username = username;
        this.password = password;
        this.identificador = identificador;
        estaLogueado = false;
    }

    /**
     * Devuelve el nombre de usuario del usuario.
     *
     * @return el nombre de usuario
     */
    public String getUsername() {
        return username;
    }

    /**
     * Devuelve la contraseña del usuario.
     *
     * @return la contraseña
     */
    public String getPassword() {
        return password;
    }

    /**
     * Devuelve el identificador del usuario.
     *
     * @return el identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Verifica si el usuario está logueado.
     *
     * @return true si el usuario está logueado, false en caso contrario
     */
    public boolean estaLogueado() {
        return estaLogueado;
    }

    /**
     * Establece que el usuario se ha logueado.
     */
    public void seLogueo() {
        this.estaLogueado = true;
    }

    /**
     * Establece que el usuario se ha deslogueado.
     */
    public void seDeslogueo() {
        this.estaLogueado = false;
    }

    /**
     * Permite al usuario cambiar su contraseña.
     */
    public void cambiarPassword() {
        Scanner scan = new Scanner(System.in);
        String password;
        System.out.println("Ingrese su clave actual:");
        password = scan.nextLine();
        if (sonIgualesPasswords(password)) {
            System.out.println("Ingrese su nueva clave:");
            password = scan.nextLine();
            setPassword(password);
        } else {
            System.out.println("La clave ingresada es incorrecta.\n\n");
        }
    }

    /**
     * Establece una nueva contraseña para el usuario.
     *
     * @param password la nueva contraseña
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Verifica si el nombre de usuario especificado es igual al nombre de
     * usuario del usuario actual.
     *
     * @param username el nombre de usuario a verificar
     * @return true si son iguales, false en caso contrario
     */
    public boolean sonIgualesUsers(String username) {
        return this.username.equalsIgnoreCase(username);
    }

    /**
     * Verifica si la contraseña especificada es igual a la contraseña del
     * usuario actual.
     *
     * @param password la contraseña a verificar
     * @return true si son iguales, false en caso contrario
     */
    public boolean sonIgualesPasswords(String password) {
        return this.password.equalsIgnoreCase(password);
    }

    /**
     * Método abstracto para ver las opciones disponibles para el usuario.
     */
    public abstract void verOpciones();
}
