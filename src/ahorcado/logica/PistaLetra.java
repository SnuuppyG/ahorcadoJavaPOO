package logica;

public class PistaLetra extends Pista {

    /*
      Crea la pista de letra revelada.
     */
    public PistaLetra() {
        super("Letra revelada");
    }

    @Override
    protected String aplicar(JuegoAhorcado juego) {
        return juego.revelarLetraAleatoria()
                .map(letra -> "Letra revelada: " + letra)
                .orElse("No quedan letras por revelar.");
    }
}