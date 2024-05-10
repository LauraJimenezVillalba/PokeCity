package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import models.Building;

public class BuildingGenerator {
  
  public static Building randomOfType(String clase) {
    switch (clase) {
      case "Combate":
          return randomCombate();
      case "Medicina":
        return randomMedicina();
      case "Servicios":
        return randomServicios();
      case "Comida":
        return randomComida();
      case "Ventas":
        return randomVentas();
      case "Vivienda":
        return randomVivienda();
    }
    return null;
  }
  
  public static String[] randomClases() {
    List<String> strings = new ArrayList<>();

    strings.add("Combate");
    strings.add("Comida");
    strings.add("Medicina");
    strings.add("Servicios");
    strings.add("Ventas");
    strings.add("Vivienda");

    Collections.shuffle(strings);
    String[] selectedStrings = new String[2];
    selectedStrings[0] = strings.get(0);
    selectedStrings[1] = strings.get(1);
    return selectedStrings;
  }
  
  public static Building randomCombate() {
    List<Building> edificios = new ArrayList<>();

    edificios.add(new Building("Dojo de Combate", "Combate"));
    edificios.add(new Building("Gimnasio Pokémon", "Combate"));

    Collections.shuffle(edificios);
    return edificios.get(0);
  }
  
  public static Building randomMedicina() {
    List<Building> edificios = new ArrayList<>();

    edificios.add(new Building("Centro Pokémon", "Medicina"));

    Collections.shuffle(edificios);
    return edificios.get(0);
  }
  
  public static Building randomServicios() {
    List<Building> edificios = new ArrayList<>();

    edificios.add(new Building("Plaza de la fuente", "Servicios"));

    Collections.shuffle(edificios);
    return edificios.get(0);
  }
  
  public static Building randomComida() {
    List<Building> edificios = new ArrayList<>();

    edificios.add(new Building("Cafetería Pokémon", "Comida"));

    Collections.shuffle(edificios);
    return edificios.get(0);
  }
  
  public static Building randomVentas() {
    List<Building> edificios = new ArrayList<>();

    edificios.add(new Building("PókeMart", "Ventas"));

    Collections.shuffle(edificios);
    return edificios.get(0);
  }
  
  public static Building randomVivienda() {
    List<Building> edificios = new ArrayList<>();

    edificios.add(new Building("Villa Moderna", "Vivienda"));
    edificios.add(new Building("Rincón Marino", "Vivienda"));
    edificios.add(new Building("Finca Maravillosa", "Vivienda"));
    edificios.add(new Building("Retiro Nevado", "Vivienda"));

    Collections.shuffle(edificios);
    return edificios.get(0);
  }

}
