import java.sql.*;
import java.util.List;

public class ClasificacionDao {

    public void verClasificacion(String nombreTorneo) {
        int idTorneo = obtenerIdTorneoPorNombre(nombreTorneo);
        if (idTorneo == -1) {
            System.out.println("Torneo no encontrado.");
            return;
        }

        String sql = "SELECT equipos.nombre AS Equipo, clasificacion.posicion AS Posicion, clasificacion.puntos AS Puntos, clasificacion.partidos_jugados AS PJ, clasificacion.partidos_ganados AS PG, clasificacion.partidos_empatados AS PE, clasificacion.partidos_perdidos AS PP FROM Participacion_Y_Clasificacion clasificacion INNER JOIN Equipos equipos ON clasificacion.equipo_id = equipos.ID_equipo WHERE clasificacion.torneo_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idTorneo);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Pos | Equipo | Puntos | PJ | PG | PE | PP");
            System.out.println("------------------------------------------");
            while (rs.next()) {
                String equipo = rs.getString("Equipo");
                int posicion = rs.getInt("Posicion");
                int puntos = rs.getInt("Puntos");
                int pj = rs.getInt("PJ");
                int pg = rs.getInt("PG");
                int pe = rs.getInt("PE");
                int pp = rs.getInt("PP");
                System.out.println(posicion + " | " + equipo + " | " + puntos + " | " + pj + " | " + pg + " | " + pe + " | " + pp);
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public void agregarClasificaciones(List<Clasificacion> clasificaciones) {
        String sql = "INSERT INTO Participacion_Y_Clasificacion (equipo_id, torneo_id, posicion, puntos, partidos_jugados, partidos_ganados, partidos_empatados, partidos_perdidos) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Clasificacion clasificacion : clasificaciones) {
                pstmt.setInt(1, clasificacion.getEquipoId());
                pstmt.setInt(2, clasificacion.getTorneoId());
                pstmt.setInt(3, clasificacion.getPosicion());
                pstmt.setInt(4, clasificacion.getPuntos());
                pstmt.setInt(5, clasificacion.getPartidosJugados());
                pstmt.setInt(6, clasificacion.getPartidosGanados());
                pstmt.setInt(7, clasificacion.getPartidosEmpatados());
                pstmt.setInt(8, clasificacion.getPartidosPerdidos());
                pstmt.executeUpdate();
            }
            System.out.println("Clasificaciones agregadas correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private int obtenerIdTorneoPorNombre(String nombre) {
        String sql = "SELECT ID_torneo FROM Torneos WHERE nombre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ID_torneo");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1; // Retorna -1 si el torneo no fue encontrado
    }
}
