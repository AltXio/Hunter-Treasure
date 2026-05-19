package hunterTreasure.interfaz;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JPanel {

    private final Image IMAGENFONDO;

    public MenuPrincipal() {
        ImageIcon fondo = new ImageIcon(getClass().getResource("/imagenes/ElementosJuego/fondoMenu.jpg"));
        IMAGENFONDO = fondo.getImage();
        setLayout(null);

        JButton botonStart = new JButton("Empezar");
        botonStart.setFocusable(false);
        botonStart.setBounds(300, 300, 200, 50);
        add(botonStart);

        JButton botonInstrucciones = new JButton("Instrucciones");
        botonInstrucciones.setFocusable(false);
        botonInstrucciones.setBounds(300, 375, 200, 50);
        add(botonInstrucciones);

        JButton botonSalir = new JButton("Salir");
        botonSalir.setFocusable(false);
        botonSalir.setBounds(300, 450, 200, 50);
        add(botonSalir);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(IMAGENFONDO, 0, 0, getWidth(), getHeight(), this);
    }
}