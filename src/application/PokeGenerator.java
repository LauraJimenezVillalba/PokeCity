package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import models.Pokemon;

public class PokeGenerator {

  public static Pokemon newPoke() {
    String[] selectedStrings = likesAndDislikes();
    return new Pokemon(name(), species(), selectedStrings[0], selectedStrings[1]);
  }

  public static String[] likesAndDislikes() {
    List<String> strings = new ArrayList<>();

    strings.add("Combate");
    strings.add("Comida");
    strings.add("Medicina");
    strings.add("Servicios");
    strings.add("Ventas");
    strings.add("Espectáculos");

    Collections.shuffle(strings);
    String[] selectedStrings = new String[2];
    selectedStrings[0] = strings.get(0);
    selectedStrings[1] = strings.get(1);
    return selectedStrings;
  }

  public static String species() {
    List<String> strings = new ArrayList<>();
    URL carpetaURL = Main.class.getResource("/img/poke/");

    if (carpetaURL != null) {
      File carpeta = new File(carpetaURL.getFile());

      if (carpeta.exists() && carpeta.isDirectory()) {
        File[] archivos = carpeta.listFiles();
        for (File archivo : archivos) {
          if (archivo.isFile()) {
            String nombreArchivo = archivo.getName();
            int posPunto = nombreArchivo.lastIndexOf(".");
            if (posPunto > 0) {
              nombreArchivo = nombreArchivo.substring(0, posPunto);
            }
            strings.add(nombreArchivo);
          }
        }
      }
    }

    Collections.shuffle(strings);
    return strings.get(0);
  }

  public static String name() {
    List<String> strings = new ArrayList<>();
    InputStream archivo;
    try {
      archivo = new FileInputStream("names.txt");
      if (archivo != null) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(archivo))) {
          String line;
          while ((line = reader.readLine()) != null) {
            strings.add(line);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    Collections.shuffle(strings);
    return strings.get(0);
  }

  public static List<String> allSpecies() {
    List<String> strings = new ArrayList<>();
    URL carpetaURL = Main.class.getResource("/img/poke/");

    if (carpetaURL != null) {
      File carpeta = new File(carpetaURL.getFile());

      if (carpeta.exists() && carpeta.isDirectory()) {
        File[] archivos = carpeta.listFiles();
        for (File archivo : archivos) {
          if (archivo.isFile()) {
            String nombreArchivo = archivo.getName();
            int posPunto = nombreArchivo.lastIndexOf(".");
            if (posPunto > 0) {
              nombreArchivo = nombreArchivo.substring(0, posPunto);
            }
            strings.add(nombreArchivo);
          }
        }
      }
    }

    return strings;
  }

}
