package hunterTreasure.interfaz;

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
    public static final int LIMITE_X = 89;

    private final String NOMBRE_USUARIO;
    private final Image FONDO_ARENA;
    private final Image FONDO_AGUA;
    private final Image barco;
    private final Personaje personaje;
    private Timer timer;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private final ArrayList<Enemigo> enemigos;
    private boolean inmunidad = false;
    private int contadorInmunidad = 0;
    private final ArrayList<Proyectil> proyectiles = new ArrayList<>();
    private int ultimaDireccionX = 5;
    private int ultimaDireccionY = 0;
    private final ArrayList<ObjetoColeccionable> objetoColeccionable = new ArrayList<>();
    private final Image imgBarril;
    private final Image imgCofre;
    private final Image imgMapa;
    private final Image imgMoneda;


    public VistaJuego(PrincipalWindow principalWindow, String NOMBRE_USUARIO) {
        this.NOMBRE_USUARIO = NOMBRE_USUARIO;

        FONDO_ARENA = new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagenes/mapas/arenaMapa.jpg"))).getImage();
        FONDO_AGUA = new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagenes/mapas/aguaMapa.jpg"))).getImage();
        barco = new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagenes/elementosJuego/barcoTierra.png"))).getImage();

        Image imgPersonaje = new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagenes/personajes/pirata.png"))).getImage();
        personaje = new Personaje(108, 280, 5, 3, 150, imgPersonaje);

        Image imgEnemigoCalamar = new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagenes/enemigos/calamar.png"))).getImage();
        Image imgEnemigoCangrejo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagenes/enemigos/cangrejoSmash.png"))).getImage();
        Image imgEnemigoEsqueleto = new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagenes/enemigos/EsqueletoEnemigo.png"))).getImage();
        Image imgEnemigoPirata = new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagenes/enemigos/pirataAsesino.gif"))).getImage();

        imgBarril = new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagenes/objetos/barril.png"))).getImage();
        imgCofre = new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagenes/objetos/cofre.png"))).getImage();
        imgMapa = new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagenes/objetos/mapa.png"))).getImage();
        imgMoneda = new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagenes/objetos/moneda.png"))).getImage();


        Random random = new Random();
        random.nextInt(400);
        random.nextInt(700 - ALTO_HUD - 130);
        enemigos = new ArrayList<>();

        enemigos.add(new Enemigo("Calamar", 2, 10, 2, 170, false,
                random.nextInt(400) + 400,
                random.nextInt(570) + ALTO_HUD,
                2, imgEnemigoCalamar));

        enemigos.add(new Enemigo("Cangrejo", 2, 13, 1, 130, false,
                random.nextInt(400) + 400,
                random.nextInt(570) + ALTO_HUD,
                1, imgEnemigoCangrejo));

        enemigos.add(new Enemigo("Esqueleto", 1, 20, 1, 130, true,
                random.nextInt(400) + 400,
                random.nextInt(570) + ALTO_HUD,
                1, imgEnemigoEsqueleto));

        enemigos.add(new Enemigo("Pirata", 2, 16, 2, 150, false,
                random.nextInt(400) + 400,
                random.nextInt(570) + ALTO_HUD,
                2, imgEnemigoPirata));

        for (int i = 0; i < 2; i++) {
            int x = random.nextInt(670) + LIMITE_X;
            int y = random.nextInt(600) + ALTO_HUD;
            objetoColeccionable.add(new ObjetoColeccionable("barril", x, y, 40));
        }

        for (int i = 0; i < 1; i++) {
            int x = random.nextInt(670) + LIMITE_X;
            int y = random.nextInt(600) + ALTO_HUD;
            objetoColeccionable.add(new ObjetoColeccionable("cofre", x, y, 45));
        }

        for (int i = 0; i < 1; i++) {
            int x = random.nextInt(670) + LIMITE_X;
            int y = random.nextInt(600) + ALTO_HUD;
            objetoColeccionable.add(new ObjetoColeccionable("mapa", x, y, 40));
        }

        for (int i = 0; i < 2; i++) {
            int x = random.nextInt(670) + LIMITE_X;
            int y = random.nextInt(600) + ALTO_HUD;
            objetoColeccionable.add(new ObjetoColeccionable("moneda", x, y, 40));
        }

        setFocusable(true);
        addHierarchyListener(_ -> {
            if (isShowing()) requestFocusInWindow();
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    upPressed = true;
                    ultimaDireccionX = 0;
                    ultimaDireccionY = -personaje.getVelocidadPersonaje();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    downPressed = true;
                    ultimaDireccionX = 0;
                    ultimaDireccionY = personaje.getVelocidadPersonaje();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    leftPressed = true;
                    ultimaDireccionX = -personaje.getVelocidadPersonaje();
                    ultimaDireccionY = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rightPressed = true;
                    ultimaDireccionX = personaje.getVelocidadPersonaje();
                    ultimaDireccionY = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    int posX = personaje.getPosicionXPersonaje() + personaje.getTAMANO_PERSONAJE() / 2;
                    int posY = personaje.getPosicionYPersonaje() + personaje.getTAMANO_PERSONAJE() / 2;

                    proyectiles.add(new Proyectil(posX, posY, ultimaDireccionX * 2, ultimaDireccionY * 2, 10));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_UP) upPressed = false;
                if (e.getKeyCode() == KeyEvent.VK_DOWN) downPressed = false;
                if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = false;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = false;
            }
        });

        // 60fps = 16ms
        timer = new Timer(16, _ -> {
            int vel = personaje.getVelocidadPersonaje();
            if (upPressed) personaje.moverPersonaje(0, -vel);
            if (downPressed) personaje.moverPersonaje(0, +vel);
            if (leftPressed) personaje.moverPersonaje(-vel, 0);
            if (rightPressed) personaje.moverPersonaje(+vel, 0);

            //limites
            if (personaje.getPosicionXPersonaje() < LIMITE_X)
                personaje.setPosicionXPersonaje(LIMITE_X);
            if (personaje.getPosicionXPersonaje() > getWidth() - personaje.getTAMANO_PERSONAJE())
                personaje.setPosicionXPersonaje(getWidth() - personaje.getTAMANO_PERSONAJE());
            if (personaje.getPosicionYPersonaje() < ALTO_HUD)
                personaje.setPosicionYPersonaje(ALTO_HUD);
            if (personaje.getPosicionYPersonaje() > getHeight() - personaje.getTAMANO_PERSONAJE())
                personaje.setPosicionYPersonaje(getHeight() - personaje.getTAMANO_PERSONAJE());

            enemigos.forEach(enemigo ->
                    enemigo.moverEnemigo(personaje.getPosicionXPersonaje(), personaje.getPosicionYPersonaje()));

            // bloqueo por eliminación del ConcurrentModificationException
            enemigos.removeIf(enemigo -> enemigo.getVidaEnemigo() <= 0);

            proyectiles.forEach(Proyectil::mover);

            for (int i = 0; i < proyectiles.size(); i++) {
                Proyectil proyectil = proyectiles.get(i);

                Rectangle areaProyectil = new Rectangle(
                        proyectil.getPosicionXDisparo(), proyectil.getPosicionYDisparo(), proyectil.getTamanoDisparo(), proyectil.getTamanoDisparo()
                );

                for (Enemigo enemigo : enemigos) {
                    Rectangle areaEnemigo = new Rectangle(
                            enemigo.getPosicionXEnemigo() + personaje.getTAMANO_PERSONAJE() / 4,
                            enemigo.getPosicionYEnemigo() + personaje.getTAMANO_PERSONAJE() / 4,
                            enemigo.getTamanoEnemigo() - personaje.getTAMANO_PERSONAJE() / 4 * 2,
                            enemigo.getTamanoEnemigo() - personaje.getTAMANO_PERSONAJE() / 4 * 2
                    );

                    if (areaProyectil.intersects(areaEnemigo)) {
                        enemigo.setVidaEnemigo(enemigo.getVidaEnemigo() - 1);

                        proyectiles.remove(i);
                        i--;
                        break;
                    }
                }
            }

            proyectiles.removeIf(proyectil ->
                    proyectil.getPosicionXDisparo() > getWidth() ||
                            proyectil.getPosicionXDisparo() < LIMITE_X ||
                            proyectil.getPosicionYDisparo() < ALTO_HUD ||
                            proyectil.getPosicionYDisparo() > getHeight());

            if (inmunidad) {
                contadorInmunidad--;
                if (contadorInmunidad <= 0) inmunidad = false;
            }

            if (!inmunidad) {
                for (Enemigo enemigo : enemigos) {
                    Rectangle areaPersonaje = new Rectangle(
                            personaje.getPosicionXPersonaje() + enemigo.getTamanoEnemigo() / 4,
                            personaje.getPosicionYPersonaje() + enemigo.getTamanoEnemigo() / 4,
                            personaje.getTAMANO_PERSONAJE() - enemigo.getTamanoEnemigo() / 4 * 2,
                            personaje.getTAMANO_PERSONAJE() - enemigo.getTamanoEnemigo() / 4 * 2
                    );

                    Rectangle areaEnemigo = new Rectangle(
                            enemigo.getPosicionXEnemigo() + personaje.getTAMANO_PERSONAJE() / 4,
                            enemigo.getPosicionYEnemigo() + personaje.getTAMANO_PERSONAJE() / 4,
                            enemigo.getTamanoEnemigo() - personaje.getTAMANO_PERSONAJE() / 4 * 2,
                            enemigo.getTamanoEnemigo() - personaje.getTAMANO_PERSONAJE() / 4 * 2
                    );
                    if (areaPersonaje.intersects(areaEnemigo)) {
                        personaje.setVidaPersonaje(personaje.getVidaPersonaje() - 1);
                        inmunidad = true;
                        contadorInmunidad = 187;
                        break;
                    }
                }
            }

            for (ObjetoColeccionable objeto : objetoColeccionable) {

                if (!objeto.isRecolectado()) {
                    Rectangle areaObjeto = new Rectangle(
                            objeto.getPosicionXObjeto(),
                            objeto.getPosicionYObjeto(),
                            objeto.getTamanoObjeto() - 10 * 2,
                            objeto.getTamanoObjeto() - 10 * 2
                    );

                    int reduccionHitBoxPersonaje = personaje.getTAMANO_PERSONAJE() / 3;
                    Rectangle areaRecoleccion = new Rectangle(
                            personaje.getPosicionXPersonaje() + reduccionHitBoxPersonaje,
                            personaje.getPosicionYPersonaje() + reduccionHitBoxPersonaje,
                            personaje.getTAMANO_PERSONAJE() - (reduccionHitBoxPersonaje * 2),
                            personaje.getTAMANO_PERSONAJE() - (reduccionHitBoxPersonaje * 2)
                    );

                    if (areaRecoleccion.intersects(areaObjeto)) {
                        objeto.setRecolectado(true);
                    }
                }
            }

            if (personaje.getVidaPersonaje() == 0) {
                timer.stop();
                principalWindow.cambiarPanel(new GameOver(principalWindow, NOMBRE_USUARIO));
            }

            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int anchoAgua = getWidth() / 9;
        int anchoArena = getWidth() - anchoAgua;
        int xBarco = anchoAgua - 150 / 2;
        int yBarco = getHeight() / 2 - 150 / 2;
        g.drawImage(FONDO_AGUA, 0, 0, anchoAgua, getHeight(), this);
        g.drawImage(FONDO_ARENA, anchoAgua, 0, anchoArena, getHeight(), this);
        g.drawImage(barco, xBarco, yBarco, 150, 150, this);

        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, getWidth(), ALTO_HUD);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Jugador: " + NOMBRE_USUARIO, 20, 35);
        g.drawString("Vidas: " + personaje.getVidaPersonaje(), 300, 35);
        g.drawString("Enemigos: " + 0, 500, 35);

        for (Enemigo enemigo : enemigos) {
            g.drawImage(enemigo.getImagenEnemigo(), enemigo.getPosicionXEnemigo(), enemigo.getPosicionYEnemigo(),
                    enemigo.getTamanoEnemigo(), enemigo.getTamanoEnemigo(), this);
        }

        if (!inmunidad || contadorInmunidad % 2 == 0) {
            g.drawImage(personaje.getImagenPersonaje(), personaje.getPosicionXPersonaje(), personaje.getPosicionYPersonaje(),
                    personaje.getTAMANO_PERSONAJE(), personaje.getTAMANO_PERSONAJE(), this);
        }

        g.setColor(Color.blue);
        proyectiles.forEach(proyectil ->
                g.fillOval(proyectil.getPosicionXDisparo(), proyectil.getPosicionYDisparo(), proyectil.getTamanoDisparo(), proyectil.getTamanoDisparo()));

        for (ObjetoColeccionable objeto : objetoColeccionable) {
            if (!objeto.isRecolectado()) {
                if (objeto.getTipo().equalsIgnoreCase("barril")) {
                    g.drawImage(
                            imgBarril,
                            objeto.getPosicionXObjeto(),
                            objeto.getPosicionYObjeto(),
                            objeto.getTamanoObjeto(),
                            objeto.getTamanoObjeto(),
                            this);
                } else if (objeto.getTipo().equalsIgnoreCase("cofre")) {
                    g.drawImage(
                            imgCofre,
                            objeto.getPosicionXObjeto(),
                            objeto.getPosicionYObjeto(),
                            objeto.getTamanoObjeto(),
                            objeto.getTamanoObjeto(),
                            this);
                } else if (objeto.getTipo().equalsIgnoreCase("mapa")) {
                    g.drawImage(
                            imgMapa,
                            objeto.getPosicionXObjeto(),
                            objeto.getPosicionYObjeto(),
                            objeto.getTamanoObjeto(),
                            objeto.getTamanoObjeto(),
                            this);
                } else if (objeto.getTipo().equalsIgnoreCase("moneda")) {
                    g.drawImage(
                            imgMoneda,
                            objeto.getPosicionXObjeto(),
                            objeto.getPosicionYObjeto(),
                            objeto.getTamanoObjeto(),
                            objeto.getTamanoObjeto(),
                            this);
                }
            }
        }
    }
}