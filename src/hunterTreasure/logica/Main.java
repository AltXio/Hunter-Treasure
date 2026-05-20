package hunterTreasure.logica;

import hunterTreasure.interfaz.MenuPrincipal;
import hunterTreasure.interfaz.PrincipalWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PrincipalWindow();
        });
    }
}