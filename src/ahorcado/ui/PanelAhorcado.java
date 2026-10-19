package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import logica.JuegoAhorcado;

public class PanelAhorcado extends JPanel {

    private static final Color COLOR_CUERDA = new Color(190, 40, 40);

    private int errores = 0;

    /*
      Configura el fondo y el tamaño preferido del panel.
     */
    public PanelAhorcado() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(300, 300));
    }

    /*
     Establece la cantidad de errores (entre 0 y JuegoAhorcado.MAX_INTENTOS) y redibuja el panel.
     */
    public void setErrores(int errores) {
        this.errores = Math.max(0, Math.min(errores, JuegoAhorcado.MAX_INTENTOS));
        repaint();
    }

    /*
      Dibuja la estructura fija y después las partes del cuerpo según los errores.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            dibujarEstructura(g2);
            dibujarCuerpo(g2);
        } finally {
            g2.dispose();
        }
    }

    /*
      Dibuja la base, el poste y la viga de la horca.
     */
    private void dibujarEstructura(Graphics2D g2) {
        g2.setColor(new Color(90, 60, 30));
        g2.drawLine(30, 270, 200, 270);
        g2.drawLine(70, 270, 70, 40);
        g2.drawLine(70, 40, 190, 40);
    }

    /*
      Dibuja cada parte del cuerpo según el número de errores acumulados.
      Las 7 piezas corresponden a los 7 intentos; si cambia MAX_INTENTOS, hay que revisar este dibujo.
     */
    private void dibujarCuerpo(Graphics2D g2) {
        g2.setColor(Color.DARK_GRAY);
        if (errores >= 1) g2.drawOval(170, 80, 40, 40);     // cabeza
        if (errores >= 2) g2.drawLine(190, 120, 190, 190);  // torso
        if (errores >= 3) g2.drawLine(190, 140, 220, 170);  // brazo derecho
        if (errores >= 4) g2.drawLine(190, 140, 160, 170);  // brazo izquierdo
        if (errores >= 5) g2.drawLine(190, 190, 215, 240);  // pierna derecha
        if (errores >= 6) g2.drawLine(190, 190, 165, 240);  // pierna izquierda
        if (errores >= 7) {                                 // cuerda final
            g2.setColor(COLOR_CUERDA);
            g2.drawLine(190, 40, 190, 80);
        }
    }
}