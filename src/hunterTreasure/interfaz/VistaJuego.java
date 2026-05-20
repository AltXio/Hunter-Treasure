package hunterTreasure.interfaz;

import javax.swing.*;
import java.awt.*;
public class VistaJuego extends JPanel {

    public static final int ALTO_HUD = 60;
    public static final int LIMITE_X = 800 / 9; // ~88px

    private PrincipalWindow principalWindow;
    private String nombreUsuario;
    private int vidas = 3;
    private int  enemigosEliminados = 0;
    private Image fondoArena;
    private Image fondoAgua;
    private Image barco;

    public VistaJuego(PrincipalWindow principalWindow, String nombreUsuario) {
        this.principalWindow = principalWindow;
        this.nombreUsuario = nombreUsuario;
        fondoArena = new ImageIcon(getClass().getResource("/imagenes/mapas/arenaMapa.jpg")).getImage();
        fondoAgua = new ImageIcon(getClass().getResource("/imagenes/mapas/aguaMapa.jpg")).getImage();
        barco = new ImageIcon(getClass().getResource("/imagenes/elementosJuego/barcoTierra.png")).getImage();

        // 60fps = 16ms
        Timer timer = new Timer(16, e -> repaint());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int anchoAgua = getWidth() / 9;
        int anchoArena = getWidth() - anchoAgua;
        int xBarco = anchoAgua - 150 / 2;
        int yBarco = getHeight() / 2 - 150 / 2;
        g.drawImage(fondoAgua, 0, 0, anchoAgua, getHeight(), this);
        g.drawImage(fondoArena, anchoAgua, 0, anchoArena, getHeight(), this);
        g.drawImage(barco, xBarco + 150, yBarco, -150, 150, this);

        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, getWidth(), ALTO_HUD);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Jugador: " + nombreUsuario, 20, 35);
        g.drawString("Vidas: " + vidas, 300, 35);
        g.drawString("Enemigos: " + enemigosEliminados, 500, 35);
    }
}