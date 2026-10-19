/*
 * Clase: PistaCategoria
 * Primera pista: muestra la categoría a la que pertenece la palabra secreta.
 * Hereda de Pista y sobrescribe generarMensaje.
 */
package logica;

public class PistaCategoria extends Pista {

    /*
     * Crea la pista de categoría.
     */
    public PistaCategoria() {
        super("Categoría");
    }

    @Override
    protected String generarMensaje(JuegoAhorcado juego) {
        return "La categoría es: " + juego.getCategoria();
    }
}