package hunterTreasure.modelo;

import java.awt.*;
import java.util.ArrayList;

public class Enemigo {
    private String nombreEnemigo;
    private int velocidadEnemigo;
    private int vidaEnemigo;
    private int dañoEnemigo;
    private int tamañoEnemigo;
    private boolean portadorLlave;
    private int posicionXEnemigo;
    private int posicionYEnemigo;
    private int tipoAtaque;
    private Image ImagenEnemigo;

    public Enemigo(String nombreEnemigo, int velocidadEnemigo, int vidaEnemigo, int dañoEnemigo, int tamañoEnemigo, boolean portadorLlave, int posicionXEnemigo, int posicionYEnemigo, int tipoAtaque, Image imagenEnemigo) {
        this.nombreEnemigo = nombreEnemigo;
        this.velocidadEnemigo = velocidadEnemigo;
        this.vidaEnemigo = vidaEnemigo;
        this.dañoEnemigo = dañoEnemigo;
        this.tamañoEnemigo = tamañoEnemigo;
        this.portadorLlave = portadorLlave;
        this.posicionXEnemigo = posicionXEnemigo;
        this.posicionYEnemigo = posicionYEnemigo;
        this.tipoAtaque = tipoAtaque;
        ImagenEnemigo = imagenEnemigo;
    }

    public String getNombreEnemigo() {
        return nombreEnemigo;
    }

    public void setNombreEnemigo(String nombreEnemigo) {
        this.nombreEnemigo = nombreEnemigo;
    }

    public int getVelocidadEnemigo() {
        return velocidadEnemigo;
    }

    public void setVelocidadEnemigo(int velocidadEnemigo) {
        this.velocidadEnemigo = velocidadEnemigo;
    }

    public int getVidaEnemigo() {
        return vidaEnemigo;
    }

    public void setVidaEnemigo(int vidaEnemigo) {
        this.vidaEnemigo = vidaEnemigo;
    }

    public int getDañoEnemigo() {
        return dañoEnemigo;
    }

    public void setDañoEnemigo(int dañoEnemigo) {
        this.dañoEnemigo = dañoEnemigo;
    }

    public int getTamañoEnemigo() {
        return tamañoEnemigo;
    }

    public void setTamañoEnemigo(int tamañoEnemigo) {
        this.tamañoEnemigo = tamañoEnemigo;
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

    public void setPosicionXEnemigo(int posicionXEnemigo) {
        this.posicionXEnemigo = posicionXEnemigo;
    }

    public int getPosicionYEnemigo() {
        return posicionYEnemigo;
    }

    public void setPosicionYEnemigo(int posicionYEnemigo) {
        this.posicionYEnemigo = posicionYEnemigo;
    }

    public int getTipoAtaque() {
        return tipoAtaque;
    }

    public void setTipoAtaque(int tipoAtaque) {
        this.tipoAtaque = tipoAtaque;
    }

    public Image getImagenEnemigo() {
        return ImagenEnemigo;
    }

    public void setImagenEnemigo(Image imagenEnemigo) {
        ImagenEnemigo = imagenEnemigo;
    }

    public void moverEnemigo(int posJugadorX, int posJugadorY){
        if (posJugadorX > posicionXEnemigo) posicionXEnemigo += velocidadEnemigo;
        if (posJugadorX < posicionXEnemigo) posicionXEnemigo -= velocidadEnemigo;
        if (posJugadorY > posicionYEnemigo) posicionYEnemigo += velocidadEnemigo;
        if (posJugadorY < posicionYEnemigo) posicionYEnemigo -= velocidadEnemigo;
    }
}