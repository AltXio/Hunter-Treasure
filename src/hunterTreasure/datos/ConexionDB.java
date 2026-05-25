package hunterTreasure.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionDB {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/hunter_treasure?";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql";

    public static void registrarPartida(String jugador, int puntuacion, int eliminados) {

        String insertQy = "INSERT INTO partidas (jugador, puntuacion, enemigos_eliminados) VALUES (?, ?, ?)";

        try (
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(insertQy)) {

            ps.setString(1, jugador);
            ps.setInt(2, puntuacion);
            ps.setInt(3, eliminados);

            int addRow = ps.executeUpdate();
            if (addRow > 0) {
                System.out.println("¡Partida registrada exitosamente!");
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar o insertar en la base de datos");
        }
    }
}