public class Entrenador extends Persona {
    private String email;
    private String telefono;

    public Entrenador(int id, String nombre, String nacionalidad, String fechaNacimiento, String email, String telefono) {
        super(id, nombre, nacionalidad, fechaNacimiento);
        this.email = email;
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", nacionalidad='" + getNacionalidad() + '\'' +
                ", fechaNacimiento='" + getFechaNacimiento() + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
