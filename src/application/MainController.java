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
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import models.Building;

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
  private Rectangle colorCielo;

  @FXML
  private Label hora;

  @FXML
  private Button closeInventory;

  @FXML
  private Button construir;

  private int size = 10;
  private int iconSize = 100;
  private double gridAncho = 2666.67;
  private double gridAlto = 1500;
  private ArrayList<Building> buildings = new ArrayList<>();
  private Building selectedBuilding;
  private int day = 1;

  public void initialize() {

    buildings.add(new Building("centro"));
    buildings.add(new Building("tienda"));
    buildings.add(new Building("gimnasio"));
    buildings.add(new Building("dojo"));
    buildings.add(new Building("fuente"));

    prepararInventario();

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
      hora.setText("Día " + day +  " / " + nuevaHora);
    }));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();

    spawnPoke(1);

  }

  public void prepararInventario() {
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

    inventory.toBack();
  }

  public void closeInventory() {
    inventory.toBack();
    construir.setVisible(false);
  }

  public void openInventory(ActionEvent eventOrigen) {
    Button ubicacion = (Button) eventOrigen.getSource();
    ToggleButton[] toggleButtons = new ToggleButton[buildings.size()];
    ToggleGroup toggleGroup = new ToggleGroup();
    int numColumns = 4;
    int i = 0;

    addEdificio.getChildren().clear();

    for (Building building : buildings) {
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

    construir.setOnAction(event -> {
      construir(ubicacion, selectedBuilding);
    });

    inventory.toFront();
  }

  public void construir(Button ubicacion, Building edificio) {
    Image image = new Image(getClass().getResourceAsStream("/img/" + edificio.getImage() + ".png"));
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(ubicacion.getPrefHeight());
    imageView.setPreserveRatio(true);
    ubicacion.setGraphic(imageView);
    buildings.remove(selectedBuilding);
    ubicacion.setOnAction(null);
    ubicacion.setOnMouseEntered(null);
    ubicacion.setOnMouseExited(null);
    closeInventory();
  }


  public void spawnPoke(int number) {
    Image image = new Image(getClass().getResourceAsStream("/img/poke/" + number + ".png"));
    ImageView imageView = new ImageView(image);

    imageView.setFitWidth(75);
    imageView.setFitHeight(75);

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

