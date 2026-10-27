package Pruebas;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        try (Stream<Path> stream = Files.list(Path.of("T1/ActividadRepaso"))) {
            stream.filter(e->e.getFileName().toString().endsWith(".java")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
