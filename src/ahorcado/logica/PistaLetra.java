/*
 * Clase: PistaLetra
 * Segunda pista: revela una letra aleatoria de la palabra que aún no ha sido acertada.
 * La letra revelada cuenta como acierto dentro de la partida.
 */
package logica;

public class PistaLetra extends Pista {

    /*
     * Crea la pista de letra revelada.
     */
    public PistaLetra() {
        super("Letra revelada");
    }

    @Override
    protected String generarMensaje(JuegoAhorcado juego) {
        return juego.revelarLetraAleatoria();
    }
}