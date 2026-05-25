CREATE DATABASE IF NOT EXISTS hunter_treasure;
USE hunter_treasure;

CREATE TABLE IF NOT EXISTS partidas (
    id_partida INT AUTO_INCREMENT PRIMARY KEY,
    jugador VARCHAR(50) NOT NULL,
    puntuacion INT NOT NULL,
    enemigos_eliminados INT NOT NULL,
    fecha_partida TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

SELECT * FROM partidas;