package app;

import javax.swing.SwingUtilities;
import ui.VentanaAhorcado;

public class Main {

    /*
      Método principal. Lanza la ventana en el hilo de eventos de Swing.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaAhorcado().setVisible(true));
    }
}