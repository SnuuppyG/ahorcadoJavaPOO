/*
 * Clase: PistaEscrita
 * Tercera pista: muestra una descripción escrita relacionada con la palabra secreta.
 */
package logica;

public class PistaEscrita extends Pista {

    /*
     * Crea la pista escrita.
     */
    public PistaEscrita() {
        super("Descripción");
    }

    @Override
    protected String generarMensaje(JuegoAhorcado juego) {
        return "Pista: " + juego.getPistaEscrita();
    }
}