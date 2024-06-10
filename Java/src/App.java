import java.util.*;

public class App {

    private static final EntrenadorDao entrenadorDao = new EntrenadorDao();
    private static final JugadorDao jugadorDao = new JugadorDao();
    private static final EquipoDao equipoDao = new EquipoDao();
    private static final ClasificacionDao clasificacionDao = new ClasificacionDao();
    private static final TorneoDao torneoDao = new TorneoDao();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("");
            System.out.println("Bienvenido al programa de gestión de fútbol");
            System.out.println("Por favor, seleccione una opción:");
            System.out.println("1. Buscar");
            System.out.println("2. Añadir");
            System.out.println("3. Borrar");
            System.out.println("4. Modificar");
            System.out.println("5. Ver clasificación ligas");
            System.out.println("6. Ver plantilla Ordenada");
            System.out.println("7. Crear torneo");
            System.out.println("8. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    buscar(scanner);
                    break;
                case 2:
                    agregar(scanner);
                    break;
                case 3:
                    borrar(scanner);
                    break;
                case 4:
                    modificar(scanner);
                    break;
                case 5:
                    verClasificacion(scanner);
                    break;
                case 6:
                    verPlantillaOrdenada(scanner);
                    break;
                case 7:
                    crearTorneo(scanner);
                    break;
                case 8:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
        scanner.close();
    }

    // Métodos de búsqueda
    public static void buscar(Scanner scanner) {
        System.out.println("Seleccione la entidad a buscar:");
        System.out.println("1. Entrenador");
        System.out.println("2. Jugador");
        System.out.println("3. Equipo");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el nombre:");
        String nombre = scanner.nextLine();

        switch (opcion) {
            case 1:
                Entrenador entrenador = entrenadorDao.obtenerEntrenadorPorNombre(nombre);
                if (entrenador != null) {
                    System.out.println(entrenador);
                } else {
                    System.out.println("Entrenador no encontrado.");
                }
                break;
            case 2:
                Jugador jugador = jugadorDao.obtenerJugadorPorNombre(nombre);
                if (jugador != null) {
                    System.out.println(jugador);
                } else {
                    System.out.println("Jugador no encontrado.");
                }
                break;
            case 3:
                Equipo equipo = equipoDao.obtenerEquipoPorNombre(nombre);
                if (equipo != null) {
                    System.out.println(equipo);
                    List<Jugador> jugadores = jugadorDao.obtenerJugadoresPorEquipo(equipo.getId());
                    System.out.println("\nPlantilla del equipo:");
                    for (Jugador j : jugadores) {
                        System.out.println(j);
                    }
                } else {
                    System.out.println("Equipo no encontrado.");
                }
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    // Métodos para añadir
    public static void agregar(Scanner scanner) {
        System.out.println("Seleccione la entidad a añadir:");
        System.out.println("1. Entrenador");
        System.out.println("2. Jugador");
        System.out.println("3. Equipo");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (opcion) {
            case 1:
                agregarEntrenador(scanner);
                break;
            case 2:
                agregarJugador(scanner);
                break;
            case 3:
                agregarEquipo(scanner);
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private static void agregarEntrenador(Scanner scanner) {
        System.out.println("Ingrese nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese nacionalidad:");
        String nacionalidad = scanner.nextLine();
        System.out.println("Ingrese fecha de nacimiento (YYYY-MM-DD):");
        String fechaNacimiento = scanner.nextLine();
        System.out.println("Ingrese email:");
        String email = scanner.nextLine();
        System.out.println("Ingrese telefono:");
        String telefono = scanner.nextLine();

        Entrenador entrenador = new Entrenador(0, nombre, nacionalidad, fechaNacimiento, email, telefono);
        entrenadorDao.agregarEntrenador(entrenador);
    }

    private static void agregarJugador(Scanner scanner) {
        System.out.println("Ingrese nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese nacionalidad:");
        String nacionalidad = scanner.nextLine();
        System.out.println("Ingrese fecha de nacimiento (YYYY-MM-DD):");
        String fechaNacimiento = scanner.nextLine();
        System.out.println("Ingrese posición:");
        String posicion = scanner.nextLine();
        System.out.println("Ingrese nombre del equipo:");
        String nombreEquipo = scanner.nextLine();
        System.out.println("Ingrese nombre del entrenador:");
        String nombreEntrenador = scanner.nextLine();

        Jugador jugador = new Jugador(0, nombre, nacionalidad, fechaNacimiento, posicion, nombreEquipo, nombreEntrenador);
        jugadorDao.agregarJugador(jugador);
    }

    private static void agregarEquipo(Scanner scanner) {
        System.out.println("Ingrese nombre del equipo:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese país:");
        String pais = scanner.nextLine();
        System.out.println("Ingrese fundación (YYYY-MM-DD):");
        String fundacion = scanner.nextLine();
        System.out.println("Ingrese estadio:");
        String estadio = scanner.nextLine();
        System.out.println("Ingrese nombre del entrenador:");
        String nombreEntrenador = scanner.nextLine();

        int entrenadorId = entrenadorDao.obtenerEntrenadorPorNombre(nombreEntrenador).getId();
        Equipo equipo = new Equipo(0, nombre, pais, fundacion, estadio, entrenadorId);
        equipoDao.agregarEquipo(equipo);
    }

    // Métodos para borrar
    public static void borrar(Scanner scanner) {
        System.out.println("Seleccione la entidad a borrar:");
        System.out.println("1. Entrenador");
        System.out.println("2. Jugador");
        System.out.println("3. Equipo");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                borrarEntrenador(scanner);
                break;
            case 2:
                borrarJugador(scanner);
                break;
            case 3:
                borrarEquipo(scanner);
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private static void borrarEntrenador(Scanner scanner) {
        System.out.println("Ingrese el nombre del entrenador:");
        String nombre = scanner.nextLine();

        Entrenador entrenador = entrenadorDao.obtenerEntrenadorPorNombre(nombre);
        if (entrenador != null) {
            entrenadorDao.borrarEntrenador(entrenador.getId());
        } else {
            System.out.println("Entrenador no encontrado.");
        }
    }

    private static void borrarJugador(Scanner scanner) {
        System.out.println("Ingrese el nombre del jugador:");
        String nombre = scanner.nextLine();

        Jugador jugador = jugadorDao.obtenerJugadorPorNombre(nombre);
        if (jugador != null) {
            jugadorDao.borrarJugador(jugador.getId());
        } else {
            System.out.println("Jugador no encontrado.");
        }
    }

    private static void borrarEquipo(Scanner scanner) {
        System.out.println("Ingrese el nombre del equipo:");
        String nombre = scanner.nextLine();

        Equipo equipo = equipoDao.obtenerEquipoPorNombre(nombre);
        if (equipo != null) {
            equipoDao.borrarEquipo(equipo.getId());
        } else {
            System.out.println("Equipo no encontrado.");
        }
    }

    // Métodos para modificar
    public static void modificar(Scanner scanner) {
        System.out.println("Seleccione la entidad a modificar:");
        System.out.println("1. Entrenador");
        System.out.println("2. Jugador");
        System.out.println("3. Equipo");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (opcion) {
            case 1:
                modificarEntrenador(scanner);
                break;
            case 2:
                modificarJugador(scanner);
                break;
            case 3:
                modificarEquipo(scanner);
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private static void modificarEntrenador(Scanner scanner) {
        System.out.println("Ingrese el nombre del entrenador a modificar:");
        String nombreOriginal = scanner.nextLine();

        Entrenador entrenador = entrenadorDao.obtenerEntrenadorPorNombre(nombreOriginal);
        if (entrenador == null) {
            System.out.println("Entrenador no encontrado.");
            return;
        }

        System.out.println("Ingrese nuevo nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese nueva nacionalidad:");
        String nacionalidad = scanner.nextLine();
        System.out.println("Ingrese nueva fecha de nacimiento (YYYY-MM-DD):");
        String fechaNacimiento = scanner.nextLine();
        System.out.println("Ingrese nuevo email:");
        String email = scanner.nextLine();
        System.out.println("Ingrese nuevo telefono:");
        String telefono = scanner.nextLine();

        entrenador.setNombre(nombre);
        entrenador.setNacionalidad(nacionalidad);
        entrenador.setFechaNacimiento(fechaNacimiento);
        entrenador.setEmail(email);
        entrenador.setTelefono(telefono);
        entrenadorDao.actualizarEntrenador(entrenador);
    }

    private static void modificarJugador(Scanner scanner) {
        System.out.println("Ingrese el nombre del jugador a modificar:");
        String nombreOriginal = scanner.nextLine();

        Jugador jugador = jugadorDao.obtenerJugadorPorNombre(nombreOriginal);
        if (jugador == null) {
            System.out.println("Jugador no encontrado.");
            return;
        }

        System.out.println("Ingrese nuevo nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese nueva nacionalidad:");
        String nacionalidad = scanner.nextLine();
        System.out.println("Ingrese nueva fecha de nacimiento (YYYY-MM-DD):");
        String fechaNacimiento = scanner.nextLine();
        System.out.println("Ingrese nueva posición:");
        String posicion = scanner.nextLine();
        System.out.println("Ingrese nuevo nombre del equipo:");
        String nombreEquipo = scanner.nextLine();

        jugador.setNombre(nombre);
        jugador.setNacionalidad(nacionalidad);
        jugador.setFechaNacimiento(fechaNacimiento);
        jugador.setPosicion(posicion);
        jugador.setEquipoNombre(nombreEquipo);
        jugadorDao.actualizarJugador(jugador);
    }

    private static void modificarEquipo(Scanner scanner) {
        System.out.println("Ingrese el nombre del equipo a modificar:");
        String nombreOriginal = scanner.nextLine();

        Equipo equipo = equipoDao.obtenerEquipoPorNombre(nombreOriginal);
        if (equipo == null) {
            System.out.println("Equipo no encontrado.");
            return;
        }

        System.out.println("Ingrese nuevo nombre del equipo:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese nuevo país:");
        String pais = scanner.nextLine();
        System.out.println("Ingrese nueva fundación (YYYY-MM-DD):");
        String fundacion = scanner.nextLine();
        System.out.println("Ingrese nuevo estadio:");
        String estadio = scanner.nextLine();
        System.out.println("Ingrese nuevo nombre del entrenador:");
        String nombreEntrenador = scanner.nextLine();

        equipo.setNombre(nombre);
        equipo.setPais(pais);
        equipo.setFundacion(fundacion);
        equipo.setEstadio(estadio);
        equipo.setEntrenadorId(entrenadorDao.obtenerEntrenadorPorNombre(nombreEntrenador).getId());
        equipoDao.actualizarEquipo(equipo);
    }

    public static void verClasificacion(Scanner scanner) {
        System.out.println("Ingrese el nombre del torneo que desea ver su clasificación:");
        String nombreTorneo = scanner.nextLine();
        clasificacionDao.verClasificacion(nombreTorneo);
    }

    public static void verPlantillaOrdenada(Scanner scanner) {
        System.out.println("Ingrese el nombre del equipo:");
        String nombreEquipo = scanner.nextLine();

        System.out.println("Seleccione el criterio de ordenamiento:");
        System.out.println("1. Por fecha de nacimiento");
        System.out.println("2. Por nombre");
        System.out.println("3. Por posición");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Comparator<Jugador> comparator = null;
        switch (opcion) {
            case 1:
                comparator = Comparator.comparing(Jugador::getFechaNacimiento);
                break;
            case 2:
                comparator = Comparator.comparing(Jugador::getNombre);
                break;
            case 3:
                comparator = Comparator.comparing(Jugador::getPosicion);
                break;
            default:
                System.out.println("Opción no válida");
                return;
        }

        int equipoId = equipoDao.obtenerEquipoPorNombre(nombreEquipo).getId();
        List<Jugador> jugadores = jugadorDao.obtenerPlantillaOrdenada(equipoId, comparator);
        jugadores.forEach(System.out::println);

        System.out.println("Presione Enter para volver al menú principal...");
        scanner.nextLine();
    }

    public static void crearTorneo(Scanner scanner) {
        System.out.println("Ingrese el nombre del torneo:");
        String nombreTorneo = scanner.nextLine();

        System.out.println("Ingrese la descripción del torneo:");
        String descripcionTorneo = scanner.nextLine();

        System.out.println("Ingrese la fecha de inicio del torneo (YYYY-MM-DD):");
        String fechaInicio = scanner.nextLine();

        System.out.println("Ingrese la fecha de fin del torneo (YYYY-MM-DD):");
        String fechaFin = scanner.nextLine();

        Torneo torneo = new Torneo(nombreTorneo, descripcionTorneo, fechaInicio, fechaFin);
        torneoDao.crearTorneo(torneo);

        System.out.println("Ingrese los nombres de los equipos a agregar al torneo (separados por comas):");
        String nombresEquipos = scanner.nextLine();
        List<Integer> equipoIds = new ArrayList<>();
        for (String nombreEquipo : nombresEquipos.split(",")) {
            int equipoId = equipoDao.obtenerEquipoPorNombre(nombreEquipo.trim()).getId();
            equipoIds.add(equipoId);
        }
        torneoDao.agregarEquiposATorneo(torneo.getId(), equipoIds);

        generarClasificacionAleatoria(torneo.getId(), equipoIds);
    }

    private static void generarClasificacionAleatoria(int torneoId, List<Integer> equipoIds) {
        Random rand = new Random();
        List<Clasificacion> clasificaciones = new ArrayList<>();

        for (int i = 0; i < equipoIds.size(); i++) {
            int equipoId = equipoIds.get(i);
            int puntos = rand.nextInt(100); // Puntos aleatorios
            int partidosJugados = 38; // Suponemos que se jugaron 38 partidos
            int partidosGanados = rand.nextInt(partidosJugados);
            int partidosEmpatados = rand.nextInt(partidosJugados - partidosGanados);
            int partidosPerdidos = partidosJugados - partidosGanados - partidosEmpatados;

            clasificaciones.add(new Clasificacion(equipoId, torneoId, i + 1, puntos, partidosJugados, partidosGanados,
                    partidosEmpatados, partidosPerdidos));
        }

        clasificaciones.sort(Comparator.comparingInt(Clasificacion::getPuntos).reversed());

        clasificacionDao.agregarClasificaciones(clasificaciones);
    }
}
