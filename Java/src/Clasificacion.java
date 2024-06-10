public class Clasificacion {
    private int equipoId;
    private int torneoId;
    private int posicion;
    private int puntos;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;

    public Clasificacion(int equipoId, int torneoId, int posicion, int puntos, int partidosJugados, int partidosGanados, int partidosEmpatados, int partidosPerdidos) {
        this.equipoId = equipoId;
        this.torneoId = torneoId;
        this.posicion = posicion;
        this.puntos = puntos;
        this.partidosJugados = partidosJugados;
        this.partidosGanados = partidosGanados;
        this.partidosEmpatados = partidosEmpatados;
        this.partidosPerdidos = partidosPerdidos;
    }

    public int getEquipoId() {
        return equipoId;
    }

    public int getTorneoId() {
        return torneoId;
    }

    public int getPosicion() {
        return posicion;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }
}
