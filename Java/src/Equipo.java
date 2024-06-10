public class Equipo {
    private int id;
    private String nombre;
    private String pais;
    private String fundacion;
    private String estadio;
    private int entrenadorId;

    public Equipo(int id, String nombre, String pais, String fundacion, String estadio, int entrenadorId) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.fundacion = fundacion;
        this.estadio = estadio;
        this.entrenadorId = entrenadorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFundacion() {
        return fundacion;
    }

    public void setFundacion(String fundacion) {
        this.fundacion = fundacion;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public int getEntrenadorId() {
        return entrenadorId;
    }

    public void setEntrenadorId(int entrenadorId) {
        this.entrenadorId = entrenadorId;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", fundacion='" + fundacion + '\'' +
                ", estadio='" + estadio + '\'' +
                ", entrenadorId=" + entrenadorId +
                '}';
    }
}
