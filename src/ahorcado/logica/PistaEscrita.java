package logica;

public class PistaEscrita extends Pista {

    /*
      Crea la pista escrita.
     */
    public PistaEscrita() {
        super("Descripción");
    }

    @Override
    protected String aplicar(JuegoAhorcado juego) {
        return "Pista: " + juego.getPistaEscrita();
    }
}