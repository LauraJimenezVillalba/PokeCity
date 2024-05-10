package application;

import java.io.File;
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

    // hacer que lo tome de archivo?
    strings.add("Paco");
    strings.add("Juan");
    strings.add("Juana");
    strings.add("Francisco");
    strings.add("Francisca");
    strings.add("Bart");
    strings.add("Lisa");
    strings.add("Marge");
    strings.add("Maggie");
    strings.add("Homer");
    strings.add("Elsa");
    strings.add("Anna");
    strings.add("Olaf");
    strings.add("Stella");
    strings.add("Mario");
    strings.add("Luigi");
    strings.add("Daisy");
    strings.add("Marinette");
    strings.add("Adrien");
    strings.add("Tikki");
    strings.add("Plagg");
    strings.add("Pancake");
    strings.add("Cupcake");
    strings.add("Chocolate");
    strings.add("Sugar");
    strings.add("Cáctus");
    strings.add("Pétalo");
    strings.add("Burbuja");

    Collections.shuffle(strings);
    return strings.get(0);
  }

}
