import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntrenadorDao {

    public Entrenador obtenerEntrenadorPorNombre(String nombre) {
        String sql = "SELECT * FROM Entrenadores WHERE nombre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Entrenador(
                        rs.getInt("ID_entrenador"),
                        rs.getString("nombre"),
                        rs.getString("nacionalidad"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("email"),
                        rs.getString("telefono")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void agregarEntrenador(Entrenador entrenador) {
        String sql = "INSERT INTO Entrenadores (nombre, nacionalidad, fecha_nacimiento, email, telefono) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, entrenador.getNombre());
            pstmt.setString(2, entrenador.getNacionalidad());
            pstmt.setString(3, entrenador.getFechaNacimiento());
            pstmt.setString(4, entrenador.getEmail());
            pstmt.setString(5, entrenador.getTelefono());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void actualizarEntrenador(Entrenador entrenador) {
        String sql = "UPDATE Entrenadores SET nombre = ?, nacionalidad = ?, fecha_nacimiento = ?, email = ?, telefono = ? WHERE ID_entrenador = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, entrenador.getNombre());
            pstmt.setString(2, entrenador.getNacionalidad());
            pstmt.setString(3, entrenador.getFechaNacimiento());
            pstmt.setString(4, entrenador.getEmail());
            pstmt.setString(5, entrenador.getTelefono());
            pstmt.setInt(6, entrenador.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarEntrenador(int id) {
        String sql = "DELETE FROM Entrenadores WHERE ID_entrenador = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Entrenador> obtenerTodosLosEntrenadores() {
        List<Entrenador> entrenadores = new ArrayList<>();
        String sql = "SELECT * FROM Entrenadores";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Entrenador entrenador = new Entrenador(
                        rs.getInt("ID_entrenador"),
                        rs.getString("nombre"),
                        rs.getString("nacionalidad"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("email"),
                        rs.getString("telefono")
                );
                entrenadores.add(entrenador);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return entrenadores;
    }
}
