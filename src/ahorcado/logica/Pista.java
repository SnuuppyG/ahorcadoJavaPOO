package logica;

public abstract class Pista {

    private final String nombre;
    private boolean usada = false;

    /*
      Crea una pista con su nombre visible.
     */
    protected Pista(String nombre) {
        this.nombre = nombre;
    }

    /*
      Usa la pista: la marca como usada y devuelve el mensaje correspondiente.
      Si ya fue usada, devuelve un aviso sin repetir la acción.
     */
    public final String usar(JuegoAhorcado juego) {
        if (usada) {
            return "Ya usaste la pista de " + nombre.toLowerCase() + ".";
        }
        usada = true;
        return aplicar(juego);
    }

    /*
      Cada subclase define qué hace la pista y devuelve el mensaje para el jugador.
      Puede modificar el estado de la partida (por eso no se llama generarMensaje).
     */
    protected abstract String aplicar(JuegoAhorcado juego);

    /*
      Restablece la pista para una nueva partida.
     */
    public void reiniciar() {
        usada = false;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isUsada() {
        return usada;
    }
}