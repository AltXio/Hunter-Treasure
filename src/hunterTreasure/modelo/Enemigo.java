package hunterTreasure.modelo;

import java.awt.*;

public class Enemigo {
    private final int velocidadEnemigo;
    private final int tamanoEnemigo;
    private final Image imagenEnemigo;
    private int vidaEnemigo;
    private boolean portadorLlave;
    private int posicionXEnemigo;
    private int posicionYEnemigo;

    public Enemigo(int velocidadEnemigo, int vidaEnemigo, int tamanoEnemigo, boolean portadorLlave,
                   int posicionXEnemigo, int posicionYEnemigo, Image imagenEnemigo) {
        this.velocidadEnemigo = velocidadEnemigo;
        this.vidaEnemigo = vidaEnemigo;
        this.tamanoEnemigo = tamanoEnemigo;
        this.portadorLlave = portadorLlave;
        this.posicionXEnemigo = posicionXEnemigo;
        this.posicionYEnemigo = posicionYEnemigo;
        this.imagenEnemigo = imagenEnemigo;
    }

    public void moverEnemigo(int jugadorX, int jugadorY) {
        if (this.posicionXEnemigo < jugadorX) this.posicionXEnemigo += velocidadEnemigo;
        if (this.posicionXEnemigo > jugadorX) this.posicionXEnemigo -= velocidadEnemigo;
        if (this.posicionYEnemigo < jugadorY) this.posicionYEnemigo += velocidadEnemigo;
        if (this.posicionYEnemigo > jugadorY) this.posicionYEnemigo -= velocidadEnemigo;
    }

    public int getVidaEnemigo() { return vidaEnemigo; }
    public void setVidaEnemigo(int vidaEnemigo) { this.vidaEnemigo = vidaEnemigo; }
    public int getTamanoEnemigo() { return tamanoEnemigo; }
    public boolean isPortadorLlave() { return portadorLlave; }
    public void setPortadorLlave(boolean portadorLlave) { this.portadorLlave = portadorLlave; }
    public int getPosicionXEnemigo() { return posicionXEnemigo; }
    public int getPosicionYEnemigo() { return posicionYEnemigo; }
    public Image getImagenEnemigo() { return imagenEnemigo; }
}