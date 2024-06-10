import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JugadorDao {

    public Jugador obtenerJugadorPorNombre(String nombre) {
        String sql = "SELECT j.*, e.nombre AS equipo_nombre, t.nombre AS entrenador_nombre " +
                     "FROM Jugadores j " +
                     "JOIN Equipos e ON j.equipo_id = e.ID_equipo " +
                     "JOIN Entrenadores t ON j.entrenador_id = t.ID_entrenador " +
                     "WHERE j.nombre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Jugador(
                        rs.getInt("ID_jugador"),
                        rs.getString("nombre"),
                        rs.getString("nacionalidad"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("posicion"),
                        rs.getString("equipo_nombre"),
                        rs.getString("entrenador_nombre")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void agregarJugador(Jugador jugador) {
        String sql = "INSERT INTO Jugadores (nombre, nacionalidad, fecha_nacimiento, posicion, equipo_id, entrenador_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, jugador.getNombre());
            pstmt.setString(2, jugador.getNacionalidad());
            pstmt.setString(3, jugador.getFechaNacimiento());
            pstmt.setString(4, jugador.getPosicion());
            pstmt.setInt(5, obtenerEquipoIdPorNombre(jugador.getEquipoNombre()));
            pstmt.setInt(6, obtenerEntrenadorIdPorNombre(jugador.getEntrenadorNombre()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void actualizarJugador(Jugador jugador) {
        String sql = "UPDATE Jugadores SET nombre = ?, nacionalidad = ?, fecha_nacimiento = ?, posicion = ?, equipo_id = ?, entrenador_id = ? WHERE ID_jugador = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, jugador.getNombre());
            pstmt.setString(2, jugador.getNacionalidad());
            pstmt.setString(3, jugador.getFechaNacimiento());
            pstmt.setString(4, jugador.getPosicion());
            pstmt.setInt(5, obtenerEquipoIdPorNombre(jugador.getEquipoNombre()));
            pstmt.setInt(6, obtenerEntrenadorIdPorNombre(jugador.getEntrenadorNombre()));
            pstmt.setInt(7, jugador.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarJugador(String nombre) {
        String sql = "DELETE FROM Jugadores WHERE nombre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Jugador> obtenerJugadoresPorEquipo(int equipoId) {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT j.*, e.nombre AS equipo_nombre, t.nombre AS entrenador_nombre " +
                     "FROM Jugadores j " +
                     "JOIN Equipos e ON j.equipo_id = e.ID_equipo " +
                     "JOIN Entrenadores t ON j.entrenador_id = t.ID_entrenador " +
                     "WHERE j.equipo_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, equipoId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Jugador jugador = new Jugador(
                        rs.getInt("ID_jugador"),
                        rs.getString("nombre"),
                        rs.getString("nacionalidad"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("posicion"),
                        rs.getString("equipo_nombre"),
                        rs.getString("entrenador_nombre")
                );
                jugadores.add(jugador);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return jugadores;
    }

    public List<Jugador> obtenerPlantillaOrdenada(int equipoId, Comparator<Jugador> comparator) {
        List<Jugador> jugadores = obtenerJugadoresPorEquipo(equipoId);
        jugadores.sort(comparator);
        return jugadores;
    }

    private int obtenerEquipoIdPorNombre(String nombre) {
        String sql = "SELECT ID_equipo FROM Equipos WHERE nombre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ID_equipo");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    private int obtenerEntrenadorIdPorNombre(String nombre) {
        String sql = "SELECT ID_entrenador FROM Entrenadores WHERE nombre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ID_entrenador");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    
    public void borrarJugador(int id) {
        String sql = "DELETE FROM Jugadores WHERE ID_jugador = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
