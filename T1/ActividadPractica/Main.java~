package ActividadPractica;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final Path CARPETA_BASE = Path.of("T1", "ActividadPractica");
    private static final Path ARCHIVO_CONFIG = CARPETA_BASE.resolve("config.properties");
    private static Path CARPETA_DATOS = Path.of("T1", "ActividadPractica", "Almacenamiento");
    private static Path ARCHIVO_PARTICIPANTES = CARPETA_DATOS.resolve("participantes.dat");
    private static Path COPIA_SEGURIDAD = CARPETA_DATOS.resolve("participantes.bak");
    private static Path HISTORIAL = CARPETA_DATOS.resolve("historial.txt");
    private static Path ARCHIVO_BINARIO = CARPETA_DATOS.resolve("resumen.bin");
    private static final int TAMANO_REGISTRO = Integer.BYTES + Double.BYTES; // 4 + 8 = 12 bytes

    private static final Scanner SC = new Scanner(System.in);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static HashSet<Participante> participantes = new HashSet<>();
    private static Properties propiedades;

    public static void main(String[] args) {
        try {
            propiedades = cargarPropiedades();
            configurarRutasAlmacenamiento();
            Files.createDirectories(CARPETA_DATOS);
            participantes = recuperarParticipantes();
            ejecutarMenu();
        } catch (IOException e) {
            System.out.println("Error de archivos: " + e.getMessage());
        } finally {
            SC.close();
        }
    }

    private static void ejecutarMenu() throws IOException {
        int opcion;
        do {
            menu();
            opcion = leerInt("Tu opción: ");
            try {
                switch (opcion) {
                    case 1 -> anadirParticipante();
                    case 2 -> registrarKilometros();
                    case 3 -> anadirRuta();
                    case 4 -> consultarParticipante();
                    case 5 -> modificarObservacion();
                    case 6 -> cambiarMinimoDistincion();
                    case 7 -> mostrarDistinciones();
                    case 8 -> buscarParticipantesPorRuta();
                    case 9 -> mostrarDistanciaMedia();
                    case 10 -> mostrarTodasLasRutas();
                    case 11 -> recuperarCopiaSeguridad();
                    case 12 -> exportarFichaJson();
                    case 13 -> importarFichaJson();
                    case 14 -> consultarHistorialCompleto();
                    case 15 -> consultarHistorialParticipante();
                    case 16 -> generarFicheroBinario();
                    case 17 -> mostrarFicheroBinario();
                    case 18 -> consultarRegistroPorPosicion();
                    case 19 -> modificarKilometrosRegistro();
                    case 0 -> {
                        guardarParticipantes();
                        System.out.println("Datos guardados. Hasta pronto.");
                    }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (IOException e) {
                System.out.println("No se pudo completar la operación: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private static Properties cargarPropiedades() throws IOException {
        Files.createDirectories(CARPETA_BASE);
        Files.createDirectories(CARPETA_DATOS);

        Properties p = new Properties();
        p.setProperty("asociacion", "Asociación de senderismo");
        p.setProperty("almacenamiento", CARPETA_DATOS.toString());
        p.setProperty("km_min", "100");

        if (Files.exists(ARCHIVO_CONFIG)) {
            try (BufferedReader reader = Files.newBufferedReader(ARCHIVO_CONFIG)) {
                p.load(reader);
            }
            if (p.getProperty("asociacion") == null || p.getProperty("asociacion").isBlank()) {
                p.setProperty("asociacion", "Asociación de senderismo");
            }
            if (p.getProperty("almacenamiento") == null || p.getProperty("almacenamiento").isBlank()) {
                p.setProperty("almacenamiento", CARPETA_DATOS.toString());
            }
            try {
                double minimo = Double.parseDouble(p.getProperty("km_min", "100"));
                if (!Double.isFinite(minimo) || minimo < 0) {
                    p.setProperty("km_min", "100");
                }
            } catch (NumberFormatException e) {
                p.setProperty("km_min", "100");
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(ARCHIVO_CONFIG)) {
            p.store(writer, "Configuración del Gestor de expediciones");
        }
        return p;
    }

    private static void configurarRutasAlmacenamiento() {
        String carpetaConfigurada = propiedades.getProperty(
                "almacenamiento", CARPETA_DATOS.toString());
        CARPETA_DATOS = Path.of(carpetaConfigurada);
        ARCHIVO_PARTICIPANTES = CARPETA_DATOS.resolve("participantes.dat");
        COPIA_SEGURIDAD = CARPETA_DATOS.resolve("participantes.bak");
        HISTORIAL = CARPETA_DATOS.resolve("historial.txt");
        ARCHIVO_BINARIO = CARPETA_DATOS.resolve("resumen.bin");
    }

    private static void guardarPropiedades() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(ARCHIVO_CONFIG)) {
            propiedades.store(writer, "Configuración del Gestor de expediciones");
        }
    }

    @SuppressWarnings("unchecked")
    private static HashSet<Participante> recuperarParticipantes() throws IOException {
        if (!Files.exists(ARCHIVO_PARTICIPANTES)) {
            return new HashSet<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(Files.newInputStream(ARCHIVO_PARTICIPANTES)))) {
            Object objeto = in.readObject();
            if (objeto instanceof HashSet<?>) {
                return (HashSet<Participante>) objeto;
            }
            System.out.println("El fichero de participantes tiene un formato inesperado. Se iniciará una lista vacía.");
        } catch (ClassNotFoundException | EOFException e) {
            System.out.println("No se pudieron recuperar los participantes: " + e.getMessage());
        }
        return new HashSet<>();
    }

    private static void guardarParticipantes() throws IOException {
        Files.createDirectories(ARCHIVO_PARTICIPANTES.getParent());
        if (Files.exists(ARCHIVO_PARTICIPANTES)) {
            Files.copy(ARCHIVO_PARTICIPANTES, COPIA_SEGURIDAD, StandardCopyOption.REPLACE_EXISTING);
        }
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(Files.newOutputStream(ARCHIVO_PARTICIPANTES)))) {
            out.writeObject(participantes);
        }
    }

    private static Participante buscarParticipante(int id) {
        return participantes.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    private static void anadirParticipante() throws IOException {
        int id = leerInt("ID del participante (no negativo): ");
        while (id < 0 || buscarParticipante(id) != null) {
            System.out.println(id < 0 ? "El ID no puede ser negativo." : "Ese ID ya existe.");
            id = leerInt("Introduce otro ID: ");
        }
        String nombre = leerTexto("Nombre: ");
        while (nombre.isBlank()) {
            System.out.println("El nombre no puede estar vacío.");
            nombre = leerTexto("Nombre: ");
        }
        participantes.add(new Participante(id, nombre));
        registrarOperacion("Participante añadido", id);
        System.out.println("Participante añadido.");
    }

    private static void registrarKilometros() throws IOException {
        Participante p = pedirParticipante();
        if (p == null) return;
        double km = leerDouble("Kilómetros que se añadirán: ");
        while (!Double.isFinite(km) || km < 0) {
            System.out.println("Introduce una distancia válida, no negativa y finita.");
            km = leerDouble("Kilómetros: ");
        }
        p.sumarKm(km);
        registrarOperacion("Kilómetros añadidos: " + km, p.getId());
        System.out.println("Kilómetros registrados.");
    }

    private static void anadirRuta() throws IOException {
        Participante p = pedirParticipante();
        if (p == null) return;
        String ruta = leerTexto("Nombre de la ruta: ");
        while (ruta.isBlank()) {
            System.out.println("La ruta no puede estar vacía.");
            ruta = leerTexto("Nombre de la ruta: ");
        }
        if (p.agregarRuta(ruta)) {
            registrarOperacion("Ruta añadida: " + ruta, p.getId());
            System.out.println("Ruta añadida.");
        } else {
            System.out.println("Esa ruta ya está registrada para este participante.");
        }
    }

    private static void consultarParticipante() {
        Participante p = pedirParticipante();
        if (p != null) System.out.println(p);
    }

    private static void modificarObservacion() {
        Participante p = pedirParticipante();
        if (p == null) return;
        p.setObservacion(leerTexto("Nueva observación provisional (vacía para borrar): "));
        System.out.println("Observación actualizada. No se guardará en disco ni en el historial.");
    }

    private static void cambiarMinimoDistincion() throws IOException {
        double minimo = leerDouble("Nuevo mínimo de kilómetros para la distinción: ");
        while (!Double.isFinite(minimo) || minimo < 0) {
            System.out.println("El mínimo debe ser un número válido y no negativo. Se conserva el anterior hasta introducir uno válido.");
            minimo = leerDouble("Nuevo mínimo: ");
        }
        propiedades.setProperty("km_min", Double.toString(minimo));
        guardarPropiedades();
        System.out.println("Mínimo actualizado a " + minimo + " km.");
    }

    private static void mostrarDistinciones() {
        double minimo = Double.parseDouble(propiedades.getProperty("km_min", "100"));
        List<Participante> resultado = participantes.stream()
                .filter(p -> p.getKmAcumulados() >= minimo)
                .sorted(Comparator.comparingDouble(Participante::getKmAcumulados).reversed())
                .collect(Collectors.toList());
        if (resultado.isEmpty()) {
            System.out.println("No hay participantes que alcancen " + minimo + " km.");
        } else {
            resultado.forEach(System.out::println);
        }
    }

    private static void buscarParticipantesPorRuta() {
        String ruta = leerTexto("Ruta que quieres buscar: ");
        List<Participante> resultado = participantes.stream()
                .filter(p -> p.getRutas().stream().anyMatch(r -> r.equalsIgnoreCase(ruta)))
                .collect(Collectors.toList());
        if (resultado.isEmpty()) System.out.println("Ningún participante ha completado esa ruta.");
        else resultado.forEach(System.out::println);
    }

    private static void mostrarDistanciaMedia() {
        participantes.stream().mapToDouble(Participante::getKmAcumulados).average()
                .ifPresentOrElse(
                        media -> System.out.printf(Locale.ROOT, "Distancia media: %.2f km%n", media),
                        () -> System.out.println("No hay participantes para calcular la media."));
    }

    private static void mostrarTodasLasRutas() {
        List<String> todas = participantes.stream()
                .flatMap(p -> p.getRutas().stream())
                .distinct()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
        if (todas.isEmpty()) System.out.println("No hay rutas registradas.");
        else todas.forEach(System.out::println);
    }

    @SuppressWarnings("unchecked")
    private static void recuperarCopiaSeguridad() throws IOException {
        if (!Files.exists(COPIA_SEGURIDAD)) {
            System.out.println("No hay copia de seguridad disponible.");
            return;
        }
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(Files.newInputStream(COPIA_SEGURIDAD)))) {
            Object objeto = in.readObject();
            if (!(objeto instanceof HashSet<?>)) {
                System.out.println("La copia de seguridad no tiene el formato esperado.");
                return;
            }
            participantes = (HashSet<Participante>) objeto;
            try (ObjectOutputStream out = new ObjectOutputStream(
                    new BufferedOutputStream(Files.newOutputStream(ARCHIVO_PARTICIPANTES)))) {
                out.writeObject(participantes);
            }
            System.out.println("Copia de seguridad restaurada.");
            registrarOperacionGeneral("Copia de seguridad restaurada");
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo leer la copia: " + e.getMessage());
        }
    }

    private static void exportarFichaJson() throws IOException {
        Participante p = pedirParticipante();
        if (p == null) return;
        Path carpetaJson = CARPETA_DATOS.resolve("fichas");
        Files.createDirectories(carpetaJson);
        Path archivo = carpetaJson.resolve("participante" + p.getId() + ".json");
        Files.writeString(archivo, GSON.toJson(p));
        registrarOperacion("Ficha exportada a JSON", p.getId());
        System.out.println("Ficha exportada a: " + archivo);
    }

    private static void importarFichaJson() throws IOException {
        Path carpetaJson = CARPETA_DATOS.resolve("fichas");
        Files.createDirectories(carpetaJson);
        List<Path> archivos;
        try (Stream<Path> stream = Files.walk(carpetaJson)) {
            archivos = stream.filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().toLowerCase(Locale.ROOT).endsWith(".json"))
                    .sorted()
                    .collect(Collectors.toList());
        }
        if (archivos.isEmpty()) {
            System.out.println("No hay fichas JSON disponibles en la carpeta de fichas ni en sus subcarpetas.");
            return;
        }
        System.out.println("Fichas JSON disponibles:");
        for (int i = 0; i < archivos.size(); i++) {
            System.out.println((i + 1) + ". " + carpetaJson.relativize(archivos.get(i)));
        }
        int seleccion = leerInt("Selecciona el número de la ficha: ");
        while (seleccion < 1 || seleccion > archivos.size()) {
            seleccion = leerInt("Selección no válida. Elige un número de la lista: ");
        }

        Participante importado;
        try {
            importado = GSON.fromJson(Files.readString(archivos.get(seleccion - 1)), Participante.class);
        } catch (JsonSyntaxException e) {
            System.out.println("El JSON no tiene un formato válido.");
            return;
        }
        if (!participanteValido(importado)) {
            System.out.println("La ficha contiene datos inválidos. No se ha importado.");
            return;
        }

        Participante existente = buscarParticipante(importado.getId());
        if (existente != null) {
            int opcion = leerInt("El ID ya existe. 1) Sustituir  2) Cancelar: ");
            while (opcion != 1 && opcion != 2) opcion = leerInt("Elige 1 o 2: ");
            if (opcion == 2) {
                System.out.println("Importación cancelada.");
                return;
            }
            participantes.remove(existente);
        }
        // Las observaciones no se importan: son provisionales.
        importado.setObservacion(null);
        participantes.add(importado);
        registrarOperacion("Ficha importada desde JSON", importado.getId());
        System.out.println("Ficha importada correctamente.");
    }

    private static boolean participanteValido(Participante p) {
        if (p == null || p.getId() < 0 || p.getNombre() == null || p.getNombre().isBlank()
                || !Double.isFinite(p.getKmAcumulados()) || p.getKmAcumulados() < 0
                || p.getRutas() == null) return false;
        Set<String> unicas = new HashSet<>();
        for (String ruta : p.getRutas()) {
            if (ruta == null || ruta.isBlank() || !unicas.add(ruta.toLowerCase(Locale.ROOT))) return false;
        }
        return true;
    }

    private static void consultarHistorialCompleto() throws IOException {
        if (!Files.exists(HISTORIAL) || Files.size(HISTORIAL) == 0) {
            System.out.println("El historial está vacío.");
            return;
        }
        System.out.println("===== HISTORIAL COMPLETO =====");
        try (Stream<String> lineas = Files.lines(HISTORIAL)) {
            lineas.forEach(System.out::println);
        }
    }

    private static void consultarHistorialParticipante() throws IOException {
        if (!Files.exists(HISTORIAL) || Files.size(HISTORIAL) == 0) {
            System.out.println("El historial está vacío.");
            return;
        }
        int id = leerInt("ID del participante: ");
        String marca = "ID: " + id;
        List<String> coincidencias;
        try (Stream<String> lineas = Files.lines(HISTORIAL)) {
            coincidencias = lineas.filter(linea -> linea.contains(marca))
                    .collect(Collectors.toList());
        }
        if (coincidencias.isEmpty()) System.out.println("No hay operaciones registradas para ese participante.");
        else coincidencias.forEach(System.out::println);
    }

    private static void registrarOperacion(String operacion, int id) throws IOException {
        Files.createDirectories(HISTORIAL.getParent());
        String linea = java.time.LocalDateTime.now() + " - " + operacion + " - ID: " + id
                + System.lineSeparator();
        Files.writeString(HISTORIAL, linea, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    private static void registrarOperacionGeneral(String operacion) throws IOException {
        Files.createDirectories(HISTORIAL.getParent());
        String linea = java.time.LocalDateTime.now() + " - " + operacion + System.lineSeparator();
        Files.writeString(HISTORIAL, linea, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    private static void generarFicheroBinario() throws IOException {
        Files.createDirectories(ARCHIVO_BINARIO.getParent());
        List<Participante> ordenados = participantes.stream()
                .sorted(Comparator.comparingInt(Participante::getId))
                .collect(Collectors.toList());
        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(Files.newOutputStream(ARCHIVO_BINARIO)))) {
            for (Participante p : ordenados) {
                out.writeInt(p.getId());
                out.writeDouble(p.getKmAcumulados());
            }
        }
        registrarOperacionGeneral("Fichero binario generado");
        System.out.println("Fichero binario generado. Registros: " + ordenados.size()
                + ". Tamaño por registro: " + TAMANO_REGISTRO + " bytes.");
    }

    private static void mostrarFicheroBinario() throws IOException {
        if (!Files.exists(ARCHIVO_BINARIO)) {
            System.out.println("Todavía no se ha generado el fichero binario.");
            return;
        }
        if (Files.size(ARCHIVO_BINARIO) == 0) {
            System.out.println("El fichero binario está vacío.");
            return;
        }
        try (DataInputStream in = new DataInputStream(
                new BufferedInputStream(Files.newInputStream(ARCHIVO_BINARIO)))) {
            System.out.println("ID | Kilómetros");
            while (true) {
                try {
                    int id = in.readInt();
                    double km = in.readDouble();
                    System.out.printf(Locale.ROOT, "%d | %.2f km%n", id, km);
                } catch (EOFException fin) {
                    break;
                }
            }
        }
    }

    private static void consultarRegistroPorPosicion() throws IOException {
        if (!comprobarBinario()) return;
        long cantidad = Files.size(ARCHIVO_BINARIO) / TAMANO_REGISTRO;
        int posicion = leerInt("Posición del registro (empieza en 1): ");
        if (posicion < 1 || posicion > cantidad) {
            System.out.println("Posición inexistente. El fichero contiene " + cantidad + " registros.");
            return;
        }
        try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO_BINARIO.toFile(), "r")) {
            raf.seek((long) (posicion - 1) * TAMANO_REGISTRO);
            int id = raf.readInt();
            double km = raf.readDouble();
            System.out.printf(Locale.ROOT, "Registro %d -> ID: %d, kilómetros: %.2f%n", posicion, id, km);
        }
    }

    private static void modificarKilometrosRegistro() throws IOException {
        if (!comprobarBinario()) return;
        long tamano = Files.size(ARCHIVO_BINARIO);
        if (tamano % TAMANO_REGISTRO != 0) {
            System.out.println("El fichero está incompleto o dañado; no se modificará.");
            return;
        }
        long cantidad = tamano / TAMANO_REGISTRO;
        int posicion = leerInt("Posición del registro a modificar (empieza en 1): ");
        if (posicion < 1 || posicion > cantidad) {
            System.out.println("Posición inexistente. El fichero contiene " + cantidad + " registros.");
            return;
        }
        double km = leerDouble("Nuevos kilómetros: ");
        while (!Double.isFinite(km) || km < 0) {
            System.out.println("Los kilómetros deben ser un número finito no negativo.");
            km = leerDouble("Nuevos kilómetros: ");
        }
        try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO_BINARIO.toFile(), "rw")) {
            raf.seek((long) (posicion - 1) * TAMANO_REGISTRO + Integer.BYTES);
            raf.writeDouble(km);
        }
        System.out.println("Registro modificado. Esta modificación solo afecta a la instantánea binaria.");
    }

    private static boolean comprobarBinario() throws IOException {
        if (!Files.exists(ARCHIVO_BINARIO)) {
            System.out.println("Primero debes generar el fichero binario (opción 16).");
            return false;
        }
        if (Files.size(ARCHIVO_BINARIO) % TAMANO_REGISTRO != 0) {
            System.out.println("El fichero binario está dañado: el tamaño no coincide con registros de "
                    + TAMANO_REGISTRO + " bytes.");
            return false;
        }
        return true;
    }

    private static Participante pedirParticipante() {
        int id = leerInt("ID del participante: ");
        Participante p = buscarParticipante(id);
        if (p == null) System.out.println("No existe un participante con ese ID.");
        return p;
    }

    private static int leerInt(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = SC.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = SC.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return SC.nextLine().trim();
    }

    private static void menu() {
        System.out.println("""
                ===== GESTOR DE EXPEDICIONES =====
                1. Añadir participante
                2. Registrar kilómetros
                3. Añadir rutas
                4. Consultar datos de un participante
                5. Modificar la observación provisional
                6. Cambiar mínimo de kilómetros para la distinción
                7. Mostrar participantes con distinción
                8. Buscar participantes por ruta
                9. Mostrar distancia media acumulada
                10. Mostrar todas las rutas completadas
                11. Recuperar copia de seguridad
                12. Exportar ficha a JSON
                13. Importar ficha desde JSON
                14. Consultar historial completo
                15. Consultar historial de un participante
                16. Generar fichero binario
                17. Mostrar contenido del fichero binario
                18. Consultar registro por posición
                19. Modificar kilómetros de un registro
                0. Salir
                """);
    }
}
