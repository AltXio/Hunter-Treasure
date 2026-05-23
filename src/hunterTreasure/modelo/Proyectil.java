package hunterTreasure.modelo;

public class Proyectil {
    private int posicionXDisparo;
    private int posicionYDisparo;
    private final int velocidadXDisparo;
    private final int velocidadYDisparo;
    private final int tamanoDisparo;

    public Proyectil(int posicionXDisparo, int posicionYDisparo, int velocidadXDisparo, int velocidadYDisparo, int tamanoDisparo) {
        this.posicionXDisparo = posicionXDisparo;
        this.posicionYDisparo = posicionYDisparo;
        this.velocidadXDisparo = velocidadXDisparo;
        this.velocidadYDisparo = velocidadYDisparo;
        this.tamanoDisparo = tamanoDisparo;
    }

    public void mover() {
        this.posicionXDisparo += velocidadXDisparo;
        this.posicionYDisparo += velocidadYDisparo;
    }

    public int getPosicionXDisparo() {
        return posicionXDisparo;
    }

    public int getPosicionYDisparo() {
        return posicionYDisparo;
    }

    public int getTamanoDisparo() {
        return tamanoDisparo;
    }
}