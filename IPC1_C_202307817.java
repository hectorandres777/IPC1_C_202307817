import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IPC1_C_202307817 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        String[][] personajes = new String[100][8];
        int totalPersonajes = 0;

        do {
            System.out.println("1. Agregar Personaje");
            System.out.println("2. modificar personaje");
            System.out.println("3. Eliminar personaje");
            System.out.println("4. Ver datos de personaje");
            System.out.println("5. Ver Listado de personajes");
            System.out.println("6. Realizar pelea entre personajes");
            System.out.println("7. Ver historial de peleas");
            System.out.println("8. Ver datos de estudiantes");
            System.out.println("9. salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch(opcion) {
                case 1:
                    System.out.println("\nHas seleccionado: Agregar Personaje");

                    if (totalPersonajes >= personajes.length) {
                        System.out.println("No se pueden agregar más personajes.");
                        break;
                    }

                    // Solicitar nombre
                    System.out.print("Nombre del personaje: ");
                    String nombre = scanner.nextLine().trim();

                    // Verificar
                    boolean existeNombre = false;
                    for (int i = 0; i < totalPersonajes; i++) {
                        if (personajes[i][0].equalsIgnoreCase(nombre)) {
                            existeNombre = true;
                            break;
                        }
                    }
                    if (existeNombre) {
                        System.out.println("El personaje ya existe.");
                        break;
                    }
                    // Solicitar arma
                    System.out.print("Arma del personaje: ");
                    var arma = scanner.nextLine().trim();

                    // Solicitar habilidades
                    String[] habilidades = new String[5];
                    for (int i = 0; i < 5; i++) {
                        System.out.print("Habilidad " + (i + 1) + " (deja vacío para terminar): ");
                        String habilidad = scanner.nextLine().trim();
                        if (habilidad.isEmpty()) break;
                        habilidades[i] = habilidad;
                    }
                    // Solicitar nivel de poder
                    System.out.print("Nivel de poder (1-100): ");
                    String nivelStr = scanner.nextLine().trim();
                    int nivel;
                    try {
                        nivel = Integer.parseInt(nivelStr);
                        if (nivel < 1 || nivel > 100) {
                            System.out.println("El nivel debe estar entre 1 y 100.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Nivel inválido.");
                        break;
                    }

                    // Guardar personaje
                    personajes[totalPersonajes][0] = nombre;
                    personajes[totalPersonajes][1] = arma;
                    for (int i = 0; i < 5; i++) {
                        personajes[totalPersonajes][2 + i] = habilidades[i] != null ? habilidades[i] : "";
                    }
                    personajes[totalPersonajes][7] = String.valueOf(nivel);
                    totalPersonajes++;
                    System.out.println("Personaje agregado exitosamente.");
                    break;
                case 2:
                    System.out.println("\nHas seleccionado: Modificar Personaje");

                    if (totalPersonajes == 0) {
                        System.out.println("No hay personajes registrados.");
                        break;
                    }
                    System.out.print("Ingresa el nombre del personaje a modificar: ");
                    String nombreBuscar = scanner.nextLine().trim();

                    int indexModificar = -1;
                    for (int i = 0; i < totalPersonajes; i++) {
                        if (personajes[i][0].equalsIgnoreCase(nombreBuscar)) {
                            indexModificar = i;
                            break;
                        }
                    }
                    if (indexModificar == -1) {
                        System.out.println("Personaje no encontrado.");
                        break;
                    }
                    // Mostrar datos actuales
                    System.out.println("\nDatos actuales del personaje:");
                    System.out.println("Nombre: " + personajes[indexModificar][0]);
                    System.out.println("Arma: " + personajes[indexModificar][1]);
                    System.out.println("Habilidades:");
                    for (int i = 2; i <= 6; i++) {
                        if (!personajes[indexModificar][i].isEmpty()) {
                            System.out.println("  - " + personajes[indexModificar][i]);
                        }
                    }
                    System.out.println("Nivel de poder: " + personajes[indexModificar][7]);

                    // Solicitar nuevos datos
                    System.out.print("\nNueva arma (deja vacío para mantener la actual): ");
                    String nuevaArma = scanner.nextLine().trim();
                    if (!nuevaArma.isEmpty()) {
                        personajes[indexModificar][1] = nuevaArma;
                    }
                    System.out.println("Ingresa hasta 5 nuevas habilidades (deja vacío para mantener cada una):");
                    for (int i = 0; i < 5; i++) {
                        System.out.print("Habilidad " + (i + 1) + ": ");
                        String nuevaHab = scanner.nextLine().trim();
                        if (!nuevaHab.isEmpty()) {
                            personajes[indexModificar][2 + i] = nuevaHab;
                        }
                    }
                    System.out.print("Nuevo nivel de poder (1-100, deja vacío para mantener): ");
                    String nuevoNivelStr = scanner.nextLine().trim();
                    if (!nuevoNivelStr.isEmpty()) {
                        try {
                            var nuevoNivel = Integer.parseInt(nuevoNivelStr);
                            if (nuevoNivel >= 1 && nuevoNivel <= 100) {
                                personajes[indexModificar][7] = String.valueOf(nuevoNivel);
                                System.out.println("Nivel de poder actualizado.");
                            } else {
                                System.out.println("Nivel fuera de rango. No se modificó.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida. No se modificó el nivel.");
                        }
                    }
                    System.out.println("Modificación completada.");
                    break;
                case 3:
                    System.out.println("\n Eliminar personaje");

                    if (totalPersonajes == 0) {
                        System.out.println("No hay personajes registrados.");
                        break;
                    }
                    System.out.print("Ingresa el nombre del personaje a eliminar: ");
                    String nombreEliminar = scanner.nextLine().trim();

                    int indexEliminar = -1;
                    for (int i = 0; i < totalPersonajes; i++) {
                        if (personajes[i][0].equalsIgnoreCase(nombreEliminar)) {
                            indexEliminar = i;
                            break;
                        }
                    }
                    if (indexEliminar == -1) {
                        System.out.println("Personaje no encontrado.");
                        break;
                    }
                    // Confirmación
                    System.out.print("¿Estás seguro de que deseas eliminar a " + personajes[indexEliminar][0] + "? (s/n): ");
                    String confirmacion = scanner.nextLine().trim().toLowerCase();
                    if (!confirmacion.equals("s")) {
                        System.out.println("Eliminación cancelada.");
                        break;
                    }
                    // Eliminar personaje desplazando los siguientes
                    for (int i = indexEliminar; i < totalPersonajes - 1; i++) {
                        for (int j = 0; j < 8; j++) {
                            personajes[i][j] = personajes[i + 1][j];
                        }
                    }
                    // Limpiar la última posición
                    for (int j = 0; j < 8; j++) {
                        personajes[totalPersonajes - 1][j] = "";
                    }
                    totalPersonajes--;
                    System.out.println("Personaje eliminado exitosamente.");
                    break;
                case 4:
                    System.out.println("\nHas seleccionado: Ver datos de personaje");

                    if (totalPersonajes == 0) {
                        System.out.println("No hay personajes registrados.");
                        break;
                    }
                    // Mostrar lista de personajes con sus IDs
                    System.out.println("Lista de personajes:");
                    for (int i = 0; i < totalPersonajes; i++) {
                        System.out.println("ID " + i + ": " + personajes[i][0]);
                    }
                    System.out.print("Ingresa el ID del personaje que deseas consultar: ");
                    String idStr = scanner.nextLine().trim();
                    int id;
                    try {
                        id = Integer.parseInt(idStr);
                        if (id < 0 || id >= totalPersonajes) {
                            System.out.println("ID fuera de rango.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida.");
                        break;
                    }
                    // Mostrar datos del personaje
                    System.out.println("\nDatos del personaje:");
                    System.out.println("ID: " + id);
                    System.out.println("Nombre: " + personajes[id][0]);
                    System.out.println("Arma: " + personajes[id][1]);
                    System.out.println("Habilidades:");
                    boolean tieneHabilidades = false;
                    for (int i = 2; i <= 6; i++) {
                        if (!personajes[id][i].isEmpty()) {
                            System.out.println("  - " + personajes[id][i]);
                            tieneHabilidades = true;
                        }
                    }
                    if (!tieneHabilidades) {
                        System.out.println("  (Sin habilidades registradas)");
                    }
                    System.out.println("Nivel de poder: " + personajes[id][7]);
                    break;
                case 5:
                    System.out.println("\nHas seleccionado: Ver listado de personajes");

                    if (totalPersonajes == 0) {
                        System.out.println("No existen personajes.");
                        break;
                    }
                    System.out.println("Personajes registrados:");
                    for (var i = 0; i < totalPersonajes; i++) {
                        var nombrePersonaje = personajes[i][0];
                        var nivelPoder = personajes[i][7];
                        System.out.println("Nombre: " + nombrePersonaje + " | Nivel de poder: " + nivelPoder);
                    }
                    break;
                case 6:
                    System.out.println("\nHas seleccionado: Realizar pelea entre personajes");
                    if (totalPersonajes < 2) {
                        System.out.println("Se necesitan al menos dos personajes registrados para realizar una pelea.");
                        break;
                    }
                    // pedir nombre de 1
                    System.out.print("Nombre del primer personaje: ");
                    String nombre1 = scanner.nextLine().trim();

                    // Budscar personaje 1
                    var per1 = -1;
                    for (int i = 0; i < totalPersonajes; i++) {
                        if (personajes[i][0].equalsIgnoreCase(nombre1)) {
                            per1 = i;
                            break;
                        }
                    }
                    if (per1 == -1) {
                        System.out.println("El personaje '" + nombre1 + "' no fue encontrado.");
                        break;
                    }
                    // pedri nombre del 2
                    System.out.print("Nombre del segundo personaje: ");
                    String nombre2 = scanner.nextLine().trim();
                    // Buscar per. 2
                    int per2 = -1;
                    for (int i = 0; i < totalPersonajes; i++) {
                        if (personajes[i][0].equalsIgnoreCase(nombre2)) {
                            per2 = i;
                            break;
                        }
                    }
                    if (per2 == -1) {
                        System.out.println("El personaje '" + nombre2 + "' no fue encontrado.");
                        break;
                    }
                    if (per1 == per2) {
                        System.out.println("No puedes hacer que un personaje pelee consigo mismo.");
                        break;
                    }
                    // fecha y hora
                    LocalDateTime fechaHora = LocalDateTime.now();
                    DateTimeFormatter dato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    String fechaHoraPelea = fechaHora.format(dato);

                    System.out.println("Pelea registrada entre '" + nombre1 + "' y '" + nombre2 + "' el " + fechaHoraPelea);
                    break;


                default:
                    System.out.println("\nOpción no válida.");
            }

            System.out.println();
        } while(opcion != 9);

        scanner.close();
    }
}


