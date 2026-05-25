package hunterTreasure.interfaz;

import hunterTreasure.datos.ConexionDB;
import hunterTreasure.modelo.Enemigo;
import hunterTreasure.modelo.ObjetoColeccionable;
import hunterTreasure.modelo.Personaje;
import hunterTreasure.modelo.Proyectil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class VistaJuego extends JPanel {

    public static final int ALTO_HUD = 60;
    public static final int LIMITE_X = 40;

    private final String NOMBRE_USUARIO;
    private final PrincipalWindow principalWindow;
    private final Random random = new Random();

    private Image fondoArena, fondoAgua, barco;
    private Image imgBarril, imgCofre, imgMapa, imgMoneda, imgLlave, imgPuertaAbierta, imgPuertaCerrada;

    private Personaje personaje;
    private final ArrayList<Enemigo> enemigos = new ArrayList<>();
    private final ArrayList<Proyectil> proyectiles = new ArrayList<>();
    private final ArrayList<ObjetoColeccionable> objetoColeccionable = new ArrayList<>();

    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private int contadorInmunidad = 0;
    private int ultimaDireccionX = 5;
    private int ultimaDireccionY = 0;
    private int puntuacionTotal = 0;
    private int enemigosEliminados = 0;
    private boolean tieneLlave = false;
    private boolean inmunidad = false;
    private Timer timer;

    public VistaJuego(PrincipalWindow principalWindow, String NOMBRE_USUARIO) {
        this.principalWindow = principalWindow;
        this.NOMBRE_USUARIO = NOMBRE_USUARIO;

        cargarRecursos();
        inicializarPersonaje();
        inicializarEnemigos();
        inicializarColeccionables();
        adaptersListeners();
        configurarBucleJuego();
    }

    private void cargarRecursos() {
        fondoArena = obtenerImagen("/imagenes/mapas/arenaMapa.jpg");
        fondoAgua = obtenerImagen("/imagenes/mapas/aguaMapa.jpg");
        barco = obtenerImagen("/imagenes/elementosJuego/barcoTierra.png");
        imgBarril = obtenerImagen("/imagenes/objetos/barril.png");
        imgCofre = obtenerImagen("/imagenes/objetos/cofre.png");
        imgMapa = obtenerImagen("/imagenes/objetos/mapa.png");
        imgMoneda = obtenerImagen("/imagenes/objetos/moneda.png");
        imgPuertaAbierta = obtenerImagen("/imagenes/elementosJuego/puertaAbierta.png");
        imgPuertaCerrada = obtenerImagen("/imagenes/elementosJuego/puertaCerrada.png");
        imgLlave = obtenerImagen("/imagenes/objetos/llave.png");
    }

    private Image obtenerImagen(String ruta) {
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(ruta))).getImage();
    }

    private void inicializarPersonaje() {
        Image imgPersonaje = obtenerImagen("/imagenes/personajes/pirata.png");
        personaje = new Personaje(108, 280, 5, 3, 150, imgPersonaje);
    }

    private void inicializarEnemigos() {
        enemigos.add(new Enemigo(2, 10, 170, false, random.nextInt(400) + 400, random.nextInt(570) + ALTO_HUD, obtenerImagen("/imagenes/enemigos/calamar.png")));
        enemigos.add(new Enemigo(2, 13, 130, false, random.nextInt(400) + 400, random.nextInt(570) + ALTO_HUD, obtenerImagen("/imagenes/enemigos/cangrejoSmash.png")));
        enemigos.add(new Enemigo(1, 20, 130, false, random.nextInt(400) + 400, random.nextInt(570) + ALTO_HUD, obtenerImagen("/imagenes/enemigos/EsqueletoEnemigo.png")));
        enemigos.add(new Enemigo(2, 16, 150, false, random.nextInt(400) + 400, random.nextInt(570) + ALTO_HUD, obtenerImagen("/imagenes/enemigos/pirataAsesino.gif")));

        if (!enemigos.isEmpty()) {
            int indiceAleatorio = random.nextInt(enemigos.size());
            enemigos.get(indiceAleatorio).setPortadorLlave(true);
        }
    }

    private void inicializarColeccionables() {
        agregarColeccionablesAleatorios("barril", 2, 40);
        agregarColeccionablesAleatorios("cofre", 1, 45);
        agregarColeccionablesAleatorios("mapa", 1, 40);
        agregarColeccionablesAleatorios("moneda", 2, 40);
    }

    private void agregarColeccionablesAleatorios(String tipo, int cantidad, int tamano) {
        for (int i = 0; i < cantidad; i++) {
            int x = random.nextInt(670) + LIMITE_X;
            int y = random.nextInt(600) + ALTO_HUD;
            objetoColeccionable.add(new ObjetoColeccionable(tipo, x, y, tamano));
        }
    }

    private void adaptersListeners() {
        setFocusable(true);
        addHierarchyListener(_ -> {
            if (isShowing()) requestFocusInWindow();
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> {
                        upPressed = true; ultimaDireccionX = 0; ultimaDireccionY = -personaje.getVelocidadPersonaje();
                    }
                    case KeyEvent.VK_DOWN -> {
                        downPressed = true; ultimaDireccionX = 0; ultimaDireccionY = personaje.getVelocidadPersonaje();
                    }
                    case KeyEvent.VK_LEFT -> {
                        leftPressed = true; ultimaDireccionX = -personaje.getVelocidadPersonaje(); ultimaDireccionY = 0;
                    }
                    case KeyEvent.VK_RIGHT -> {
                        rightPressed = true; ultimaDireccionX = personaje.getVelocidadPersonaje(); ultimaDireccionY = 0;
                    }
                    case KeyEvent.VK_SPACE -> dispararProyectil();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> upPressed = false;
                    case KeyEvent.VK_DOWN -> downPressed = false;
                    case KeyEvent.VK_LEFT -> leftPressed = false;
                    case KeyEvent.VK_RIGHT -> rightPressed = false;
                }
            }
        });
    }

    private void dispararProyectil() {
        int posX = personaje.getPosicionXPersonaje() + personaje.getTamanoPersonaje() / 2;
        int posY = personaje.getPosicionYPersonaje() + personaje.getTamanoPersonaje() / 2;
        proyectiles.add(new Proyectil(posX, posY, ultimaDireccionX * 2, ultimaDireccionY * 2, 10));
    }

    private void configurarBucleJuego() {
        // 60fps = ~16ms
        timer = new Timer(16, _ -> {
            moverJugador();
            moverEnemigos();
            moverProyectiles();
            aplicarInmunidadYColisiones();
            actualizarColeccionables();
            verificarCondicionesFinDeJuego();
            repaint();
        });
        timer.start();
    }

    private void moverJugador() {
        int vel = personaje.getVelocidadPersonaje();
        if (upPressed) personaje.moverPersonaje(0, -vel);
        if (downPressed) personaje.moverPersonaje(0, +vel);
        if (leftPressed) personaje.moverPersonaje(-vel, 0);
        if (rightPressed) personaje.moverPersonaje(+vel, 0);

        if (personaje.getPosicionXPersonaje() < LIMITE_X) personaje.setPosicionXPersonaje(LIMITE_X);
        if (personaje.getPosicionXPersonaje() > getWidth() - personaje.getTamanoPersonaje())
            personaje.setPosicionXPersonaje(getWidth() - personaje.getTamanoPersonaje());
        if (personaje.getPosicionYPersonaje() < ALTO_HUD) personaje.setPosicionYPersonaje(ALTO_HUD);
        if (personaje.getPosicionYPersonaje() > getHeight() - personaje.getTamanoPersonaje())
            personaje.setPosicionYPersonaje(getHeight() - personaje.getTamanoPersonaje());
    }

    private void moverEnemigos() {
        enemigos.forEach(enemigo -> enemigo.moverEnemigo(personaje.getPosicionXPersonaje(), personaje.getPosicionYPersonaje()));
        enemigos.removeIf(enemigo -> enemigo.getVidaEnemigo() <= 0);
    }

    private void moverProyectiles() {
        proyectiles.forEach(Proyectil::mover);

        for (int i = 0; i < proyectiles.size(); i++) {
            Proyectil proyectil = proyectiles.get(i);
            Rectangle areaProyectil = new Rectangle(proyectil.getPosicionXDisparo(), proyectil.getPosicionYDisparo(), proyectil.getTamanoDisparo(), proyectil.getTamanoDisparo());

            for (Enemigo enemigo : enemigos) {
                Rectangle areaEnemigo = new Rectangle(
                        enemigo.getPosicionXEnemigo() + personaje.getTamanoPersonaje() / 4,
                        enemigo.getPosicionYEnemigo() + personaje.getTamanoPersonaje() / 4,
                        enemigo.getTamanoEnemigo() - personaje.getTamanoPersonaje() / 4 * 2,
                        enemigo.getTamanoEnemigo() - personaje.getTamanoPersonaje() / 4 * 2
                );

                if (areaProyectil.intersects(areaEnemigo)) {
                    enemigo.setVidaEnemigo(enemigo.getVidaEnemigo() - 1);

                    if (enemigo.getVidaEnemigo() <= 0) {
                        enemigosEliminados++;
                        puntuacionTotal += 10;
                        if (enemigo.isPortadorLlave()) {
                            objetoColeccionable.add(new ObjetoColeccionable("llave", enemigo.getPosicionXEnemigo(), enemigo.getPosicionYEnemigo(), 35));
                        }
                    }
                    proyectiles.remove(i);
                    i--;
                    break;
                }
            }
        }

        proyectiles.removeIf(proyectil ->
                proyectil.getPosicionXDisparo() > getWidth() || proyectil.getPosicionXDisparo() < LIMITE_X ||
                        proyectil.getPosicionYDisparo() < ALTO_HUD || proyectil.getPosicionYDisparo() > getHeight());
    }

    private void aplicarInmunidadYColisiones() {
        if (inmunidad) {
            contadorInmunidad--;
            if (contadorInmunidad <= 0) inmunidad = false;
        }

        if (!inmunidad) {
            for (Enemigo enemigo : enemigos) {
                Rectangle areaPersonaje = new Rectangle(
                        personaje.getPosicionXPersonaje() + enemigo.getTamanoEnemigo() / 4,
                        personaje.getPosicionYPersonaje() + enemigo.getTamanoEnemigo() / 4,
                        personaje.getTamanoPersonaje() - enemigo.getTamanoEnemigo() / 4 * 2,
                        personaje.getTamanoPersonaje() - enemigo.getTamanoEnemigo() / 4 * 2
                );

                Rectangle areaEnemigo = new Rectangle(
                        enemigo.getPosicionXEnemigo() + personaje.getTamanoPersonaje() / 4,
                        enemigo.getPosicionYEnemigo() + personaje.getTamanoPersonaje() / 4,
                        enemigo.getTamanoEnemigo() - personaje.getTamanoPersonaje() / 4 * 2,
                        enemigo.getTamanoEnemigo() - personaje.getTamanoPersonaje() / 4 * 2
                );

                if (areaPersonaje.intersects(areaEnemigo)) {
                    personaje.setVidaPersonaje(personaje.getVidaPersonaje() - 1);
                    inmunidad = true;
                    contadorInmunidad = 187;
                    break;
                }
            }
        }
    }

    private void actualizarColeccionables() {
        for (ObjetoColeccionable objeto : objetoColeccionable) {
            if (!objeto.isRecolectado()) {
                Rectangle areaObjeto = new Rectangle(objeto.getPosicionXObjeto(), objeto.getPosicionYObjeto(), objeto.getTamanoObjeto() - 20, objeto.getTamanoObjeto() - 20);
                int reduccionHitBoxPersonaje = personaje.getTamanoPersonaje() / 3;
                Rectangle areaRecoleccion = new Rectangle(
                        personaje.getPosicionXPersonaje() + reduccionHitBoxPersonaje,
                        personaje.getPosicionYPersonaje() + reduccionHitBoxPersonaje,
                        personaje.getTamanoPersonaje() - (reduccionHitBoxPersonaje * 2),
                        personaje.getTamanoPersonaje() - (reduccionHitBoxPersonaje * 2)
                );

                if (areaRecoleccion.intersects(areaObjeto)) {
                    objeto.setRecolectado(true);
                    if (objeto.getTipo().equalsIgnoreCase("llave")) {
                        tieneLlave = true;
                    }
                    switch (objeto.getTipo().toLowerCase()) {
                        case "moneda" -> puntuacionTotal += 15;
                        case "barril" -> puntuacionTotal += 10;
                        case "mapa" -> puntuacionTotal += 5;
                        case "cofre" -> puntuacionTotal += 50;
                    }
                }
            }
        }
    }

    private void verificarCondicionesFinDeJuego() {
        if (tieneLlave) {
            Rectangle areaPuerta = new Rectangle(720, 80, 50, 50);
            Rectangle areaPersonaje = new Rectangle(personaje.getPosicionXPersonaje(), personaje.getPosicionYPersonaje(), personaje.getTamanoPersonaje(), personaje.getTamanoPersonaje());

            if (areaPersonaje.intersects(areaPuerta)) {
                timer.stop();
                ConexionDB.registrarPartida(NOMBRE_USUARIO, puntuacionTotal, enemigosEliminados);
                principalWindow.cambiarPanel(new GameVictory(principalWindow, NOMBRE_USUARIO));
            }
        }

        if (personaje.getVidaPersonaje() == 0) {
            timer.stop();
            ConexionDB.registrarPartida(NOMBRE_USUARIO, puntuacionTotal, enemigosEliminados);
            principalWindow.cambiarPanel(new GameOver(principalWindow, NOMBRE_USUARIO));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarEscenario(g);
        dibujarHUD(g);
        dibujarEntidades(g);
    }

    private void dibujarEscenario(Graphics g) {
        int anchoAgua = getWidth() / 9;
        int anchoArena = getWidth() - anchoAgua;
        g.drawImage(fondoAgua, 0, 0, anchoAgua, getHeight(), this);
        g.drawImage(fondoArena, anchoAgua, 0, anchoArena, getHeight(), this);
        g.drawImage(barco, anchoAgua - 75, getHeight() / 2 - 75, 150, 150, this);

        if (tieneLlave) {
            g.drawImage(imgPuertaAbierta, 720, 80, 50, 50, this);
        } else {
            g.drawImage(imgPuertaCerrada, 720, 80, 50, 50, this);
        }
    }

    private void dibujarHUD(Graphics g) {
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, getWidth(), ALTO_HUD);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Jugador: " + NOMBRE_USUARIO, 20, 35);
        g.drawString("Vidas: " + personaje.getVidaPersonaje(), 250, 35);
        g.drawString("Enemigos: " + enemigosEliminados, 400, 35);
        g.drawString("Puntos: " + puntuacionTotal + " pts", 570, 35);
    }

    private void dibujarEntidades(Graphics g) {
        for (ObjetoColeccionable objeto : objetoColeccionable) {
            if (!objeto.isRecolectado()) {
                Image img = switch (objeto.getTipo().toLowerCase()) {
                    case "barril" -> imgBarril;
                    case "cofre" -> imgCofre;
                    case "mapa" -> imgMapa;
                    case "moneda" -> imgMoneda;
                    case "llave" -> imgLlave;
                    default -> null;
                };
                if (img != null) g.drawImage(img, objeto.getPosicionXObjeto(), objeto.getPosicionYObjeto(), objeto.getTamanoObjeto(), objeto.getTamanoObjeto(), this);
            }
        }

        enemigos.forEach(e -> g.drawImage(e.getImagenEnemigo(), e.getPosicionXEnemigo(), e.getPosicionYEnemigo(), e.getTamanoEnemigo(), e.getTamanoEnemigo(), this));

        g.setColor(Color.BLUE);
        proyectiles.forEach(p -> g.fillOval(p.getPosicionXDisparo(), p.getPosicionYDisparo(), p.getTamanoDisparo(), p.getTamanoDisparo()));

        if (!inmunidad || contadorInmunidad % 2 == 0) {
            g.drawImage(personaje.getImagenPersonaje(), personaje.getPosicionXPersonaje(), personaje.getPosicionYPersonaje(), personaje.getTamanoPersonaje(), personaje.getTamanoPersonaje(), this);
        }
    }
}