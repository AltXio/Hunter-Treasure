package hunterTreasure.interfaz;

import hunterTreasure.modelo.Enemigo;
import hunterTreasure.modelo.Personaje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class VistaJuego extends JPanel {

    public static final int ALTO_HUD = 60;
    public static final int LIMITE_X = 89;

    private PrincipalWindow principalWindow;
    private String nombreUsuario;
    private Image fondoArena;
    private Image fondoAgua;
    private Image barco;
    private Image imgPersonaje;
    private Personaje personaje;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private ArrayList<Enemigo> enemigos;
    private boolean inmmunidad = false;
    private int contadorInmunidad = 0;


    public VistaJuego(PrincipalWindow principalWindow, String nombreUsuario) {
        this.principalWindow = principalWindow;
        this.nombreUsuario = nombreUsuario;

        fondoArena = new ImageIcon(getClass().getResource("/imagenes/mapas/arenaMapa.jpg")).getImage();
        fondoAgua = new ImageIcon(getClass().getResource("/imagenes/mapas/aguaMapa.jpg")).getImage();
        barco = new ImageIcon(getClass().getResource("/imagenes/elementosJuego/barcoTierra.png")).getImage();

        imgPersonaje = new ImageIcon(getClass().getResource("/imagenes/personajes/pirata.png")).getImage();
        personaje = new Personaje(108, 280, 5, 3, 150, imgPersonaje);

        Image imgEnemigoCalamar = new ImageIcon(getClass().getResource("/imagenes/enemigos/calamar.png")).getImage();
        Image imgEnemigoCangrejo = new ImageIcon(getClass().getResource("/imagenes/enemigos/cangrejoSmash.png")).getImage();
        Image imgEnemigoEsqueleto = new ImageIcon(getClass().getResource("/imagenes/enemigos/EsqueletoEnemigo.png")).getImage();
        Image imgEnemigoPirata = new ImageIcon(getClass().getResource("/imagenes/enemigos/pirataAsesino.gif")).getImage();

        Random random = new Random();
        int xEnemigo = random.nextInt(400) + 400;
        int yEnemigo = random.nextInt(700 - ALTO_HUD - 130) + ALTO_HUD;
        enemigos = new ArrayList<>();

        enemigos.add(new Enemigo("Calamar", 2, 3, 2, 170, false,
                random.nextInt(400) + 400,
                random.nextInt(570) + ALTO_HUD,
                2, imgEnemigoCalamar));

        enemigos.add(new Enemigo("Cangrejo", 1, 5, 1, 130, false,
                random.nextInt(400) + 400,
                random.nextInt(570) + ALTO_HUD,
                1, imgEnemigoCangrejo));

        enemigos.add(new Enemigo("Esqueleto", 1, 4, 1, 130, true,
                random.nextInt(400) + 400,
                random.nextInt(570) + ALTO_HUD,
                1, imgEnemigoEsqueleto));

        enemigos.add(new Enemigo("Pirata", 1, 5, 3, 150, false,
                random.nextInt(400) + 400,
                random.nextInt(570) + ALTO_HUD,
                2, imgEnemigoPirata));

        setFocusable(true);
        addHierarchyListener(e -> {
            if (isShowing()) requestFocusInWindow();
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_UP) upPressed = true;
                if (e.getKeyCode() == KeyEvent.VK_DOWN) downPressed = true;
                if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = true;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = true;
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
        Timer timer = new Timer(16, e -> {
            int vel = personaje.getVelocidadPersonaje();
            if (upPressed) personaje.moverPersonaje(0, -vel);
            if (downPressed) personaje.moverPersonaje(0, +vel);
            if (leftPressed) personaje.moverPersonaje(-vel, 0);
            if (rightPressed) personaje.moverPersonaje(+vel, 0);

            //limites
            if (personaje.getPosicionXPersonaje() < LIMITE_X)
                personaje.setPosicionXPersonaje(LIMITE_X);
            if (personaje.getPosicionXPersonaje() > getWidth() - personaje.getTamañoPersonaje())
                personaje.setPosicionXPersonaje(getWidth() - personaje.getTamañoPersonaje());
            if (personaje.getPosicionYPersonaje() < ALTO_HUD)
                personaje.setPosicionYPersonaje(ALTO_HUD);
            if (personaje.getPosicionYPersonaje() > getHeight() - personaje.getTamañoPersonaje())
                personaje.setPosicionYPersonaje(getHeight() - personaje.getTamañoPersonaje());

            enemigos.forEach(enemigo -> {
                enemigo.moverEnemigo(personaje.getPosicionXPersonaje(), personaje.getPosicionYPersonaje());
            });

            // si está inmune, decrementar contador
            if (inmmunidad) {
                contadorInmunidad--;
                if (contadorInmunidad <= 0) inmmunidad = false;
            }

// verificar colisión solo si NO es inmune
            if (!inmmunidad) {
                for (Enemigo enemigo : enemigos) {
                    Rectangle areaPersonaje = new Rectangle(
                            personaje.getPosicionXPersonaje() + enemigo.getTamañoEnemigo() / 4,
                            personaje.getPosicionYPersonaje() + enemigo.getTamañoEnemigo() / 4 ,
                            personaje.getTamañoPersonaje() - enemigo.getTamañoEnemigo() / 4 * 2,
                            personaje.getTamañoPersonaje()- enemigo.getTamañoEnemigo() / 4 * 2
                    );

                    Rectangle areaEnemigo = new Rectangle(
                            enemigo.getPosicionXEnemigo() + personaje.getTamañoPersonaje() / 4,
                            enemigo.getPosicionYEnemigo() + personaje.getTamañoPersonaje() / 4,
                            enemigo.getTamañoEnemigo() - personaje.getTamañoPersonaje() / 4 * 2,
                            enemigo.getTamañoEnemigo() - personaje.getTamañoPersonaje() / 4 * 2
                    );
                    if (areaPersonaje.intersects(areaEnemigo)) {
                        personaje.setVidaPersonaje(personaje.getVidaPersonaje() - 1);
                        inmmunidad = true;
                        contadorInmunidad = 187;
                    }
                }
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
        g.drawImage(fondoAgua, 0, 0, anchoAgua, getHeight(), this);
        g.drawImage(fondoArena, anchoAgua, 0, anchoArena, getHeight(), this);
        g.drawImage(barco, xBarco, yBarco, 150, 150, this);

        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, getWidth(), ALTO_HUD);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Jugador: " + nombreUsuario, 20, 35);
        g.drawString("Vidas: " + personaje.getVidaPersonaje(), 300, 35);
        g.drawString("Enemigos: " + 0, 500, 35);

        for (Enemigo enemigo : enemigos) {
            g.drawImage(enemigo.getImagenEnemigo(), enemigo.getPosicionXEnemigo(), enemigo.getPosicionYEnemigo(),
                    enemigo.getTamañoEnemigo(), enemigo.getTamañoEnemigo(), this);
        }

        // en paintComponent al dibujar el personaje
        if (!inmmunidad || contadorInmunidad % 2 == 0) {
            g.drawImage(personaje.getImagenPersonaje(), personaje.getPosicionXPersonaje(), personaje.getPosicionYPersonaje(),
                    personaje.getTamañoPersonaje(), personaje.getTamañoPersonaje(), this);
        }
    }
}