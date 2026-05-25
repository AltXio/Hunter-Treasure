package hunterTreasure.interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MenuPrincipal extends JPanel {

    private Image imagenFondo;

    public MenuPrincipal(PrincipalWindow principalWindow) {
        ImageIcon fondo = new ImageIcon(Objects.requireNonNull(getClass().getResource(
                "/imagenes/ElementosJuego/fondoMenu.jpg")));
        imagenFondo = fondo.getImage();
        setLayout(null);

        JButton botonStart = new JButton("Empezar");
        botonStart.setFocusable(false);
        botonStart.setBounds(300, 300, 200, 50);
        add(botonStart);
        botonStart.addActionListener(_ -> {
            String nombreUsuario = JOptionPane.showInputDialog("Introduce tu nombre: ");
            principalWindow.cambiarPanel(new VistaJuego(principalWindow, nombreUsuario));
        });

        JButton botonInstrucciones = new JButton("Instrucciones");
        botonInstrucciones.setFocusable(false);
        botonInstrucciones.setBounds(300, 375, 200, 50);
        add(botonInstrucciones);
        botonInstrucciones.addActionListener(_ ->
                JOptionPane.showMessageDialog(this,
                        "<html>" +
                                "<b>MOVIMIENTO:</b<br><br>" +
                                " Arriba &uarr;<br>" +
                                " Abajo &darr;<br>" +
                                " Izquierda &larr;<br>" +
                                " Derecha &rarr;<br><br>" +
                                " Derrota a todos los enemigos para ganar." +
                                "</html>"
                ));

        JButton botonSalir = new JButton("Salir");
        botonSalir.setFocusable(false);
        botonSalir.setBounds(300, 450, 200, 50);
        add(botonSalir);
        botonSalir.addActionListener(_ -> System.exit(0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }
}