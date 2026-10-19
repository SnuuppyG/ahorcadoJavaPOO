/*
 * Clase: JuegoAhorcado
 * Lógica principal del juego. Controla la palabra secreta, las letras acertadas
 * y falladas, los intentos disponibles y el estado de la partida
 * (EN_CURSO, GANADO o PERDIDO). Sus atributos son privados y se accede a ellos
 * mediante métodos públicos (encapsulación). Usa ArrayList para las letras.
 */
package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import modelo.BancoDePalabras;
import modelo.FichaPalabra;

public class JuegoAhorcado {

    public static final int MAX_INTENTOS = 7;

    public enum Estado { EN_CURSO, GANADO, PERDIDO }

    private final BancoDePalabras banco;
    private final Random random = new Random();
    private final ArrayList<Pista> pistas = new ArrayList<>();
    private final ArrayList<Character> letrasAcertadas = new ArrayList<>();
    private final ArrayList<Character> letrasFalladas = new ArrayList<>();
    private FichaPalabra fichaActual;
    private Estado estado;

    /*
     * Crea el juego, registra las tres pistas y comienza una primera partida.
     */
    public JuegoAhorcado(BancoDePalabras banco) {
        this.banco = banco;
        pistas.add(new PistaCategoria());
        pistas.add(new PistaLetra());
        pistas.add(new PistaEscrita());
        nuevaPartida();
    }

    /*
     * Reinicia el juego con una palabra aleatoria del banco.
     */
    public void nuevaPartida() {
        fichaActual = banco.obtenerAleatoria();
        letrasAcertadas.clear();
        letrasFalladas.clear();
        for (Pista pista : pistas) {
            pista.reiniciar();
        }
        estado = Estado.EN_CURSO;
    }

    /*
     * Procesa un intento de letra. Devuelve true si la letra está en la palabra.
     * Ignora letras ya usadas o partidas terminadas.
     */
    public boolean intentarLetra(char letra) {
        if (estado != Estado.EN_CURSO || letraYaUsada(letra)) {
            return false;
        }
        char letraMayus = Character.toUpperCase(letra);
        if (fichaActual.getPalabra().indexOf(letraMayus) >= 0) {
            letrasAcertadas.add(letraMayus);
            verificarVictoria();
            return true;
        }
        letrasFalladas.add(letraMayus);
        if (letrasFalladas.size() >= MAX_INTENTOS) {
            estado = Estado.PERDIDO;
        }
        return false;
    }

    /*
     * Revela una letra aleatoria que aún no esté acertada. Usada por PistaLetra.
     */
    public String revelarLetraAleatoria() {
        if (estado != Estado.EN_CURSO) {
            return "La partida ya terminó.";
        }
        ArrayList<Character> ocultas = new ArrayList<>();
        for (char c : fichaActual.getPalabra().toCharArray()) {
            if (!letrasAcertadas.contains(c) && !ocultas.contains(c)) {
                ocultas.add(c);
            }
        }
        if (ocultas.isEmpty()) {
            return "No quedan letras por revelar.";
        }
        char elegida = ocultas.get(random.nextInt(ocultas.size()));
        letrasAcertadas.add(elegida);
        verificarVictoria();
        return "Letra revelada: " + elegida;
    }

    /*
     * Indica si la letra ya fue acertada o fallada en esta partida.
     */
    public boolean letraYaUsada(char letra) {
        char letraMayus = Character.toUpperCase(letra);
        return letrasAcertadas.contains(letraMayus) || letrasFalladas.contains(letraMayus);
    }

    /*
     * Devuelve la palabra con guiones bajos para las letras no descubiertas.
     * Ejemplo: "G _ T O".
     */
    public String getPalabraOculta() {
        StringBuilder sb = new StringBuilder();
        for (char c : fichaActual.getPalabra().toCharArray()) {
            sb.append(letrasAcertadas.contains(c) ? c : '_').append(' ');
        }
        return sb.toString().trim();
    }

    /*
     * Comprueba si todas las letras de la palabra ya fueron acertadas.
     */
    private boolean palabraAdivinada() {
        for (char c : fichaActual.getPalabra().toCharArray()) {
            if (!letrasAcertadas.contains(c)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Cambia el estado a GANADO si la palabra está completa.
     */
    private void verificarVictoria() {
        if (palabraAdivinada()) {
            estado = Estado.GANADO;
        }
    }

    public String getCategoria() {
        return fichaActual.getCategoria();
    }

    public String getPistaEscrita() {
        return fichaActual.getPistaEscrita();
    }

    public String getPalabraSecreta() {
        return fichaActual.getPalabra();
    }

    public int getIntentosRestantes() {
        return MAX_INTENTOS - letrasFalladas.size();
    }

    public List<Character> getLetrasFalladas() {
        return Collections.unmodifiableList(letrasFalladas);
    }

    public List<Pista> getPistas() {
        return Collections.unmodifiableList(pistas);
    }

    public Estado getEstado() {
        return estado;
    }
}