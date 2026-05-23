package hunterTreasure.interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameOver extends JPanel {

    private final Image IMAGEN__FONDO;

    public GameOver(PrincipalWindow principalWindow, String nombreUsuario) {
        IMAGEN__FONDO = new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagenes/elementosJuego/died.png"))).getImage();
        setLayout(null);

        JButton botonReintentar = new JButton("Intentar de nuevo");
        botonReintentar.setFocusable(false);
        botonReintentar.setBounds(300, 500, 200, 50);
        add(botonReintentar);
        botonReintentar.addActionListener(_ -> principalWindow.cambiarPanel(new VistaJuego(principalWindow, nombreUsuario)));

        JButton botonSalir = new JButton("Salir");
        botonSalir.setFocusable(false);
        botonSalir.setBounds(300, 580, 200, 50);
        add(botonSalir);
        botonSalir.addActionListener(_ -> System.exit(0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(IMAGEN__FONDO, 0, 0, getWidth(), getHeight(), this);
    }
}