package hunterTreasure.modelo;

import java.awt.*;

public class Enemigo {
    private int velocidadEnemigo;
    private int vidaEnemigo;
    private int tamanoEnemigo;
    private boolean portadorLlave;
    private int posicionXEnemigo;
    private int posicionYEnemigo;
    private Image ImagenEnemigo;

    public Enemigo(int velocidadEnemigo, int vidaEnemigo, int tamanoEnemigo, boolean portadorLlave, int posicionXEnemigo, int posicionYEnemigo, Image imagenEnemigo) {
        this.velocidadEnemigo = velocidadEnemigo;
        this.vidaEnemigo = vidaEnemigo;
        this.tamanoEnemigo = tamanoEnemigo;
        this.portadorLlave = portadorLlave;
        this.posicionXEnemigo = posicionXEnemigo;
        this.posicionYEnemigo = posicionYEnemigo;
        ImagenEnemigo = imagenEnemigo;
    }

    public int getVidaEnemigo() {
        return vidaEnemigo;
    }

    public void setVidaEnemigo(int vidaEnemigo) {
        this.vidaEnemigo = vidaEnemigo;
    }

    public int getTamanoEnemigo() {
        return tamanoEnemigo;
    }

    public boolean isPortadorLlave() {
        return portadorLlave;
    }

    public void setPortadorLlave(boolean portadorLlave) {
        this.portadorLlave = portadorLlave;
    }

    public int getPosicionXEnemigo() {
        return posicionXEnemigo;
    }

    public int getPosicionYEnemigo() {
        return posicionYEnemigo;
    }

    public Image getImagenEnemigo() {
        return ImagenEnemigo;
    }

    public void moverEnemigo(int posJugadorX, int posJugadorY) {
        if (posJugadorX > posicionXEnemigo) posicionXEnemigo += velocidadEnemigo;
        if (posJugadorX < posicionXEnemigo) posicionXEnemigo -= velocidadEnemigo;
        if (posJugadorY > posicionYEnemigo) posicionYEnemigo += velocidadEnemigo;
        if (posJugadorY < posicionYEnemigo) posicionYEnemigo -= velocidadEnemigo;
    }
}