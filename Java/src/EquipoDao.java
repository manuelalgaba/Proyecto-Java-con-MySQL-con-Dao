import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoDao {

    public Equipo obtenerEquipoPorNombre(String nombre) {
        String sql = "SELECT * FROM Equipos WHERE nombre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Equipo(
                        rs.getInt("ID_equipo"),
                        rs.getString("nombre"),
                        rs.getString("pais"),
                        rs.getString("fundacion"),
                        rs.getString("estadio"),
                        rs.getInt("entrenador_id")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void agregarEquipo(Equipo equipo) {
        String sql = "INSERT INTO Equipos (nombre, pais, fundacion, estadio, entrenador_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, equipo.getNombre());
            pstmt.setString(2, equipo.getPais());
            pstmt.setString(3, equipo.getFundacion());
            pstmt.setString(4, equipo.getEstadio());
            pstmt.setInt(5, equipo.getEntrenadorId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void actualizarEquipo(Equipo equipo) {
        String sql = "UPDATE Equipos SET nombre = ?, pais = ?, fundacion = ?, estadio = ?, entrenador_id = ? WHERE ID_equipo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, equipo.getNombre());
            pstmt.setString(2, equipo.getPais());
            pstmt.setString(3, equipo.getFundacion());
            pstmt.setString(4, equipo.getEstadio());
            pstmt.setInt(5, equipo.getEntrenadorId());
            pstmt.setInt(6, equipo.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarEquipo(int id) {
        String sql = "DELETE FROM Equipos WHERE ID_equipo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Equipo> obtenerTodosLosEquipos() {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM Equipos";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Equipo equipo = new Equipo(
                        rs.getInt("ID_equipo"),
                        rs.getString("nombre"),
                        rs.getString("pais"),
                        rs.getString("fundacion"),
                        rs.getString("estadio"),
                        rs.getInt("entrenador_id")
                );
                equipos.add(equipo);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return equipos;
    }
}
