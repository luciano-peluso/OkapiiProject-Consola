package main;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Clase principal que inicia el sistema.
 * @author Luciano Peluso
 * @author Nahuel Olivera
 */
public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        Sistema sistema = new Sistema();
        sistema = sistema.deSerializar("Sistema.txt");
        sistema.arrancar();
    }
}
