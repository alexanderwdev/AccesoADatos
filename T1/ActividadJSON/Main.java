package ActividadJSON;

import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Clase principal que crea un anime, lo guarda en un archivo binario
 * y después lo recupera para guardarlo en formato JSON.
 *
 * @author Alexander Wheatley
 */
public class Main {

    /**
     * Crea los objetos del anime Naruto y sus datos relacionados.
     * Guarda el anime en un archivo, lo recupera y lo convierte a JSON.
     *
     * @param args argumentos recibidos al ejecutar el programa
     * @throws IOException si ocurre un error de entrada o salida
     */
    public static void main(String[] args) throws IOException {
        Path path = Path.of("T1/ActividadJSON");
        Path rutaFichero = path.resolve("anime.dat");

        List<String> habilidadesNaruto = List.of("Rasengan", "Shadow Clone Jutsu");
        List<String> habilidadesSasuke = List.of("Sharingan", "Chidori");
        List<String> habilidadesSakura = List.of("Medical Ninjutsu");

        Friend sasuke = new Friend("Sasuke Uchiha", "Uchiha", habilidadesSasuke);
        Friend sakura = new Friend("Sakura Haruno", null, habilidadesSakura);
        List<Friend> amigos = List.of(sasuke, sakura);

        MainCharacter naruto = new MainCharacter("Naruto Uzumaki", 12, habilidadesNaruto, true, amigos);

        Villain orochimaru = new Villain("Orochimaru", null);
        Villain itachi = new Villain("Itachi Uchiha", null);
        Villain pain = new Villain("Pain", "Akatsuki");

        List<Villain> villanos = new ArrayList<>();
        villanos.add(orochimaru);
        villanos.add(itachi);
        villanos.add(null);
        villanos.add(pain);

        HashMap<String, Double> puntuaciones = new HashMap<>();
        puntuaciones.put("IMDb", 8.3);
        puntuaciones.put("MyAnimeList", 7.9);

        SpecialEpisode episodioEspecial = new SpecialEpisode("Konoha Sports Festival", 11);
        List<SpecialEpisode> episodiosEspeciales = List.of(episodioEspecial);

        Season temporada1 = new Season(1, 57, null);
        Season temporada2 = new Season(2, 43, null);
        Season temporada3 = new Season(3, 41, episodiosEspeciales);
        List<Season> temporadas = Arrays.asList(temporada1, temporada2, temporada3);

        Anime anime = new Anime("Naruto", naruto, 220, true, villanos, puntuaciones, temporadas);
        System.out.println("Anime creado correctamente: " + anime.getNombreAnime());

        guardarAnime(rutaFichero, anime);

        Anime animeRecuperado = recuperarAnime();
        guardarJson(animeRecuperado);
    }

    /**
     * Guarda un objeto Anime en un archivo mediante serialización.
     *
     * @param ruta ruta del archivo donde se guardará el anime
     * @param anime objeto Anime que se desea guardar
     * @throws IOException si ocurre un error al guardar el archivo
     */
    public static void guardarAnime(Path ruta, Anime anime) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta.toFile()))) {
            oos.writeObject(anime);
            System.out.println("Anime guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar anime: " + e.getMessage());
        }
    }

    /**
     * Recupera un objeto Anime desde un archivo binario.
     *
     * @return objeto Anime recuperado o null si ocurre un error
     */
    public static Anime recuperarAnime() {
        Anime anime = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("T1/ActividadJSON/anime.dat"))) {
            anime = (Anime) ois.readObject();
            System.out.println("Anime recuperado correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al recuperar el anime: " + e.getMessage());
        }

        return anime;
    }

    /**
     * Guarda un objeto Anime en un archivo JSON con formato legible.
     *
     * @param anime objeto Anime que se desea convertir y guardar
     */
    public static void guardarJson(Anime anime) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("T1/ActividadJSON/anime.json")) {
            gson.toJson(anime, writer);
            System.out.println("JSON guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar JSON: " + e.getMessage());
        }
    }
}