package hunterTreasure.modelo;

public class ObjetoColeccionable {
    private String tipo;
    private int posicionXObjeto;
    private int posicionYObjeto;
    private int tamanoObjeto;
    private boolean recolectado;

    public ObjetoColeccionable(String tipo, int posicionXObjeto, int posicionYObjeto, int tamanoObjeto) {
        this.tipo = tipo;
        this.posicionXObjeto = posicionXObjeto;
        this.posicionYObjeto = posicionYObjeto;
        this.tamanoObjeto = tamanoObjeto;
        this.recolectado = false;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPosicionXObjeto() {
        return posicionXObjeto;
    }

    public int getPosicionYObjeto() {
        return posicionYObjeto;
    }

    public int getTamanoObjeto() {
        return tamanoObjeto;
    }

    public boolean isRecolectado() {
        return recolectado;
    }

    public void setRecolectado(boolean recolectado) {
        this.recolectado = recolectado;
    }
}