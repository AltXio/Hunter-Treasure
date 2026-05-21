package hunterTreasure.modelo;

import java.awt.*;

public class Personaje {
    private int posicionXPersonaje;
    private int posicionYPersonaje;
    private int tamañoPersonaje;
    private String nombrePersonaje;
    private int velocidadPersonaje;
    private int vidaPersonaje;
    private int dañoPersonaje;
    private Image imagenPersonaje;

    public Personaje(int posicionXPersonaje, int posicionYPersonaje, int velocidadPersonaje, int vidaPersonaje, int tamañoPersonaje, Image imagenPersonaje) {
        this.posicionXPersonaje = posicionXPersonaje;
        this.posicionYPersonaje = posicionYPersonaje;
        this.velocidadPersonaje = velocidadPersonaje;
        this.vidaPersonaje = vidaPersonaje;
        this.tamañoPersonaje = tamañoPersonaje;
        this.imagenPersonaje = imagenPersonaje;
    }

    public void setPosicionXPersonaje(int posicionXPersonaje) {
        this.posicionXPersonaje = posicionXPersonaje;
    }

    public void setPosicionYPersonaje(int posicionYPersonaje) {
        this.posicionYPersonaje = posicionYPersonaje;
    }

    public void setTamañoPersonaje(int tamañoPersonaje) {
        this.tamañoPersonaje = tamañoPersonaje;
    }

    public void setNombrePersonaje(String nombrePersonaje) {
        this.nombrePersonaje = nombrePersonaje;
    }

    public void setVelocidadPersonaje(int velocidadPersonaje) {
        this.velocidadPersonaje = velocidadPersonaje;
    }

    public void setVidaPersonaje(int vidaPersonaje) {this.vidaPersonaje = Math.max(0, vidaPersonaje);}

    public void dañoPersonaje(int dañoPersonaje) {
        this.dañoPersonaje = dañoPersonaje;
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

    public int getTamañoPersonaje() {
        return tamañoPersonaje;
    }

    public String getNombrePersonaje() {
        return nombrePersonaje;
    }

    public int getVelocidadPersonaje() {
        return velocidadPersonaje;
    }

    public int getVidaPersonaje() {
        return vidaPersonaje;
    }

    public int dañoPersonaje() {
        return dañoPersonaje;
    }

    public Image getImagenPersonaje() {
        return imagenPersonaje;
    }

    public void moverPersonaje(int dx, int dy) {
        this.posicionXPersonaje += dx;
        this.posicionYPersonaje += dy;
    }
}