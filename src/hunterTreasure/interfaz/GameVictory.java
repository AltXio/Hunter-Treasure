package hunterTreasure.interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameVictory extends JPanel {

    private final Image imagenFondo;

    public GameVictory(PrincipalWindow principalWindow, String nombreUsuario) {
        imagenFondo = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "/imagenes/elementosJuego/winner.jpg"))).getImage();
        setLayout(null);

        JButton botonReintentar = new JButton("Intentar de nuevo");
        botonReintentar.setFocusable(false);
        botonReintentar.setBounds(300, 530, 200, 50);
        add(botonReintentar);
        botonReintentar.addActionListener(_ -> principalWindow.cambiarPanel(
                new VistaJuego(principalWindow, nombreUsuario)));

        JButton botonSalir = new JButton("Salir");
        botonSalir.setFocusable(false);
        botonSalir.setBounds(300, 600, 200, 50);
        add(botonSalir);
        botonSalir.addActionListener(_ -> System.exit(0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }
}