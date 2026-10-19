/*
 * Clase: Pista (abstracta)
 * Base de las tres pistas del juego. Define el comportamiento común:
 * controlar si la pista ya fue usada y reiniciarla en cada partida.
 * Cada subclase decide qué mensaje genera (polimorfismo mediante generarMensaje).
 */
package logica;

public abstract class Pista {

    private final String nombre;
    private boolean usada = false;

    /*
     * Crea una pista con su nombre visible.
     */
    protected Pista(String nombre) {
        this.nombre = nombre;
    }

    /*
     * Usa la pista: la marca como usada y devuelve el mensaje correspondiente.
     * Si ya fue usada, devuelve un aviso sin repetir la acción.
     */
    public final String usar(JuegoAhorcado juego) {
        if (usada) {
            return "Ya usaste la pista de " + nombre.toLowerCase() + ".";
        }
        usada = true;
        return generarMensaje(juego);
    }

    /*
     * Cada subclase define su propio mensaje de pista.
     */
    protected abstract String generarMensaje(JuegoAhorcado juego);

    /*
     * Restablece la pista para una nueva partida.
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