/*
 * Clase: VentanaAhorcado
 * Ventana principal (JFrame) del juego. Organiza la interfaz en regiones:
 * la palabra y los mensajes arriba, el dibujo al centro, los intentos, las
 * pistas y el botón de nueva partida a la derecha, y el teclado de letras abajo.
 * Actúa como intermediario entre el usuario y la lógica (JuegoAhorcado).
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import logica.JuegoAhorcado;
import logica.Pista;
import modelo.BancoDePalabras;

public class VentanaAhorcado extends JFrame {

    private static final String ALFABETO = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

    private final JuegoAhorcado juego;
    private final PanelAhorcado panelDibujo = new PanelAhorcado();
    private final JLabel lblPalabra = new JLabel(" ", SwingConstants.CENTER);
    private final JLabel lblMensaje = new JLabel(" ", SwingConstants.CENTER);
    private final JLabel lblIntentos = new JLabel();
    private final JLabel lblFalladas = new JLabel();
    private final Map<Character, JButton> botonesLetra = new LinkedHashMap<>();
    private final Map<Pista, JButton> botonesPista = new LinkedHashMap<>();

    /*
     * Crea la ventana, carga el juego y construye la interfaz.
     */
    public VentanaAhorcado() {
        super("Juego del Ahorcado");
        this.juego = crearJuego();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        construirInterfaz();
        pack();
        setLocationRelativeTo(null);
        actualizarVista();
    }

    /*
     * Carga el banco de palabras. Si falla, informa al usuario y cierra la aplicación.
     */
    private static JuegoAhorcado crearJuego() {
        try {
            return new JuegoAhorcado(new BancoDePalabras());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo cargar recursos/palabras.txt\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            return null;
        }
    }

    /*
     * Crea todos los componentes y los ubica en sus regiones.
     */
    private void construirInterfaz() {
        lblPalabra.setFont(new Font("Monospaced", Font.BOLD, 30));
        lblMensaje.setFont(new Font("SansSerif", Font.ITALIC, 16));
        lblIntentos.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblFalladas.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblFalladas.setForeground(new Color(180, 30, 30));

        JPanel norte = new JPanel(new BorderLayout());
        norte.add(lblPalabra, BorderLayout.CENTER);
        norte.add(lblMensaje, BorderLayout.SOUTH);
        norte.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));

        JPanel panelInfo = new JPanel(new GridLayout(0, 1, 0, 10));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 15));
        panelInfo.add(lblIntentos);
        panelInfo.add(lblFalladas);
        for (Pista pista : juego.getPistas()) {
            JButton btnPista = new JButton("Pista: " + pista.getNombre());
            btnPista.addActionListener(e -> usarPista(pista));
            botonesPista.put(pista, btnPista);
            panelInfo.add(btnPista);
        }
        JButton btnNueva = new JButton("Nueva partida");
        btnNueva.addActionListener(e -> nuevaPartida());
        panelInfo.add(btnNueva);

        JPanel panelLetras = new JPanel(new GridLayout(0, 9, 5, 5));
        panelLetras.setBorder(BorderFactory.createEmptyBorder(5, 15, 15, 15));
        for (char c : ALFABETO.toCharArray()) {
            JButton btnLetra = new JButton(String.valueOf(c));
            btnLetra.setFont(new Font("SansSerif", Font.BOLD, 16));
            btnLetra.addActionListener(e -> procesarLetra(c));
            botonesLetra.put(c, btnLetra);
            panelLetras.add(btnLetra);
        }

        add(norte, BorderLayout.NORTH);
        add(panelDibujo, BorderLayout.CENTER);
        add(panelInfo, BorderLayout.EAST);
        add(panelLetras, BorderLayout.SOUTH);
    }

    /*
     * Procesa la letra presionada y actualiza la vista.
     */
    private void procesarLetra(char letra) {
        if (juego.getEstado() != JuegoAhorcado.Estado.EN_CURSO) {
            return;
        }
        boolean acierto = juego.intentarLetra(letra);
        lblMensaje.setText(acierto ? "¡Correcto!" : "La letra no está en la palabra");
        actualizarVista();
    }

    /*
     * Usa la pista seleccionada y muestra su mensaje.
     */
    private void usarPista(Pista pista) {
        lblMensaje.setText(pista.usar(juego));
        actualizarVista();
    }

    /*
     * Inicia una nueva partida con otra palabra.
     */
    private void nuevaPartida() {
        juego.nuevaPartida();
        lblMensaje.setText("¡Nueva palabra! Buena suerte.");
        actualizarVista();
    }

    /*
     * Sincroniza todos los componentes con el estado actual del juego:
     * palabra oculta, intentos, letras falladas, dibujo, botones habilitados y mensaje final.
     */
    private void actualizarVista() {
        boolean enCurso = juego.getEstado() == JuegoAhorcado.Estado.EN_CURSO;

        lblPalabra.setText(juego.getPalabraOculta());
        lblIntentos.setText("Intentos restantes: " + juego.getIntentosRestantes());

        StringBuilder falladas = new StringBuilder();
        for (Character c : juego.getLetrasFalladas()) {
            falladas.append(c).append(' ');
        }
        lblFalladas.setText("Incorrectas: " + falladas.toString().trim());

        panelDibujo.setErrores(juego.getLetrasFalladas().size());

        for (Map.Entry<Character, JButton> entrada : botonesLetra.entrySet()) {
            entrada.getValue().setEnabled(enCurso && !juego.letraYaUsada(entrada.getKey()));
        }
        for (Map.Entry<Pista, JButton> entrada : botonesPista.entrySet()) {
            entrada.getValue().setEnabled(enCurso && !entrada.getKey().isUsada());
        }

        if (juego.getEstado() == JuegoAhorcado.Estado.GANADO) {
            lblMensaje.setText("¡Ganaste! La palabra era " + juego.getPalabraSecreta());
        } else if (juego.getEstado() == JuegoAhorcado.Estado.PERDIDO) {
            lblMensaje.setText("Perdiste. La palabra era " + juego.getPalabraSecreta());
        }
    }
}