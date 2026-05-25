package hunterTreasure.modelo;

import java.awt.*;

public class Personaje {
    private final int tamanoPersonaje;
    private final int velocidadPersonaje;
    private final Image imagenPersonaje;
    private int posicionXPersonaje;
    private int posicionYPersonaje;
    private int vidaPersonaje;

    public Personaje(int posicionXPersonaje, int posicionYPersonaje, int velocidadPersonaje, int vidaPersonaje, int tamanoPersonaje, Image imagenPersonaje) {
        this.posicionXPersonaje = posicionXPersonaje;
        this.posicionYPersonaje = posicionYPersonaje;
        this.velocidadPersonaje = velocidadPersonaje;
        this.vidaPersonaje = vidaPersonaje;
        this.tamanoPersonaje = tamanoPersonaje;
        this.imagenPersonaje = imagenPersonaje;
    }

    public void moverPersonaje(int dx, int dy) {
        this.posicionXPersonaje += dx;
        this.posicionYPersonaje += dy;
    }

    public int getPosicionXPersonaje() { return posicionXPersonaje; }
    public void setPosicionXPersonaje(int posicionXPersonaje) { this.posicionXPersonaje = posicionXPersonaje; }

    public int getPosicionYPersonaje() { return posicionYPersonaje; }
    public void setPosicionYPersonaje(int posicionYPersonaje) { this.posicionYPersonaje = posicionYPersonaje; }

    public int getVidaPersonaje() { return vidaPersonaje; }
    public void setVidaPersonaje(int vidaPersonaje) { this.vidaPersonaje = Math.max(0, vidaPersonaje); }

    public int getTamanoPersonaje() { return tamanoPersonaje; }
    public int getVelocidadPersonaje() { return velocidadPersonaje; }
    public Image getImagenPersonaje() { return imagenPersonaje; }
}