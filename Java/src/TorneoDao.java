import java.sql.*;
import java.util.List;

public class TorneoDao {

    public void crearTorneo(Torneo torneo) {
        String sql = "INSERT INTO Torneos (nombre, descripcion, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, torneo.getNombre());
            pstmt.setString(2, torneo.getDescripcion());
            pstmt.setString(3, torneo.getFechaInicio());
            pstmt.setString(4, torneo.getFechaFin());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                torneo.setId(rs.getInt(1));
                System.out.println("Torneo creado con ID: " + torneo.getId());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void agregarEquiposATorneo(int torneoId, List<Integer> equipoIds) {
        for (int equipoId : equipoIds) {
            agregarEquipoATorneo(torneoId, equipoId);
        }
    }

    private void agregarEquipoATorneo(int torneoId, int equipoId) {
        String sql = "INSERT INTO Participacion_Y_Clasificacion (torneo_id, equipo_id, posicion, puntos, partidos_jugados, partidos_ganados, partidos_empatados, partidos_perdidos) VALUES (?, ?, ?, 0, 0, 0, 0, 0)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, torneoId);
            pstmt.setInt(2, equipoId);
            pstmt.setInt(3, obtenerNuevaPosicion(torneoId));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private int obtenerNuevaPosicion(int torneoId) {
        String sql = "SELECT COUNT(*) AS total FROM Participacion_Y_Clasificacion WHERE torneo_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, torneoId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") + 1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 1; // Retorna 1 si no se encuentran registros
    }
}
