package application;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import models.Building;
import models.Pokemon;

public class MainController {

  @FXML
  private GridPane cuadricula;

  @FXML
  private GridPane addEdificio;

  @FXML
  private AnchorPane fondo;

  @FXML
  private ScrollPane scrollCuadricula;

  @FXML
  private AnchorPane superiorGrid;

  @FXML
  private AnchorPane inventory;

  @FXML
  private AnchorPane verEdificio;

  @FXML
  private Rectangle colorCielo;

  @FXML
  private Label hora;

  @FXML
  private Button closeInventory;

  @FXML
  private Button closeEdificio;

  @FXML
  private Button construir;

  @FXML
  private Button correo;

  @FXML
  private Button cambiarPokeIzq;

  @FXML
  private Button cambiarPokeDer;

  @FXML
  private Label trabajador;

  @FXML
  private Label notifica;

  @FXML
  private Circle notificaCircle;

  @FXML
  private Label dineroText;

  @FXML
  private Label edificiosVacio;

  @FXML
  private Label nombreEdificio;

  @FXML
  private Label produccionEdificio;

  @FXML
  private Label claseEdificio;

  @FXML
  private ImageView imagenEdificio;

  @FXML
  private ImageView pokeTrabajador;

  private int size = 10;
  private int iconSize = 100;
  private int iconSizeGrande = 170;
  private int iconSizePeque = 75;
  private double gridAncho = 2666.67;
  private double gridAlto = 1500;
  private ArrayList<Pokemon> pokemones = new ArrayList<>();
  private ArrayList<Building> buildings = new ArrayList<>();
  private Building selectedBuilding;
  private int day = 1;
  private int pendiente = 0;
  private int dinero = 0;
  private int salarioBase = 1;
  private boolean intercalado = false;

  /**
   * Ajustes iniciales, creación de la cuadrícula y el tiempo
   */
  public void initialize() {

    notifica.setVisible(false);
    notificaCircle.setVisible(false);

    // edificios de prueba
    buildings.add(new Building("Centro Pokémon", "Medicina"));
    buildings.add(new Building("Dojo de Combate", "Combate"));
    buildings.add(new Building("Gimnasio Pokémon", "Combate"));
    buildings.add(new Building("Plaza de la fuente", "Servicios"));
    buildings.add(new Building("PókeMart", "Ventas"));

    // pokemon de prueba
    pokemones.add(new Pokemon("Bulbasaur", "Medicina", "Combate"));
    pokemones.add(new Pokemon("Ivysaur", "Combate", "Medicina"));
    pokemones.add(new Pokemon("Venusaur", "Ventas", "Servicios"));

    prepararInventarios();

    Button[][] buttons = new Button[size][size];
    colorCielo.setMouseTransparent(true);

    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        Button button = new Button("");
        buttons[row][col] = button;
        button.prefWidthProperty().bind(cuadricula.widthProperty().divide(size));
        button.prefHeightProperty().bind(cuadricula.heightProperty().divide(size));
        button.setOnAction(event -> openInventory(event));
        GridPane.setHalignment(button, HPos.CENTER);
        Image image = new Image(getClass().getResourceAsStream("/img/button/sale.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSize);
        imageView.setPreserveRatio(true);
        button.setOnMouseEntered(event -> button.setGraphic(imageView));
        button.setOnMouseExited(event -> button.setGraphic(null));
        cuadricula.add(button, col, row);
      }
    }

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
      String horaInicial = hora.getText();
      String[] partes = horaInicial.split("/");
      String horaParte = partes[1].trim();
      LocalTime tiempoInicial = LocalTime.parse(horaParte, DateTimeFormatter.ofPattern("HH:mm"));
      int minutos = tiempoInicial.getHour() * 60 + tiempoInicial.getMinute();
      minutos = minutos + 15;
      if (minutos >= 1440) {
        minutos = 0;
        day++;
      }
      int horas = minutos / 60;
      if (horas == 6) {
        colorCielo.setOpacity(0);
      } else if (horas == 20) {
        Color noche = Color.web("#000151");
        colorCielo.setOpacity(0.5);
        colorCielo.setFill(noche);
      }
      int minutosRestantes = minutos % 60;
      String nuevaHora = String.format("%02d:%02d", horas, minutosRestantes);
      if (nuevaHora.equals("12:00") || nuevaHora.equals("00:00")) {
        pendiente++;
        if (pendiente > 0) {
          notifica.setText(String.valueOf(pendiente));
          notifica.setVisible(true);
          notificaCircle.setVisible(true);
        }
      }
      hora.setText("Día " + day + " / " + nuevaHora);
      if (!intercalado) {
        for (Building edificio : buildings) {
          if (edificio.isDesplegado() && edificio.getTrabajador() != null) {
            dinero = dinero + calcularSalario(edificio.getTrabajador(), edificio);
          }
        }
        dineroText.setText(dinero + "k");
        intercalado = true;
      } else {
        intercalado = false;
      }

    }));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();

    for (Pokemon pokemon : pokemones) {
      String spawnNumber = pokemon.getImage();
      spawnPoke(spawnNumber);
    }

  }

  /**
   * Añade las imágenes de los botones y oculta las otras ventanas inicialmente
   */
  public void prepararInventarios() {
    Image imageCorreo = new Image(getClass().getResourceAsStream("/img/button/correo.png"));
    ImageView imageViewCorreo = new ImageView(imageCorreo);
    imageViewCorreo.setFitHeight(iconSizeGrande);
    imageViewCorreo.setPreserveRatio(true);
    correo.setGraphic(imageViewCorreo);

    Image imageDinero = new Image(getClass().getResourceAsStream("/img/button/dinero.png"));
    ImageView imageViewDinero = new ImageView(imageDinero);
    imageViewDinero.setFitHeight(iconSize);
    imageViewDinero.setPreserveRatio(true);
    dineroText.setGraphic(imageViewDinero);

    Image image = new Image(getClass().getResourceAsStream("/img/button/close.png"));
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(iconSize);
    imageView.setPreserveRatio(true);
    closeInventory.setOnAction(event -> closeInventory());
    closeInventory.setGraphic(imageView);

    Image image2 = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
    ImageView imageView2 = new ImageView(image2);
    imageView2.setFitHeight(iconSize);
    imageView2.setPreserveRatio(true);
    construir.setGraphic(imageView2);
    construir.setVisible(false);

    Image image3 = new Image(getClass().getResourceAsStream("/img/button/close.png"));
    ImageView imageView3 = new ImageView(image3);
    imageView3.setFitHeight(iconSize);
    imageView3.setPreserveRatio(true);
    closeEdificio.setOnAction(event -> closeEdificio());
    closeEdificio.setGraphic(imageView3);

    Image image4 = new Image(getClass().getResourceAsStream("/img/button/dinero.png"));
    ImageView imageView4 = new ImageView(image4);
    imageView4.setFitHeight(iconSizePeque);
    imageView4.setPreserveRatio(true);
    produccionEdificio.setGraphic(imageView4);

    Image image5 = new Image(getClass().getResourceAsStream("/img/button/izquierda.png"));
    ImageView imageView5 = new ImageView(image5);
    imageView5.setFitHeight(iconSize);
    imageView5.setPreserveRatio(true);
    cambiarPokeIzq.setGraphic(imageView5);

    Image image6 = new Image(getClass().getResourceAsStream("/img/button/derecha.png"));
    ImageView imageView6 = new ImageView(image6);
    imageView6.setFitHeight(iconSize);
    imageView6.setPreserveRatio(true);
    cambiarPokeDer.setGraphic(imageView6);

    inventory.toBack();
    verEdificio.toBack();
  }

  public void closeInventory() {
    inventory.toBack();
    construir.setVisible(false);
  }

  /**
   * Prepara el inventario añadiendo los edificios que tengas a los botones
   * 
   * @param eventOrigen
   */
  public void openInventory(ActionEvent eventOrigen) {
    Button ubicacion = (Button) eventOrigen.getSource();
    ToggleButton[] toggleButtons = new ToggleButton[buildings.size()];
    ToggleGroup toggleGroup = new ToggleGroup();
    int numColumns = 4;
    int i = 0;

    addEdificio.getChildren().clear();

    for (Building building : buildings) {
      if (!building.isDesplegado()) {
        ToggleButton toggleButton = new ToggleButton("");
        toggleButton.setUserData(building);
        toggleButton.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
          if (newToggle == null) {
            construir.setVisible(false);
          } else {
            construir.setVisible(true);
            selectedBuilding = (Building) newToggle.getUserData();
          }
        });
        toggleButtons[i] = toggleButton;
        toggleButton.prefWidthProperty().bind(cuadricula.widthProperty().divide(size));
        toggleButton.prefHeightProperty().bind(cuadricula.heightProperty().divide(size));
        GridPane.setHalignment(toggleButton, HPos.CENTER);
        Image image =
            new Image(getClass().getResourceAsStream("/img/" + building.getImage() + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(toggleButton.getPrefHeight());
        imageView.setPreserveRatio(true);
        toggleButton.setGraphic(imageView);
        addEdificio.add(toggleButton, i % numColumns, i / numColumns);
        i++;
      }
    }

    if (i == 0) {
      edificiosVacio.setVisible(true);
    } else {
      edificiosVacio.setVisible(false);
    }

    construir.setOnAction(event -> {
      construir(ubicacion, selectedBuilding);
    });

    inventory.toFront();
  }

  /**
   * Toma el edificio seleccionado y lo constuye en la ubicación que hayas pulsado antes
   * 
   * @param ubicacion
   * @param edificio
   */
  public void construir(Button ubicacion, Building edificio) {
    Image image = new Image(getClass().getResourceAsStream("/img/" + edificio.getImage() + ".png"));
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(ubicacion.getPrefHeight());
    imageView.setPreserveRatio(true);
    ubicacion.setGraphic(imageView);
    ubicacion.setOnAction(event -> infoEdificio(event));
    ubicacion.setOnMouseEntered(null);
    ubicacion.setOnMouseExited(null);
    edificio.setDesplegado(true);
    ubicacion.setUserData(edificio);
    closeInventory();
  }

  public void closeEdificio() {
    verEdificio.toBack();
  }

  /**
   * Muestra el menú de información del edificio seleccionado
   * 
   * @param eventOrigen
   */
  public void infoEdificio(ActionEvent eventOrigen) {
    Button ubicacion = (Button) eventOrigen.getSource();
    Building edificio = (Building) ubicacion.getUserData();
    Image image = new Image(getClass().getResourceAsStream("/img/" + edificio.getImage() + ".png"));
    imagenEdificio.setFitHeight(ubicacion.getPrefHeight());
    imagenEdificio.setPreserveRatio(true);
    nombreEdificio.setText(edificio.getImage());
    imagenEdificio.setImage(image);
    claseEdificio.setText(edificio.getClase());
    Image imageClase =
        new Image(getClass().getResourceAsStream("/img/clases/" + edificio.getClase() + ".png"));
    ImageView imageViewClase = new ImageView(imageClase);
    imageViewClase.setFitHeight(iconSizePeque);
    imageViewClase.setPreserveRatio(true);
    claseEdificio.setGraphic(imageViewClase);
    if (edificio.getTrabajador() == null) {
      produccionEdificio.setText("Produce " + 0 + "k cada 30min");
      Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
      pokeTrabajador.setFitHeight(iconSize);
      pokeTrabajador.setPreserveRatio(true);
      pokeTrabajador.setImage(imageEmpty);
      trabajador.setText("Sin trabajador");
      trabajador.setGraphic(null);
    } else {
      produccionEdificio.setText("Produce " + calcularSalario(edificio.getTrabajador(), edificio) + "k cada 30min");
      Image imagePoke = new Image(getClass()
          .getResourceAsStream("/img/poke/" + edificio.getTrabajador().getImage() + ".png"));
      pokeTrabajador.setFitHeight(iconSize);
      pokeTrabajador.setPreserveRatio(true);
      pokeTrabajador.setImage(imagePoke);
      trabajador.setText(edificio.getTrabajador().getImage());
      Image imageEstado =
          new Image(getClass().getResourceAsStream("/img/button/" + calcularSatisfaccion(edificio.getTrabajador(), edificio) + ".png"));
      ImageView imageView = new ImageView(imageEstado);
      imageView.setFitHeight(iconSizePeque);
      imageView.setPreserveRatio(true);
      trabajador.setGraphic(imageView);
    }
    cambiarPokeDer.setOnAction(event -> cambiarTrabajador(event, edificio));
    cambiarPokeIzq.setOnAction(event -> retrocederTrabajador(event, edificio));
    verEdificio.toFront();
  }

  /**
   * Calcula que imagen mostrar en la satisfaccion del Poke trabajador
   * @param pokeTrabajador
   * @param edificio
   * @return
   */
  private String calcularSatisfaccion(Pokemon pokeTrabajador, Building edificio) {
    if (pokeTrabajador.getLike() == edificio.getClase()) {
      return "feliz";
    } else if (pokeTrabajador.getDislike() == edificio.getClase()) {
	  return "triste";
	} else {
	  return "normal";
	}
  }
  
  private int calcularSalario(Pokemon pokeTrabajador, Building edificio) {
    if (pokeTrabajador.getLike() == edificio.getClase()) {
      return salarioBase * 2;
    } else if (pokeTrabajador.getDislike() == edificio.getClase()) {
      return 0;
    } else {
      return salarioBase;
    } 
  }

  /**
   * Desplaza hacia adelante el trabajador de la lista de disponibles (hacer para que si ya trabaja
   * en un edificio no sala en otro)
   * 
   * @param eventOrigen
   * @param edificio
   */
  public void cambiarTrabajador(ActionEvent eventOrigen, Building edificio) {
    if (edificio.getTrabajador() == null) {
      Pokemon proximoTrabajador = null;
      for (Pokemon pokemon : pokemones) {
        if (!pokemon.isTrabaja()) {
          proximoTrabajador = pokemon;
          break;
        }
      }
      if (proximoTrabajador != null) {
        edificio.setTrabajador(proximoTrabajador);
        produccionEdificio.setText("Produce " + calcularSalario(edificio.getTrabajador(), edificio) + "k cada 30min");
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador.setFitHeight(iconSize);
        pokeTrabajador.setPreserveRatio(true);
        pokeTrabajador.setImage(imagePoke);
        trabajador.setText(proximoTrabajador.getImage());
        Image image =
            new Image(getClass().getResourceAsStream("/img/button/" + calcularSatisfaccion(edificio.getTrabajador(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador.setGraphic(imageView);
      } else {
        produccionEdificio.setText("Produce " + 0 + "k cada 30min");
        edificio.setTrabajador(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador.setFitHeight(iconSize);
        pokeTrabajador.setPreserveRatio(true);
        pokeTrabajador.setImage(imageEmpty);
        trabajador.setText("Sin trabajador");
        trabajador.setGraphic(null);
      }
    } else {
      int currentIndex = pokemones.indexOf(edificio.getTrabajador());
      int nextIndex = (currentIndex + 1) % pokemones.size();
      Pokemon proximoTrabajador = null;
      if (currentIndex != pokemones.size() - 1) {
        for (int i = nextIndex; i != currentIndex; i = (i + 1) % pokemones.size()) {
          Pokemon pokemon = pokemones.get(i);
          if (i == pokemones.size() - 1 && pokemon.isTrabaja()) {
	        break;
          } else if (!pokemon.isTrabaja()) {
            proximoTrabajador = pokemon;
            break;
          }
        }
      }
      if (proximoTrabajador != null) {
        edificio.getTrabajador().setTrabaja(false);
        edificio.setTrabajador(proximoTrabajador);
        produccionEdificio.setText("Produce " + calcularSalario(edificio.getTrabajador(), edificio) + "k cada 30min");
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador.setFitHeight(iconSize);
        pokeTrabajador.setPreserveRatio(true);
        pokeTrabajador.setImage(imagePoke);
        trabajador.setText(proximoTrabajador.getImage());
        Image image =
            new Image(getClass().getResourceAsStream("/img/button/" + calcularSatisfaccion(edificio.getTrabajador(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador.setGraphic(imageView);
      } else {
        produccionEdificio.setText("Produce " + 0 + "k cada 30min");
        edificio.getTrabajador().setTrabaja(false);
        edificio.setTrabajador(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador.setFitHeight(iconSize);
        pokeTrabajador.setPreserveRatio(true);
        pokeTrabajador.setImage(imageEmpty);
        trabajador.setText("Sin trabajador");
        trabajador.setGraphic(null);
      }
    }
  }


  /**
   * Desplaza hacia atrás el trabajador de la lista de disponibles (hacer para que si ya trabaja en
   * un edificio no sala en otro)
   * 
   * @param eventOrigen
   * @param edificio
   */
  public void retrocederTrabajador(ActionEvent eventOrigen, Building edificio) {
    if (edificio.getTrabajador() == null) {
      Pokemon proximoTrabajador = null;
      for (int i = pokemones.size() - 1; i >= 0; i--) {
        Pokemon pokemon = pokemones.get(i);
        if (!pokemon.isTrabaja()) {
          proximoTrabajador = pokemon;
          break;
        }
      }
      if (proximoTrabajador != null) {
        edificio.setTrabajador(proximoTrabajador);
        produccionEdificio.setText("Produce " + calcularSalario(edificio.getTrabajador(), edificio) + "k cada 30min");
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador.setFitHeight(iconSize);
        pokeTrabajador.setPreserveRatio(true);
        pokeTrabajador.setImage(imagePoke);
        trabajador.setText(proximoTrabajador.getImage());
        Image image =
            new Image(getClass().getResourceAsStream("/img/button/" + calcularSatisfaccion(edificio.getTrabajador(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador.setGraphic(imageView);
      } else {
        produccionEdificio.setText("Produce " + 0 + "k cada 30min");
        edificio.setTrabajador(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador.setFitHeight(iconSize);
        pokeTrabajador.setPreserveRatio(true);
        pokeTrabajador.setImage(imageEmpty);
        trabajador.setText("Sin trabajador");
        trabajador.setGraphic(null);
      }
    } else {
      int currentIndex = pokemones.indexOf(edificio.getTrabajador());
      int previousIndex = (currentIndex - 1 + pokemones.size()) % pokemones.size();
      Pokemon proximoTrabajador = null;
      if (currentIndex != 0) {
        for (int i = previousIndex; i != currentIndex; i =
            (i - 1 + pokemones.size()) % pokemones.size()) {
          Pokemon pokemon = pokemones.get(i);
          if (i == 0 && pokemon.isTrabaja()) {
            break;
          } else if (!pokemon.isTrabaja()) {
            proximoTrabajador = pokemon;
            break;
          }
        }
      }
      if (proximoTrabajador != null) {
        edificio.getTrabajador().setTrabaja(false);
        edificio.setTrabajador(proximoTrabajador);
        produccionEdificio.setText("Produce " + calcularSalario(edificio.getTrabajador(), edificio) + "k cada 30min");
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador.setFitHeight(iconSize);
        pokeTrabajador.setPreserveRatio(true);
        pokeTrabajador.setImage(imagePoke);
        trabajador.setText(proximoTrabajador.getImage());
        Image image =
            new Image(getClass().getResourceAsStream("/img/button/" + calcularSatisfaccion(edificio.getTrabajador(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador.setGraphic(imageView);
      } else {
        produccionEdificio.setText("Produce " + 0 + "k cada 30min");
        edificio.getTrabajador().setTrabaja(false);
        edificio.setTrabajador(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador.setFitHeight(iconSize);
        pokeTrabajador.setPreserveRatio(true);
        pokeTrabajador.setImage(imageEmpty);
        trabajador.setText("Sin trabajador");
        trabajador.setGraphic(null);
      }

    }
  }


  /**
   * Coloca el sprite del pokémon indicado y le da movimiento
   * 
   * @param spawnNumber
   */
  public void spawnPoke(String spawnNumber) {
    Image image = new Image(getClass().getResourceAsStream("/img/poke/" + spawnNumber + ".png"));
    ImageView imageView = new ImageView(image);

    imageView.setFitWidth(iconSizePeque);
    imageView.setFitHeight(iconSizePeque);

    superiorGrid.getChildren().add(imageView);

    double centerX = gridAncho / 2 - imageView.getFitWidth() / 2;
    double centerY = gridAlto / 2 - imageView.getFitHeight() / 2;
    imageView.setX(centerX);
    imageView.setY(centerY);

    imageView.toFront();

    Timeline timeline =
        new Timeline(new KeyFrame(Duration.seconds(2.1), event -> movePoke(imageView, 267, 150)));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  /**
   * Decide aleatoriamente hacia donde se mueve el sprite
   * 
   * @param imageView
   * @param moveX
   * @param moveY
   */
  private void movePoke(ImageView imageView, double moveX, double moveY) {

    Random random = new Random();
    int opcion = random.nextInt(5);

    TranslateTransition transition = new TranslateTransition();
    transition.setNode(imageView);
    transition.setCycleCount(1);
    transition.setInterpolator(Interpolator.LINEAR);
    transition.setDuration(Duration.seconds(2));

    double currentX = imageView.getX() + imageView.getTranslateX();
    double currentY = imageView.getY() + imageView.getTranslateY();

    switch (opcion) {
      case 0:
        if (currentX + 3 < gridAncho - moveX - imageView.getFitWidth() / 2) {
          transition.setByX(moveX);
        } else {
          transition.setByX(-moveX);
        }
        break;
      case 1:
        if (currentX - 3 > moveX - imageView.getFitWidth() / 2) {
          transition.setByX(-moveX);
        } else {
          transition.setByX(moveX);
        }
        break;
      case 2:
        if (currentY + 3 < gridAlto - moveY - imageView.getFitHeight() / 2) {
          transition.setByY(moveY);
        } else {
          transition.setByY(-moveY);
        }
        break;
      case 3:
        if (currentY - 3 > moveY - imageView.getFitHeight() / 2) {
          transition.setByY(-moveY);
        } else {
          transition.setByY(moveY);
        }
        break;
      case 4:
        break;
    }

    transition.play();
  }



}

