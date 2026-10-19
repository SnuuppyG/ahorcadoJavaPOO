/*
 * Clase: Main
 * Punto de entrada de la aplicación del Juego del Ahorcado.
 * Crea la ventana principal dentro del hilo de eventos de Swing (EDT),
 * que es la forma correcta de iniciar una interfaz gráfica.
 */
package app;

import javax.swing.SwingUtilities;
import ui.VentanaAhorcado;

public class Main {

    /*
     * Método principal. Lanza la ventana en el hilo de eventos de Swing.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaAhorcado().setVisible(true));
    }
}