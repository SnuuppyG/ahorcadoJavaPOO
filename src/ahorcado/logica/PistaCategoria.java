package logica;

public class PistaCategoria extends Pista {

    /*
      Crea la pista de categoría.
     */
    public PistaCategoria() {
        super("Categoría");
    }

    @Override
    protected String aplicar(JuegoAhorcado juego) {
        return "La categoría es: " + juego.getCategoria();
    }
}