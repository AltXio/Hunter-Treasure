package hunterTreasure.interfaz;

import javax.swing.*;
import java.awt.*;

public class PrincipalWindow extends JFrame {
    public PrincipalWindow() {
        super("Hunter Treasure");
        setResizable(false);
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Image icon = new ImageIcon(getClass().getResource("/imagenes/ElementosJuego/logotipo.jpg")).getImage();
        setIconImage(icon);
        setVisible(true);
    }

    public void cambiarPanel(JPanel panel) {
        setContentPane(panel);
        revalidate();
        repaint();
    }
}