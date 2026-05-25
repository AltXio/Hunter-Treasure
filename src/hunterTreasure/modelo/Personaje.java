package hunterTreasure.modelo;

import java.awt.*;

public class Personaje {
    private int posicionXPersonaje;
    private int posicionYPersonaje;
    private int tamanoPersonaje;
    private int velocidadPersonaje;
    private int vidaPersonaje;
    private Image imagenPersonaje;

    public Personaje(int posicionXPersonaje, int posicionYPersonaje, int velocidadPersonaje, int vidaPersonaje, int tamanoPersonaje, Image imagenPersonaje) {
        this.posicionXPersonaje = posicionXPersonaje;
        this.posicionYPersonaje = posicionYPersonaje;
        this.velocidadPersonaje = velocidadPersonaje;
        this.vidaPersonaje = vidaPersonaje;
        this.tamanoPersonaje = tamanoPersonaje;
        this.imagenPersonaje = imagenPersonaje;
    }

    public void setPosicionXPersonaje(int posicionXPersonaje) {
        this.posicionXPersonaje = posicionXPersonaje;
    }

    public void setPosicionYPersonaje(int posicionYPersonaje) {
        this.posicionYPersonaje = posicionYPersonaje;
    }

    public void setVidaPersonaje(int vidaPersonaje) {
        this.vidaPersonaje = Math.max(0, vidaPersonaje);
    }

    public int getPosicionXPersonaje() {
        return posicionXPersonaje;
    }

    public int getPosicionYPersonaje() {
        return posicionYPersonaje;
    }

    public int getTamanoPersonaje() {
        return tamanoPersonaje;
    }

    public int getVelocidadPersonaje() {
        return velocidadPersonaje;
    }

    public int getVidaPersonaje() {
        return vidaPersonaje;
    }

    public Image getImagenPersonaje() {
        return imagenPersonaje;
    }

    public void moverPersonaje(int dx, int dy) {
        this.posicionXPersonaje += dx;
        this.posicionYPersonaje += dy;
    }
}