# 🏴‍☠️ Hunter Treasure
--

**Hunter Treasure** es un juego de acción 2D en el que controlas a un pirata que debe sobrevivir a oleadas de enemigos, encontrar la llave del nivel y llegar a la puerta para escapar con el tesoro.

El juego fue desarrollado como proyecto académico para aprender el uso de interfaces gráficas con **Java Swing** y la integración con bases de datos mediante **JDBC y MySQL**.

---

## 🎮 Mecánicas del juego

- Mueve a tu pirata por el mapa usando las **teclas de dirección**
- Dispara proyectiles con la **barra espaciadora**
- Derrota a los enemigos para acumular puntos
- Uno de los enemigos porta la **llave del nivel** de forma aleatoria — encuéntrala
- Con la llave en tu poder, dirígete a la **puerta** para completar el nivel y ganar
- Tienes **3 vidas** — aprovechalas bien!
- Los cofres tambien pueden ser tramposos, ¡ten cuidado!

---

## 🛠️ Tecnologías utilizadas

| Componente | Tecnología |
|---|---|
| Lenguaje | Java 21+ |
| Interfaz gráfica | Java Swing / AWT |
| Base de datos | MySQL |
| Conexión BD | JDBC (`mysql-connector-j-9.7.0`) |
| Entorno de desarrollo | IntelliJ IDEA |
| Control de versiones | Git / GitHub |

---
> [!NOTE]
> El juego registra automáticamente cada partida en una base de datos MySQL al finalizar (victoria | derrota).

### Datos guardados por partida

- Nombre del jugador
- Puntuación total
- Enemigos eliminados
- Fecha y hora de la partida

### Configuración de la BD

1. Ejecuta el script SQL incluido en el proyecto:
```
src/hunterTreasure/datos/hunterTreasure.sql
```

2. El script crea la base de datos y la tabla automáticamente:
```sql
CREATE DATABASE IF NOT EXISTS hunter_treasure;
USE hunter_treasure;

CREATE TABLE IF NOT EXISTS partidas (
    id_partida INT AUTO_INCREMENT PRIMARY KEY,
    jugador VARCHAR(50) NOT NULL,
    puntuacion INT NOT NULL,
    enemigos_eliminados INT NOT NULL,
    fecha_partida TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

3. Verifica que las credenciales en `ConexionDB.java` coincidan con tu instalación de MySQL:
```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/hunter_treasure";
private static final String USER = "root";
private static final String PASSWORD = "tu_contraseña";
```

---
> [!TIP]
> ## 🚀 Instrucciones para ejecutar
> ### Ejecutar el archivo .jar

> [IMPORTANT]
> Asegúrate de tener **Java 21 o superior** instalado
1. Descarga el archivo `HunterTreasure.jar` desde la sección [Releases](/AltXio/Hunter-Treasure/releases/tag/v1.0)
2. Ejecuta el JAR desde la terminal:

```bash
java -jar HunterTreasure.jar
```

O simplemente haz **doble clic** sobre el archivo `.jar` si tu sistema operativo lo permite.

---

## 🎯 Controles

| Tecla | Acción |
|---|---|
| ↑ ↓ ← → | Mover al personaje |
| Espacio | Disparar proyectil |

---

## 📸 Un poco de Hunter Treasure...

### Menú principal
<img width="983" height="867" alt="image" src="https://github.com/user-attachments/assets/c608c1de-cca9-4a4a-bc06-1255ac9389be" />

### Pantalla de juego
![Gameplay](<img width="984" height="867" alt="Hunter Treasure Preview" src="https://github.com/user-attachments/assets/5eee3be0-88e5-440d-bcbf-0cb907919f1e" />)

### Game Over
<img width="983" height="867" alt="image" src="https://github.com/user-attachments/assets/8d43fffe-b419-4d43-bbf1-709c6c8cf9cd" />

### Victoria
<img width="983" height="867" alt="image" src="https://github.com/user-attachments/assets/ed09e7b0-d665-4c1b-90fd-872ea2a1eec9" />

---

## 📄 Licencia
---
MIT
