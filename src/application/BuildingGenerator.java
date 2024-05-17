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
      case "Espectáculos":
        return randomEspectaculos();
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
    strings.add("Espectáculos");

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
    edificios.add(new Building("Domo del Pokeathlón", "Combate"));
    edificios.add(new Building("Zona Safari", "Combate"));
    edificios.add(new Building("Liga Pokémon", "Combate"));
    edificios.add(new Building("Gran estadio", "Combate"));

    Collections.shuffle(edificios);
    return edificios.get(0);
  }
  
  public static Building randomMedicina() {
    List<Building> edificios = new ArrayList<>();

    edificios.add(new Building("Centro Pokémon", "Medicina"));
    edificios.add(new Building("Laboratorio de investigación", "Medicina"));
    edificios.add(new Building("Guardería Pokémon", "Medicina"));
    edificios.add(new Building("Museo de fósiles", "Medicina"));
    edificios.add(new Building("Museo de ciencias", "Medicina"));
    edificios.add(new Building("Farmacia", "Medicina"));

    Collections.shuffle(edificios);
    return edificios.get(0);
  }
  
  public static Building randomServicios() {
    List<Building> edificios = new ArrayList<>();

    edificios.add(new Building("Plaza de la fuente", "Servicios"));
    edificios.add(new Building("Escuela Pokémon", "Servicios"));
    edificios.add(new Building("Biblioteca", "Servicios"));
    edificios.add(new Building("Gran hotel", "Servicios"));
    edificios.add(new Building("Piscina municipal", "Servicios"));
    edificios.add(new Building("Estación", "Servicios"));

    Collections.shuffle(edificios);
    return edificios.get(0);
  }
  
  public static Building randomEspectaculos() {
    List<Building> edificios = new ArrayList<>();

    edificios.add(new Building("Cadena de televisión", "Espectáculos"));
    edificios.add(new Building("Sindicato de concursos", "Espectáculos"));
    edificios.add(new Building("Casino", "Espectáculos"));
    edificios.add(new Building("Teatro de las Geisha", "Espectáculos"));
    edificios.add(new Building("Hall de conciertos", "Espectáculos"));
    edificios.add(new Building("Carpa de las reliquias", "Espectáculos"));

    Collections.shuffle(edificios);
    return edificios.get(0);
  }

  public static Building randomComida() {
    List<Building> edificios = new ArrayList<>();

    edificios.add(new Building("Cafetería Pokémon", "Comida"));
    edificios.add(new Building("Restaurante de lujo", "Comida"));
    edificios.add(new Building("Mercado central", "Comida"));
    edificios.add(new Building("Restaurante familiar", "Comida"));
    edificios.add(new Building("Granjas", "Comida"));
    edificios.add(new Building("Buffet Libre", "Comida"));

    Collections.shuffle(edificios);
    return edificios.get(0);
  }
  
  public static Building randomVentas() {
    List<Building> edificios = new ArrayList<>();

    edificios.add(new Building("PókeMart", "Ventas"));
    edificios.add(new Building("Floristería", "Ventas"));
    edificios.add(new Building("Almacenes", "Ventas"));
    edificios.add(new Building("Trabajos de forja", "Ventas"));
    edificios.add(new Building("Centro comercial", "Ventas"));
    edificios.add(new Building("Grandes almacenes", "Ventas"));

    Collections.shuffle(edificios);
    return edificios.get(0);
  }
  
  public static Building randomVivienda() {
    List<Building> edificios = new ArrayList<>();

    edificios.add(new Building("Villa Moderna", "Vivienda"));
    edificios.add(new Building("Rincón Marino", "Vivienda"));
    edificios.add(new Building("Finca Maravillosa", "Vivienda"));
    edificios.add(new Building("Retiro Nevado", "Vivienda"));
    edificios.add(new Building("Escondite playero", "Vivienda"));
    edificios.add(new Building("Edificios tradicionales", "Vivienda"));

    Collections.shuffle(edificios);
    return edificios.get(0);
  }

}
