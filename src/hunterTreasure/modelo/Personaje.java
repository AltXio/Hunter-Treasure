package hunterTreasure.modelo;

import java.awt.*;

public class Personaje {
    private int posicionXPersonaje;
    private int posicionYPersonaje;
    private final int TAMANO_PERSONAJE;
    private String nombrePersonaje;
    private int velocidadPersonaje;
    private int vidaPersonaje;
    private int danoPersonaje;
    private Image imagenPersonaje;

    public Personaje(int posicionXPersonaje, int posicionYPersonaje, int velocidadPersonaje, int vidaPersonaje, int TAMANO_PERSONAJE, Image imagenPersonaje) {
        this.posicionXPersonaje = posicionXPersonaje;
        this.posicionYPersonaje = posicionYPersonaje;
        this.velocidadPersonaje = velocidadPersonaje;
        this.vidaPersonaje = vidaPersonaje;
        this.TAMANO_PERSONAJE = TAMANO_PERSONAJE;
        this.imagenPersonaje = imagenPersonaje;
    }

    public void setPosicionXPersonaje(int posicionXPersonaje) {
        this.posicionXPersonaje = posicionXPersonaje;
    }

    public void setPosicionYPersonaje(int posicionYPersonaje) {
        this.posicionYPersonaje = posicionYPersonaje;
    }

    public void setNombrePersonaje(String nombrePersonaje) {this.nombrePersonaje = nombrePersonaje;}

    public void setVelocidadPersonaje(int velocidadPersonaje) {this.velocidadPersonaje = velocidadPersonaje;}

    public void setVidaPersonaje(int vidaPersonaje) {this.vidaPersonaje = Math.max(0, vidaPersonaje);}

    public void danoPersonaje(int dañoPersonaje) {
        this.danoPersonaje = dañoPersonaje;
    }

    public void setImagenPersonaje(Image imagenPersonaje) {
        this.imagenPersonaje = imagenPersonaje;
    }

    public int getPosicionXPersonaje() {
        return posicionXPersonaje;
    }

    public int getPosicionYPersonaje() {
        return posicionYPersonaje;
    }

    public int getTAMANO_PERSONAJE() {
        return TAMANO_PERSONAJE;
    }

    public String getNombrePersonaje() {return nombrePersonaje;}

    public int getVelocidadPersonaje() {
        return velocidadPersonaje;
    }

    public int getVidaPersonaje() {
        return vidaPersonaje;
    }

    public int danoPersonaje() {
        return danoPersonaje;
    }

    public Image getImagenPersonaje() {
        return imagenPersonaje;
    }

    public void moverPersonaje(int dx, int dy) {
        this.posicionXPersonaje += dx;
        this.posicionYPersonaje += dy;
    }
}