package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    InputStream archivo;
    try {
        archivo = PokeGenerator.class.getResourceAsStream("/poke.txt");
        if (archivo != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(archivo))) {
              String line;
              while ((line = reader.readLine()) != null) {
                  line = line.replaceFirst("\\.[^.]+$", "");
                  strings.add(line);
              }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
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
    InputStream archivo;
    try {
        archivo = PokeGenerator.class.getResourceAsStream("/poke.txt");
        if (archivo != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(archivo))) {
              String line;
              while ((line = reader.readLine()) != null) {
                  line = line.replaceFirst("\\.[^.]+$", "");
                  strings.add(line);
              }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return strings;
  }

}
