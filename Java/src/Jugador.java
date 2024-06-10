public class Jugador extends Persona {
    private String posicion;
    private String equipoNombre;
    private String entrenadorNombre; // Añadido para almacenar el nombre del entrenador

    public Jugador(int id, String nombre, String nacionalidad, String fechaNacimiento, String posicion, String equipoNombre, String entrenadorNombre) {
        super(id, nombre, nacionalidad, fechaNacimiento);
        this.posicion = posicion;
        this.equipoNombre = equipoNombre;
        this.entrenadorNombre = entrenadorNombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getEquipoNombre() {
        return equipoNombre;
    }

    public void setEquipoNombre(String equipoNombre) {
        this.equipoNombre = equipoNombre;
    }

    public String getEntrenadorNombre() {
        return entrenadorNombre;
    }

    public void setEntrenadorNombre(String entrenadorNombre) {
        this.entrenadorNombre = entrenadorNombre;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", nacionalidad='" + getNacionalidad() + '\'' +
                ", fechaNacimiento='" + getFechaNacimiento() + '\'' +
                ", posicion='" + posicion + '\'' +
                ", equipoNombre='" + equipoNombre + '\'' +
                ", entrenadorNombre='" + entrenadorNombre + '\'' +
                '}';
    }
}
