package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Building;
import models.Huevo;
import models.Partida;
import models.Pokemon;

public class MainController {

  @FXML
  private GridPane cuadricula;

  @FXML
  private GridPane addEdificio;

  @FXML
  private AnchorPane fondo;

  @FXML
  private AnchorPane addEdificioPane;

  @FXML
  private AnchorPane decision;

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
  private Button regaloAleatorio;

  @FXML
  private Button construir;

  @FXML
  private ToggleButton decision1;

  @FXML
  private ToggleButton decision2;

  @FXML
  private Button correo;

  @FXML
  private Button cambiarPokeIzq;

  @FXML
  private Button cambiarPokeDer;

  @FXML
  private Button cambiarPokeIzq2;

  @FXML
  private Button cambiarPokeDer2;

  @FXML
  private Button cambiarPokeIzq3;

  @FXML
  private Button cambiarPokeDer3;

  @FXML
  private Button cambiarPokeIzq4;

  @FXML
  private Button cambiarPokeDer4;

  @FXML
  private Button restart;

  @FXML
  private Label trabajador;

  @FXML
  private Label trabajador2;

  @FXML
  private Label trabajador3;

  @FXML
  private Label trabajador4;

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
  private ImageView logoPrincipal;

  @FXML
  private ImageView imagenEdificio;

  @FXML
  private ImageView pokeTrabajador;

  @FXML
  private ImageView pokeTrabajador2;

  @FXML
  private ImageView pokeTrabajador3;

  @FXML
  private ImageView pokeTrabajador4;

  @FXML
  private ImageView pokeViviendo;

  @FXML
  private Label nombreViviendo;

  @FXML
  private Label likePoke;

  @FXML
  private Label dislikePoke;

  @FXML
  private Label descTrabajo;

  @FXML
  private ImageView likeIcon;

  @FXML
  private ImageView dislikeIcon;

  @FXML
  private Label numeroPoke;

  @FXML
  private Button closeDecision;

  @FXML
  private Button decidirAceptar;

  @FXML
  private Button continuarPartida;

  @FXML
  private Button reiniciarPartida;

  @FXML
  private AnchorPane menuInicial;

  @FXML
  private Label deleteText;

  @FXML
  private Label regaloText;

  @FXML
  private Button cancelarReinicio;

  @FXML
  private Button confirmarReinicio;

  @FXML
  private Button verTrabajo;

  @FXML
  private Button closeDex;

  @FXML
  private AnchorPane pokedex;

  @FXML
  private AnchorPane pokedexPane;

  @FXML
  private GridPane pokedexGrid;

  @FXML
  private AnchorPane pantallaRegalos;

  @FXML
  private AnchorPane seleccionarRegalo;

  @FXML
  private Button closeRegalos;

  @FXML
  private Button amistadRegalo;

  @FXML
  private ProgressBar medidorAmistad;

  @FXML
  private Label cantidadHuevos;

  @FXML
  private Label regalosCombate;

  @FXML
  private Label regalosServicios;

  @FXML
  private Label regalosMedicina;

  @FXML
  private Label regalosEspectaculos;

  @FXML
  private Label regalosComida;

  @FXML
  private Label regalosVentas;

  @FXML
  private Button seleccionarCombate;

  @FXML
  private Button seleccionarServicios;

  @FXML
  private Button seleccionarMedicina;

  @FXML
  private Button seleccionarEspectaculos;

  @FXML
  private Button seleccionarComida;

  @FXML
  private Button seleccionarVentas;

  @FXML
  private Button mejorarEdificio;

  @FXML
  private Button comprarRegaloEdificio;

  @FXML
  private Button demoler;

  @FXML
  private HBox boxPoke;

  private final int size = 10;
  private int iconSize = 100;
  private int iconSizeGrande = 160;
  private int iconSizePeque = 75;
  private double gridAncho = 2866.67;
  private double gridAlto = 1700;
  private boolean menu = true;
  private int anchoCuadricula = 267;
  private int altoCuadricula = 150;
  private int maxMejoras = 3;

  private Partida partida;

  public MainController(Partida partida) {
    this.partida = partida;
  }

  public Partida getPartida() {
    return partida;
  }

  /**
   * Ajustes iniciales, creación de la cuadrícula y el tiempo
   */
  public void initialize() {

    applyCache(fondo);

    if (partida == null) {
      reiniciarPartida.setVisible(false);
      partida = new Partida(new ArrayList<Pokemon>(), new ArrayList<Building>(), 1, 0, 0, false,
          true, "11:45");
      partida.getPokemones().add(PokeGenerator.newPoke());
      partida.getBuildings().add(BuildingGenerator.randomVivienda());
    } else {
      reiniciarPartida.setVisible(true);
      partida.setEmpezado(true);
      for (Pokemon pokemon : partida.getPokemones()) {
        if (pokemon.isCasa()) {
          spawnPoke(pokemon);
        }
      }
    }

    prepararInventarios();
    setPokeNumber();
    calcularTotalRegalos();

    Button[][] buttons = new Button[size][size];
    colorCielo.setMouseTransparent(true);

    superiorGrid.setPrefHeight((size * altoCuadricula) + 200);
    cuadricula.setPrefHeight(size * altoCuadricula);
    cuadricula.getRowConstraints().clear();
    for (int i = 0; i < size; i++) {
      RowConstraints rowConstraints = new RowConstraints();
      rowConstraints.setPrefHeight(altoCuadricula);
      cuadricula.getRowConstraints().add(rowConstraints);
    }

    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        Button button = new Button("");
        buttons[row][col] = button;
        button.setPrefWidth(anchoCuadricula);
        button.setPrefHeight(altoCuadricula - 2);
        button.setOnAction(event -> openInventory(event));
        GridPane.setHalignment(button, HPos.CENTER);
        Image image = new Image(getClass().getResourceAsStream("/img/button/sale.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSize);
        imageView.setPreserveRatio(true);
        button.setOnMouseEntered(event -> button.setGraphic(imageView));
        button.setOnMouseExited(event -> button.setGraphic(null));
        button.getProperties().put("row", row);
        button.getProperties().put("col", col);
        cuadricula.add(button, col, row);
        for (Building building : partida.getBuildings()) {
          if (building.getCol() == col && building.getRow() == row) {
            construirTrasGuardado(button, building);
          }
        }
      }
    }

    avanceTiempo();
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> {
      avanceTiempo();
    }));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();

  }

  /**
   * Maneja todos los datos que cambian cuando el tiempo del juego avanza
   */
  public void avanceTiempo() {
    String horaInicial = partida.getHora();
    LocalTime tiempoInicial = LocalTime.parse(horaInicial, DateTimeFormatter.ofPattern("HH:mm"));
    int minutos = tiempoInicial.getHour() * 60 + tiempoInicial.getMinute();
    minutos = minutos + 15;
    if (minutos >= 1440) {
      minutos = 0;
      partida.setDay(partida.getDay() + 1);
    }
    int horas = minutos / 60;
    if (horas >= 6 && horas < 20) {
      colorCielo.setOpacity(0);
    } else if (horas >= 20 || horas < 6) {
      Color noche = Color.web("#000151");
      colorCielo.setOpacity(0.5);
      colorCielo.setFill(noche);
    }
    int minutosRestantes = minutos % 60;
    String nuevaHora = String.format("%02d:%02d", horas, minutosRestantes);
    partida.setHora(nuevaHora);
    if (nuevaHora.equals("12:00") || nuevaHora.equals("00:00")) {
      if (partida.getPendiente() < 99) {
        partida.setPendiente(partida.getPendiente() + 1);
      }
    }
    if (nuevaHora.equals("00:00")) {
      partida.setDinero(partida.getDinero() + (partida.getPokemones().size()) * 10);
    }
    if (partida.getPendiente() > 0 && !menu) {
      notifica.setText(String.valueOf(partida.getPendiente()));
      notifica.setVisible(true);
      notificaCircle.setVisible(true);
    }
    hora.setText("Día " + partida.getDay() + " / " + nuevaHora);
    if (!partida.isIntercalado()) {
      for (Building edificio : partida.getBuildings()) {
        if (edificio.isDesplegado() && edificio.getTrabajador() != null) {
          partida
              .setDinero(partida.getDinero() + calcularSalario(edificio.getTrabajador(), edificio));
        }
        if (edificio.isDesplegado() && edificio.getTrabajador2() != null) {
          partida.setDinero(
              partida.getDinero() + calcularSalario(edificio.getTrabajador2(), edificio));
        }
        if (edificio.isDesplegado() && edificio.getTrabajador3() != null) {
          partida.setDinero(
              partida.getDinero() + calcularSalario(edificio.getTrabajador3(), edificio));
        }
        if (edificio.isDesplegado() && edificio.getTrabajador4() != null) {
          partida.setDinero(
              partida.getDinero() + calcularSalario(edificio.getTrabajador4(), edificio));
        }
      }
      partida.setIntercalado(true);
    } else {
      partida.setIntercalado(false);
    }
    dineroText.setText(partida.getDinero() + "k");
    if (partida.getDinero() >= 50) {
      regaloAleatorio.setVisible(true);
      if (partida.getSelectedBuilding() != null
          && partida.getSelectedBuilding().getNumMejoras() == 0) {
        mejorarEdificio.setDisable(false);
        mejorarEdificio.setOnAction(event -> mejorarEdificio(partida.getSelectedBuilding()));
      } else if (partida.getSelectedBuilding() != null
          && partida.getSelectedBuilding().getNumMejoras() == 1 && partida.getDinero() >= 100) {
        mejorarEdificio.setDisable(false);
        mejorarEdificio.setOnAction(event -> mejorarEdificio(partida.getSelectedBuilding()));
      } else if (partida.getSelectedBuilding() != null
          && partida.getSelectedBuilding().getNumMejoras() == 2 && partida.getDinero() >= 200) {
        mejorarEdificio.setDisable(false);
        mejorarEdificio.setOnAction(event -> mejorarEdificio(partida.getSelectedBuilding()));
      }
    } else {
      regaloAleatorio.setVisible(false);
      mejorarEdificio.setDisable(true);
    }
    if (partida.getSelectedBuilding() != null) {
      if (!partida.getSelectedBuilding().getClase().equals("Vivienda")
          && partida.getSelectedBuilding().getNumMejoras() > 0
          && partida.getDinero() >= precioRegalos(partida.getSelectedBuilding())) {
        comprarRegaloEdificio.setVisible(true);
        comprarRegaloEdificio.setDisable(false);
        comprarRegaloEdificio
            .setOnAction(event -> comprarRegaloEdificio(partida.getSelectedBuilding()));
      } else if (!partida.getSelectedBuilding().getClase().equals("Vivienda")
          && partida.getSelectedBuilding().getNumMejoras() > 0
          && partida.getDinero() < precioRegalos(partida.getSelectedBuilding())) {
        comprarRegaloEdificio.setVisible(true);
        comprarRegaloEdificio.setDisable(true);
      } else {
        comprarRegaloEdificio.setVisible(false);
      }
    }
    for (Building edificio : partida.getBuildings()) {
      List<Huevo> huevosToRemove = new ArrayList<>();
      if (edificio.getIncubando().size() >= 1) {
        for (Huevo huevo : edificio.getIncubando()) {
          if (huevo.getTiempoEclosion().equals(partida.getHora())) {
            nacer(huevo, edificio);
            huevosToRemove.add(huevo);
          }
        }
      }
      for (Huevo huevo : huevosToRemove) {
        edificio.removeIncubando(huevo);
      }
    }
    setPokeNumber();
  }

  /**
   * Borra la partida guardada y recarga todo a 0
   */
  public void reiniciarPartida() {
    partida.setProximoReset(true);
    Stage stage = (Stage) fondo.getScene().getWindow();
    stage.close();
    try {
      Path savefilePath = Paths.get("savefile.ser");
      if (savefilePath.toFile().exists()) {
        Files.delete(savefilePath);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    Main newApp = new Main();
    Stage newStage = new Stage();
    newApp.start(newStage);
  }

  /**
   * Actualiza el texto del número de ciudadanos
   */
  public void setPokeNumber() {
    int conCasa = partida.getPokemones().size();
    int sinCasa = 0;
    for (Pokemon pokemon : partida.getPokemones()) {
      if (!pokemon.isCasa()) {
        sinCasa++;
        conCasa--;
      }
    }
    numeroPoke.setText(conCasa + " (" + sinCasa + ")");
  }

  /**
   * Añade las imágenes de los botones y oculta las otras ventanas inicialmente
   */
  public void prepararInventarios() {

    correo.setOnAction(event -> openDecision());
    restart.setOnAction(event -> openPrincipal());
    continuarPartida.setOnAction(event -> closePrincipal());
    reiniciarPartida.setOnAction(event -> protocoloReiniciarPartida());
    confirmarReinicio.setOnAction(event -> reiniciarPartida());
    cancelarReinicio.setOnAction(event -> cancelarReiniciarPartida());
    numeroPoke.setOnMouseClicked(event -> openDex());
    closeInventory.setOnAction(event -> closeInventory());
    closeDex.setOnAction(event -> closeDex());
    closeEdificio.setOnAction(event -> closeEdificio());
    closeDecision.setOnAction(event -> closeDecision());
    closeRegalos.setOnAction(event -> closeRegalos());
    regaloText.setOnMouseClicked(event -> openRegalos());
    regaloAleatorio.setOnAction(event -> addRegaloRandom());
    seleccionarRegalo.setOnMouseClicked(event -> closeModalRegalo());
    demoler.setOnAction(event -> demoler(partida.getSelectedBuilding()));
    openPrincipal();

    likeIcon.setImage(new Image(getClass().getResourceAsStream("/img/button/feliz.png")));
    dislikeIcon.setImage(new Image(getClass().getResourceAsStream("/img/button/triste.png")));
    logoPrincipal.setImage(new Image(getClass().getResourceAsStream("/img/logo.png")));

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

    Image imagePoke = new Image(getClass().getResourceAsStream("/img/button/poke.png"));
    ImageView imageViewPoke = new ImageView(imagePoke);
    imageViewPoke.setFitHeight(iconSize);
    imageViewPoke.setPreserveRatio(true);
    numeroPoke.setGraphic(imageViewPoke);
    pokedex.toBack();

    Image imageTiempo = new Image(getClass().getResourceAsStream("/img/button/tiempo.png"));
    ImageView imageViewTiempo = new ImageView(imageTiempo);
    imageViewTiempo.setFitHeight(iconSize);
    imageViewTiempo.setPreserveRatio(true);
    hora.setGraphic(imageViewTiempo);

    Image image = new Image(getClass().getResourceAsStream("/img/button/close.png"));
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(iconSize);
    imageView.setPreserveRatio(true);
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

    Image image5dos = new Image(getClass().getResourceAsStream("/img/button/izquierda.png"));
    ImageView imageView5dos = new ImageView(image5dos);
    imageView5dos.setFitHeight(iconSize);
    imageView5dos.setPreserveRatio(true);
    cambiarPokeIzq2.setGraphic(imageView5dos);

    Image image5tres = new Image(getClass().getResourceAsStream("/img/button/izquierda.png"));
    ImageView imageView5tres = new ImageView(image5tres);
    imageView5tres.setFitHeight(iconSize);
    imageView5tres.setPreserveRatio(true);
    cambiarPokeIzq3.setGraphic(imageView5tres);

    Image image5cuatro = new Image(getClass().getResourceAsStream("/img/button/izquierda.png"));
    ImageView imageView5cuatro = new ImageView(image5cuatro);
    imageView5cuatro.setFitHeight(iconSize);
    imageView5cuatro.setPreserveRatio(true);
    cambiarPokeIzq4.setGraphic(imageView5cuatro);

    Image image6 = new Image(getClass().getResourceAsStream("/img/button/derecha.png"));
    ImageView imageView6 = new ImageView(image6);
    imageView6.setFitHeight(iconSize);
    imageView6.setPreserveRatio(true);
    cambiarPokeDer.setGraphic(imageView6);

    Image image6dos = new Image(getClass().getResourceAsStream("/img/button/derecha.png"));
    ImageView imageView6dos = new ImageView(image6dos);
    imageView6dos.setFitHeight(iconSize);
    imageView6dos.setPreserveRatio(true);
    cambiarPokeDer2.setGraphic(imageView6dos);

    Image image6tres = new Image(getClass().getResourceAsStream("/img/button/derecha.png"));
    ImageView imageView6tres = new ImageView(image6tres);
    imageView6tres.setFitHeight(iconSize);
    imageView6tres.setPreserveRatio(true);
    cambiarPokeDer3.setGraphic(imageView6tres);

    Image image6cuatro = new Image(getClass().getResourceAsStream("/img/button/derecha.png"));
    ImageView imageView6cuatro = new ImageView(image6cuatro);
    imageView6cuatro.setFitHeight(iconSize);
    imageView6cuatro.setPreserveRatio(true);
    cambiarPokeDer4.setGraphic(imageView6cuatro);

    Image image7 = new Image(getClass().getResourceAsStream("/img/button/restart.png"));
    ImageView imageView7 = new ImageView(image7);
    imageView7.setFitHeight(iconSizeGrande);
    imageView7.setPreserveRatio(true);
    restart.setGraphic(imageView7);

    Image image8 = new Image(getClass().getResourceAsStream("/img/button/close.png"));
    ImageView imageView8 = new ImageView(image8);
    imageView8.setFitHeight(iconSize);
    imageView8.setPreserveRatio(true);
    closeDecision.setGraphic(imageView8);

    Image image9 = new Image(getClass().getResourceAsStream("/img/button/normal.png"));
    ImageView imageView9 = new ImageView(image9);
    imageView9.setFitHeight(iconSize);
    imageView9.setPreserveRatio(true);
    decidirAceptar.setGraphic(imageView9);
    decidirAceptar.setVisible(false);

    Image image10 = new Image(getClass().getResourceAsStream("/img/button/close.png"));
    ImageView imageView10 = new ImageView(image10);
    imageView10.setFitHeight(iconSize);
    imageView10.setPreserveRatio(true);
    closeDex.setGraphic(imageView10);

    Image image11 = new Image(getClass().getResourceAsStream("/img/button/close.png"));
    ImageView imageView11 = new ImageView(image11);
    imageView11.setFitHeight(iconSize);
    imageView11.setPreserveRatio(true);
    closeRegalos.setGraphic(imageView11);

    Image imageContinuar = new Image(getClass().getResourceAsStream("/img/button/derecha.png"));
    ImageView imageViewContinuar = new ImageView(imageContinuar);
    imageViewContinuar.setFitHeight(iconSize);
    imageViewContinuar.setPreserveRatio(true);
    continuarPartida.setGraphic(imageViewContinuar);

    Image imageParaReinicio = new Image(getClass().getResourceAsStream("/img/button/triste.png"));
    ImageView imageViewParaReinicio = new ImageView(imageParaReinicio);
    imageViewParaReinicio.setFitHeight(iconSize);
    imageViewParaReinicio.setPreserveRatio(true);
    reiniciarPartida.setGraphic(imageViewParaReinicio);

    Image imageCancelar = new Image(getClass().getResourceAsStream("/img/button/close.png"));
    ImageView imageViewCancelar = new ImageView(imageCancelar);
    imageViewCancelar.setFitHeight(iconSize);
    imageViewCancelar.setPreserveRatio(true);
    cancelarReinicio.setGraphic(imageViewCancelar);

    Image imageReiniciar = new Image(getClass().getResourceAsStream("/img/button/normal.png"));
    ImageView imageViewReiniciar = new ImageView(imageReiniciar);
    imageViewReiniciar.setFitHeight(iconSize);
    imageViewReiniciar.setPreserveRatio(true);
    confirmarReinicio.setGraphic(imageViewReiniciar);

    Image imageTrabajo = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
    ImageView imageViewTrabajo = new ImageView(imageTrabajo);
    imageViewTrabajo.setFitHeight(iconSize);
    imageViewTrabajo.setPreserveRatio(true);
    verTrabajo.setGraphic(imageViewTrabajo);

    Image imageRegalo = new Image(getClass().getResourceAsStream("/img/button/regalo.png"));
    ImageView imageViewRegalo = new ImageView(imageRegalo);
    imageViewRegalo.setFitHeight(iconSize);
    imageViewRegalo.setPreserveRatio(true);
    regaloText.setGraphic(imageViewRegalo);

    Image imageRegaloCombate = new Image(getClass().getResourceAsStream("/img/clases/Combate.png"));
    ImageView imageViewRegaloCombate = new ImageView(imageRegaloCombate);
    imageViewRegaloCombate.setFitHeight(iconSize);
    imageViewRegaloCombate.setPreserveRatio(true);
    regalosCombate.setGraphic(imageViewRegaloCombate);

    Image imageRegaloServicios =
        new Image(getClass().getResourceAsStream("/img/clases/Servicios.png"));
    ImageView imageViewRegaloServicios = new ImageView(imageRegaloServicios);
    imageViewRegaloServicios.setFitHeight(iconSize);
    imageViewRegaloServicios.setPreserveRatio(true);
    regalosServicios.setGraphic(imageViewRegaloServicios);

    Image imageRegaloMedicina =
        new Image(getClass().getResourceAsStream("/img/clases/Medicina.png"));
    ImageView imageViewRegaloMedicina = new ImageView(imageRegaloMedicina);
    imageViewRegaloMedicina.setFitHeight(iconSize);
    imageViewRegaloMedicina.setPreserveRatio(true);
    regalosMedicina.setGraphic(imageViewRegaloMedicina);

    Image imageRegaloEspectaculos =
        new Image(getClass().getResourceAsStream("/img/clases/Espectáculos.png"));
    ImageView imageViewRegaloEspectaculos = new ImageView(imageRegaloEspectaculos);
    imageViewRegaloEspectaculos.setFitHeight(iconSize);
    imageViewRegaloEspectaculos.setPreserveRatio(true);
    regalosEspectaculos.setGraphic(imageViewRegaloEspectaculos);

    Image imageRegaloComida = new Image(getClass().getResourceAsStream("/img/clases/Comida.png"));
    ImageView imageViewRegaloComida = new ImageView(imageRegaloComida);
    imageViewRegaloComida.setFitHeight(iconSize);
    imageViewRegaloComida.setPreserveRatio(true);
    regalosComida.setGraphic(imageViewRegaloComida);

    Image imageRegaloVentas = new Image(getClass().getResourceAsStream("/img/clases/Ventas.png"));
    ImageView imageViewRegaloVentas = new ImageView(imageRegaloVentas);
    imageViewRegaloVentas.setFitHeight(iconSize);
    imageViewRegaloVentas.setPreserveRatio(true);
    regalosVentas.setGraphic(imageViewRegaloVentas);

    Image imageAmistad = new Image(getClass().getResourceAsStream("/img/button/amistad.png"));
    ImageView imageViewAmistad = new ImageView(imageAmistad);
    imageViewAmistad.setFitHeight(iconSize);
    imageViewAmistad.setPreserveRatio(true);
    amistadRegalo.setGraphic(imageViewAmistad);

    Image imageRegaloAleatorio =
        new Image(getClass().getResourceAsStream("/img/button/regalo.png"));
    ImageView imageViewRegaloAleatorio = new ImageView(imageRegaloAleatorio);
    imageViewRegaloAleatorio.setFitHeight(iconSize);
    imageViewRegaloAleatorio.setPreserveRatio(true);
    regaloAleatorio.setGraphic(imageViewRegaloAleatorio);

    Image imageSeleccionarCombate =
        new Image(getClass().getResourceAsStream("/img/clases/Combate.png"));
    ImageView imageViewSeleccionarCombate = new ImageView(imageSeleccionarCombate);
    imageViewSeleccionarCombate.setFitHeight(iconSize);
    imageViewSeleccionarCombate.setPreserveRatio(true);
    seleccionarCombate.setGraphic(imageViewSeleccionarCombate);

    Image imageSeleccionarServicios =
        new Image(getClass().getResourceAsStream("/img/clases/Servicios.png"));
    ImageView imageViewSeleccionarServicios = new ImageView(imageSeleccionarServicios);
    imageViewSeleccionarServicios.setFitHeight(iconSize);
    imageViewSeleccionarServicios.setPreserveRatio(true);
    seleccionarServicios.setGraphic(imageViewSeleccionarServicios);

    Image imageSeleccionarMedicina =
        new Image(getClass().getResourceAsStream("/img/clases/Medicina.png"));
    ImageView imageViewSeleccionarMedicina = new ImageView(imageSeleccionarMedicina);
    imageViewSeleccionarMedicina.setFitHeight(iconSize);
    imageViewSeleccionarMedicina.setPreserveRatio(true);
    seleccionarMedicina.setGraphic(imageViewSeleccionarMedicina);

    Image imageSeleccionarEspectaculos =
        new Image(getClass().getResourceAsStream("/img/clases/Espectáculos.png"));
    ImageView imageViewSeleccionarEspectaculos = new ImageView(imageSeleccionarEspectaculos);
    imageViewSeleccionarEspectaculos.setFitHeight(iconSize);
    imageViewSeleccionarEspectaculos.setPreserveRatio(true);
    seleccionarEspectaculos.setGraphic(imageViewSeleccionarEspectaculos);

    Image imageSeleccionarComida =
        new Image(getClass().getResourceAsStream("/img/clases/Comida.png"));
    ImageView imageViewSeleccionarComida = new ImageView(imageSeleccionarComida);
    imageViewSeleccionarComida.setFitHeight(iconSize);
    imageViewSeleccionarComida.setPreserveRatio(true);
    seleccionarComida.setGraphic(imageViewSeleccionarComida);

    Image imageSeleccionarVentas =
        new Image(getClass().getResourceAsStream("/img/clases/Ventas.png"));
    ImageView imageViewSeleccionarVentas = new ImageView(imageSeleccionarVentas);
    imageViewSeleccionarVentas.setFitHeight(iconSize);
    imageViewSeleccionarVentas.setPreserveRatio(true);
    seleccionarVentas.setGraphic(imageViewSeleccionarVentas);

    Image imageMejorar = new Image(getClass().getResourceAsStream("/img/button/dinero.png"));
    ImageView imageViewMejorar = new ImageView(imageMejorar);
    imageViewMejorar.setFitHeight(iconSizePeque);
    imageViewMejorar.setPreserveRatio(true);
    mejorarEdificio.setGraphic(imageViewMejorar);

    Image imageDemoler = new Image(getClass().getResourceAsStream("/img/button/demoler.png"));
    ImageView imageViewDemoler = new ImageView(imageDemoler);
    imageViewDemoler.setFitHeight(iconSizeGrande);
    imageViewDemoler.setPreserveRatio(true);
    demoler.setGraphic(imageViewDemoler);

    Image imageHuevo = new Image(getClass().getResourceAsStream("/img/button/huevo.png"));
    ImageView imageViewHuevo = new ImageView(imageHuevo);
    imageViewHuevo.setFitHeight(200);
    imageViewHuevo.setPreserveRatio(true);
    cantidadHuevos.setGraphic(imageViewHuevo);

    inventory.toBack();
    verEdificio.toBack();
    decision.toBack();
    pantallaRegalos.toBack();
    seleccionarRegalo.toBack();
  }

  /**
   * Prepara la pantalla de resetear partida
   */
  public void protocoloReiniciarPartida() {
    deleteText.setVisible(true);
    reiniciarPartida.setVisible(false);
    continuarPartida.setVisible(false);
    cancelarReinicio.setVisible(true);
    confirmarReinicio.setVisible(true);
  }

  /**
   * Vuelve al menú principal
   */
  public void cancelarReiniciarPartida() {
    deleteText.setVisible(false);
    reiniciarPartida.setVisible(true);
    continuarPartida.setVisible(true);
    cancelarReinicio.setVisible(false);
    confirmarReinicio.setVisible(false);
  }

  /**
   * Cierra el menú principal
   */
  public void closePrincipal() {
    menu = false;
    menuInicial.toBack();
    hora.setVisible(true);
    correo.setVisible(true);
    if (partida.getPendiente() > 0 && !menu) {
      notifica.setText(String.valueOf(partida.getPendiente()));
      notifica.setVisible(true);
      notificaCircle.setVisible(true);
    }
    dineroText.setVisible(true);
    restart.setVisible(true);
    numeroPoke.setVisible(true);
    deleteText.setVisible(false);
    regaloText.setVisible(true);
  }

  /**
   * Abre el menú principal
   */
  public void openPrincipal() {
    menu = true;
    if (!partida.isEmpezado()) {
      partida.setEmpezado(true);
    } else {
      continuarPartida.setText("Continuar partida");
      reiniciarPartida.setVisible(true);
    }
    menuInicial.toFront();
    hora.setVisible(false);
    correo.setVisible(false);
    notificaCircle.setVisible(false);
    notifica.setVisible(false);
    dineroText.setVisible(false);
    restart.setVisible(false);
    numeroPoke.setVisible(false);
    regaloText.setVisible(false);
  }

  /**
   * Cierra el inventario
   */
  public void closeInventory() {
    inventory.toBack();
    construir.setVisible(false);
  }

  /**
   * Devuelve el nombre de un pokémon con su especie
   * 
   * @param pokemon
   * @return
   */
  public String fullName(Pokemon pokemon) {
    return pokemon.getNombre() + " (" + pokemon.getImage() + ")";
  }

  /**
   * Prepara el menú de decisiones y crea una nueva si es necesario.
   */
  public void openDecision() {
    if (partida.getPendiente() > 0) {
      if (partida.isNewDecision()) {
        ToggleGroup toggleGroup = new ToggleGroup();
        decision1.setToggleGroup(toggleGroup);
        decision2.setToggleGroup(toggleGroup);
        Random rand = new Random();
        int numNoCasados = 0;
        int numeroAleatorio = 0;
        HBox hboxBoda = null;
        Pokemon[] noCasados = null;
        for (Pokemon pokemon : partida.getPokemones()) {
          if (!pokemon.isCasado() && !pokemon.isHijo() && pokemon.isCasa()) {
            numNoCasados++;
          }
        }
        if (numNoCasados >= 2) {
          numeroAleatorio = rand.nextInt(9);
          noCasados = partida.noCasados();
          Image image6 = new Image(
              getClass().getResourceAsStream("/img/poke/" + noCasados[0].getImage() + ".png"));
          ImageView imageView6 = new ImageView(image6);
          imageView6.setFitHeight(iconSizeGrande);
          imageView6.setPreserveRatio(true);
          Image image7 = new Image(
              getClass().getResourceAsStream("/img/poke/" + noCasados[1].getImage() + ".png"));
          ImageView imageView7 = new ImageView(image7);
          imageView7.setFitHeight(iconSizeGrande);
          imageView7.setPreserveRatio(true);
          hboxBoda = new HBox(imageView6, imageView7);
        } else {
          numeroAleatorio = rand.nextInt(6);
        }
        String[] clasesDecisiones = BuildingGenerator.randomClases();

        final Pokemon noCasado1;
        final Pokemon noCasado2;
        if (noCasados != null) {
          noCasado1 = noCasados[0];
          noCasado2 = noCasados[1];
        } else {
          noCasado1 = null;
          noCasado2 = null;
        }

        Pokemon newPoke1 = PokeGenerator.newPoke();
        Pokemon newPoke2 = PokeGenerator.newPoke();
        Image image = new Image(
            getClass().getResourceAsStream("/img/clases/" + clasesDecisiones[0] + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizeGrande);
        imageView.setPreserveRatio(true);
        Image image2 = new Image(
            getClass().getResourceAsStream("/img/clases/" + clasesDecisiones[1] + ".png"));
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitHeight(iconSizeGrande);
        imageView2.setPreserveRatio(true);
        Image image3 =
            new Image(getClass().getResourceAsStream("/img/poke/" + newPoke1.getImage() + ".png"));
        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitHeight(iconSizeGrande);
        imageView3.setPreserveRatio(true);
        Image image4 =
            new Image(getClass().getResourceAsStream("/img/poke/" + newPoke2.getImage() + ".png"));
        ImageView imageView4 = new ImageView(image4);
        imageView4.setFitHeight(iconSizeGrande);
        imageView4.setPreserveRatio(true);
        HBox hbox = new HBox(imageView3, imageView4);
        Image image5 = new Image(getClass().getResourceAsStream("/img/clases/Vivienda.png"));
        ImageView imageView5 = new ImageView(image5);
        imageView5.setFitHeight(iconSizeGrande);
        imageView5.setPreserveRatio(true);
        Image image6 = new Image(getClass().getResourceAsStream("/img/button/huevo.png"));
        ImageView imageView6 = new ImageView(image6);
        imageView6.setFitHeight(iconSizeGrande);
        imageView6.setPreserveRatio(true);

        switch (numeroAleatorio) {
          case 0:
            decision1.setText("+1 Edificio de clase " + clasesDecisiones[0]);
            decision2.setText("+1 Edificio de clase " + clasesDecisiones[1]);
            decision1.setGraphic(imageView);
            decision2.setGraphic(imageView2);
            break;
          case 1:
            decision1.setText("+1 Edificio de clase " + clasesDecisiones[0]);
            decision2.setText("+2 Futuros ciudadanos nuevos");
            decision1.setGraphic(imageView);
            decision2.setGraphic(hbox);
            break;
          case 2:
            decision1.setText("+1 Edificio de clase " + clasesDecisiones[0]);
            decision2.setText("+2 Futuros ciudadanos nuevos");
            decision1.setGraphic(imageView);
            decision2.setGraphic(hbox);
            break;
          case 3:
            decision1.setText("+2 Edificios de clase Vivienda");
            decision2.setText("+2 Futuros ciudadanos nuevos");
            decision1.setGraphic(imageView5);
            decision2.setGraphic(hbox);
            break;
          case 4:
            decision1.setText("+1 Edificio de clase " + clasesDecisiones[0]);
            decision2.setText("+1 huevo aún sin incubar");
            decision1.setGraphic(imageView);
            decision2.setGraphic(imageView6);
            break;
          case 5:
            decision1.setText("+2 Edificios de clase Vivienda");
            decision2.setText("+1 huevo aún sin incubar");
            decision1.setGraphic(imageView5);
            decision2.setGraphic(imageView6);
            break;
          case 6:
            decision1
                .setText(noCasados[0].getNombre() + " y " + noCasados[1].getNombre() + " se casan");
            decision2.setText("+2 Futuros ciudadanos nuevos");
            decision1.setGraphic(hboxBoda);
            decision2.setGraphic(hbox);
            break;
          case 7:
            decision1
                .setText(noCasados[0].getNombre() + " y " + noCasados[1].getNombre() + " se casan");
            decision2.setText("+1 huevo aún sin incubar");
            decision1.setGraphic(hboxBoda);
            decision2.setGraphic(imageView6);
            break;
          case 8:
            decision1
                .setText(noCasados[0].getNombre() + " y " + noCasados[1].getNombre() + " se casan");
            decision2.setText("+1 Edificio de clase " + clasesDecisiones[0]);
            decision1.setGraphic(hboxBoda);
            decision2.setGraphic(imageView);
            break;
        }
        toggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
          if (newToggle == null) {
            decidirAceptar.setVisible(false);
          } else {
            ToggleButton selectedToggleButton = (ToggleButton) newToggle;
            decidirAceptar.setVisible(true);
            decidirAceptar.setOnAction(event -> aceptarFinal(selectedToggleButton.getText(),
                newPoke1, newPoke2, noCasado1, noCasado2));
          }
        });
      }
      decision.toFront();
    }
  }

  /**
   * Añade el nuevo pokemon, actualiza el texto y lo mete en una casa vacía si disponible
   * 
   * @param pokemon
   */
  public void addCiudadano(Pokemon pokemon) {
    partida.getPokemones().add(pokemon);
    for (Building edificio : partida.getBuildings()) {
      if (edificio.getViviendo().isEmpty() && edificio.getClase().equals("Vivienda")
          && edificio.isDesplegado()) {
        pokemon.setCasa(true);
        edificio.addViviendo(pokemon);
        spawnPoke(pokemon);
        break;
      }
    }
    setPokeNumber();
  }

  /**
   * Añade lo decidido a la ciudad, sean edificios o ciudadanos
   * 
   * @param texto
   * @param new1
   * @param new2
   */
  public void aceptarFinal(String texto, Pokemon new1, Pokemon new2, Pokemon noCasado1,
      Pokemon noCasado2) {
    if (texto.contains("+1 Edificio")) {
      String[] palabras = texto.split("\\s+");
      String ultimaPalabra = palabras[palabras.length - 1];
      partida.getBuildings().add(BuildingGenerator.randomOfType(ultimaPalabra));
    } else if (texto.contains("+2 Futuros")) {
      addCiudadano(new1);
      addCiudadano(new2);
    } else if (texto.contains("+2 Edificios")) {
      partida.getBuildings().add(BuildingGenerator.randomVivienda());
      partida.getBuildings().add(BuildingGenerator.randomVivienda());
    } else if (texto.contains("+1 huevo")) {
      partida.setHuevos(partida.getHuevos() + 1);
      calcularTotalRegalos();
    } else if (texto.contains("se casan")) {
      noCasado1.setCasado(true);
      noCasado2.setCasado(true);
      for (Building edificio : partida.getBuildings()) {
        for (Pokemon viviendo : edificio.getViviendo()) {
          if (viviendo == noCasado2) {
            edificio.removeViviendo(noCasado2);
            Pokemon proximoCiudadano = null;
            for (Pokemon pokemon : partida.getPokemones()) {
              if (!pokemon.isCasa()) {
                proximoCiudadano = pokemon;
                pokemon.setCasa(true);
                edificio.addViviendo(pokemon);
                break;
              }
            }
            if (proximoCiudadano != null) {
              spawnPoke(proximoCiudadano);
              setPokeNumber();
            }
            break;
          }
        }
      }
      for (Building edificio : partida.getBuildings()) {
        for (Pokemon viviendo : edificio.getViviendo()) {
          if (viviendo == noCasado1) {
            edificio.addViviendo(noCasado2);
            break;
          }
        }
      }
      setPokeNumber();
    }
    closeDecision();
    partida.setNewDecision(true);
    partida.setPendiente(partida.getPendiente() - 1);
    notifica.setText(String.valueOf(partida.getPendiente()));
    if (partida.getPendiente() == 0) {
      notifica.setVisible(false);
      notificaCircle.setVisible(false);
    }
  }

  /**
   * Cierra el menú de decisiones
   */
  public void closeDecision() {
    partida.setNewDecision(false);
    decision1.setSelected(false);
    decision2.setSelected(false);
    decision.toBack();
  }

  /**
   * Cierra la pokedex
   */
  public void closeDex() {
    pokedex.toBack();
  }

  /**
   * Cierra la pantalla de regalos
   */
  public void closeRegalos() {
    pantallaRegalos.toBack();
  }

  /**
   * Prepara la pantalla de regalos
   * 
   * @param event
   */
  public void openRegalos() {
    regalosCombate.setText("x " + partida.getRegalosCombate());
    regalosMedicina.setText("x " + partida.getRegalosMedicina());
    regalosVentas.setText("x " + partida.getRegalosVentas());
    regalosEspectaculos.setText("x " + partida.getRegalosEspectaculos());
    regalosComida.setText("x " + partida.getRegalosComida());
    regalosServicios.setText("x " + partida.getRegalosServicios());
    cantidadHuevos.setText("x " + partida.getHuevos());
    pantallaRegalos.toFront();
    if (partida.getDinero() >= 50) {
      regaloAleatorio.setVisible(true);
    } else {
      regaloAleatorio.setVisible(false);
    }
  }

  public void openModalRegalo() {
    seleccionarCombate.setText("x " + partida.getRegalosCombate());
    if (partida.getRegalosCombate() > 0) {
      seleccionarCombate.setDisable(false);
    } else {
      seleccionarCombate.setDisable(true);
    }
    seleccionarMedicina.setText("x " + partida.getRegalosMedicina());
    if (partida.getRegalosMedicina() > 0) {
      seleccionarMedicina.setDisable(false);
    } else {
      seleccionarMedicina.setDisable(true);
    }
    seleccionarVentas.setText("x " + partida.getRegalosVentas());
    if (partida.getRegalosVentas() > 0) {
      seleccionarVentas.setDisable(false);
    } else {
      seleccionarVentas.setDisable(true);
    }
    seleccionarEspectaculos.setText("x " + partida.getRegalosEspectaculos());
    if (partida.getRegalosEspectaculos() > 0) {
      seleccionarEspectaculos.setDisable(false);
    } else {
      seleccionarEspectaculos.setDisable(true);
    }
    seleccionarComida.setText("x " + partida.getRegalosComida());
    if (partida.getRegalosComida() > 0) {
      seleccionarComida.setDisable(false);
    } else {
      seleccionarComida.setDisable(true);
    }
    seleccionarServicios.setText("x " + partida.getRegalosServicios());
    if (partida.getRegalosServicios() > 0) {
      seleccionarServicios.setDisable(false);
    } else {
      seleccionarServicios.setDisable(true);
    }
    seleccionarRegalo.toFront();
  }

  public void closeModalRegalo() {
    seleccionarRegalo.toBack();
  }

  public void addRegaloRandom() {
    String[] selectedStrings = PokeGenerator.likesAndDislikes();
    addRegalo(selectedStrings[0], true);
    partida.setDinero(partida.getDinero() - 50);
    dineroText.setText(partida.getDinero() + "k");
    if (partida.getDinero() >= 50) {
      regaloAleatorio.setVisible(true);
    } else {
      regaloAleatorio.setVisible(false);
    }
  }

  public void comprarRegaloEdificio(Building edificio) {
    addRegalo(edificio.getClase(), false);
    partida.setDinero(partida.getDinero() - precioRegalos(edificio));
    dineroText.setText(partida.getDinero() + "k");
    if (partida.getDinero() >= precioRegalos(edificio)) {
      comprarRegaloEdificio.setDisable(false);
    } else {
      comprarRegaloEdificio.setDisable(true);
    }
  }

  private void stringRegalos(Building edificio) {
    if (edificio.getNumMejoras() == 1) {
      comprarRegaloEdificio.setText("Comprar regalo (150k)");
    } else if (edificio.getNumMejoras() == 2) {
      comprarRegaloEdificio.setText("Comprar regalo (125k)");
    } else if (edificio.getNumMejoras() == maxMejoras) {
      comprarRegaloEdificio.setText("Comprar regalo (100k)");
    }
    Image imageClase =
        new Image(getClass().getResourceAsStream("/img/clases/" + edificio.getClase() + ".png"));
    ImageView imageViewClase = new ImageView(imageClase);
    imageViewClase.setFitHeight(iconSizePeque);
    imageViewClase.setPreserveRatio(true);
    comprarRegaloEdificio.setGraphic(imageViewClase);
  }

  public int precioRegalos(Building edificio) {
    if (edificio.getNumMejoras() == 1) {
      return 150;
    } else if (edificio.getNumMejoras() == 2) {
      return 125;
    } else if (edificio.getNumMejoras() == 3) {
      return 100;
    }
    return 0;
  }

  /**
   * Añade un regalo del tipo indicado al inventario
   * 
   * @param type
   */
  public void addRegalo(String type, boolean random) {
    switch (type) {
      case "Combate":
        partida.setRegalosCombate(partida.getRegalosCombate() + 1);
        break;
      case "Medicina":
        partida.setRegalosMedicina(partida.getRegalosMedicina() + 1);
        break;
      case "Ventas":
        partida.setRegalosVentas(partida.getRegalosVentas() + 1);
        break;
      case "Espectáculos":
        partida.setRegalosEspectaculos(partida.getRegalosEspectaculos() + 1);
        break;
      case "Servicios":
        partida.setRegalosServicios(partida.getRegalosServicios() + 1);
        break;
      case "Comida":
        partida.setRegalosComida(partida.getRegalosComida() + 1);
        break;
    }
    if (random) {
      openRegalos();
    }
    calcularTotalRegalos();
  }

  public void gastarRegalo(MouseEvent event, Pokemon pokeTrabajador) {
    Button botonClase = (Button) event.getSource();
    if (botonClase == seleccionarCombate) {
      partida.setRegalosCombate(partida.getRegalosCombate() - 1);
      calcularXP(pokeTrabajador, "Combate");
    } else if (botonClase == seleccionarServicios) {
      partida.setRegalosServicios(partida.getRegalosServicios() - 1);
      calcularXP(pokeTrabajador, "Servicios");
    } else if (botonClase == seleccionarMedicina) {
      partida.setRegalosMedicina(partida.getRegalosMedicina() - 1);
      calcularXP(pokeTrabajador, "Medicina");
    } else if (botonClase == seleccionarEspectaculos) {
      partida.setRegalosEspectaculos(partida.getRegalosEspectaculos() - 1);
      calcularXP(pokeTrabajador, "Espectáculos");
    } else if (botonClase == seleccionarComida) {
      partida.setRegalosComida(partida.getRegalosComida() - 1);
      calcularXP(pokeTrabajador, "Comida");
    } else if (botonClase == seleccionarVentas) {
      partida.setRegalosVentas(partida.getRegalosVentas() - 1);
      calcularXP(pokeTrabajador, "Ventas");
    }
    if (pokeTrabajador.getXp() <= 0 && pokeTrabajador.getAmistad() >= 1) {
      double negativo = pokeTrabajador.getXp();
      pokeTrabajador.setXp(negativo + 1);
      pokeTrabajador.setAmistad(pokeTrabajador.getAmistad() - 1);
    } else if (pokeTrabajador.getXp() <= 0 && pokeTrabajador.getAmistad() < 1) {
      pokeTrabajador.setXp(0);
      pokeTrabajador.setAmistad(0);
    } else if (pokeTrabajador.getXp() >= 1) {
      double negativo = pokeTrabajador.getXp();
      pokeTrabajador.setXp(negativo - 1);
      pokeTrabajador.setAmistad(pokeTrabajador.getAmistad() + 1);
    }
    if (pokeTrabajador.getAmistad() >= 15) {
      amistadRegalo.setText("Máx.");
      medidorAmistad.setProgress(1);
      amistadRegalo.setOnAction(null);
      closeModalRegalo();
    } else {
      amistadRegalo.setText(String.valueOf(pokeTrabajador.getAmistad()));
      medidorAmistad.setProgress(pokeTrabajador.getXp());
      amistadRegalo.setOnAction(eventNew -> openModalRegalo());
      openModalRegalo();
    }
    calcularTotalRegalos();
  }

  public void calcularTotalRegalos() {
    int total = partida.getRegalosCombate() + partida.getRegalosComida()
        + partida.getRegalosEspectaculos() + partida.getRegalosMedicina()
        + partida.getRegalosServicios() + partida.getRegalosVentas() + partida.getHuevos();
    regaloText.setText(String.valueOf(total));
  }

  /**
   * Prepara la Pokedex
   * 
   * @param event
   */
  public void openDex() {
    int numColumns = 7;
    int numRows = PokeGenerator.allSpecies().size() / numColumns;
    if (PokeGenerator.allSpecies().size() % numColumns != 0) {
      numRows++;
    }
    pokedexPane.setPrefHeight(numRows * 175);
    pokedexGrid.setPrefHeight(numRows * 175);
    pokedexGrid.getRowConstraints().clear();
    for (int i = 0; i < numRows; i++) {
      RowConstraints rowConstraints = new RowConstraints();
      rowConstraints.setPrefHeight(175);
      pokedexGrid.getRowConstraints().add(rowConstraints);
    }
    int i = 0;
    pokedexGrid.getChildren().clear();

    for (String pokedexMon : PokeGenerator.allSpecies()) {
      boolean pokeExists = false;
      Image image = new Image(getClass().getResourceAsStream("/img/poke/" + pokedexMon + ".png"));
      ImageView imageView = new ImageView(image);
      imageView.setFitHeight(175);
      imageView.setPreserveRatio(true);
      for (Pokemon selfMon : partida.getPokemones()) {
        if (selfMon.getImage().equals(pokedexMon)) {
          pokeExists = true;
          break;
        }
      }
      if (!pokeExists) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-1.0);
        imageView.setEffect(colorAdjust);
      }
      pokedexGrid.add(imageView, i % numColumns, i / numColumns);
      i++;
    }
    pokedex.toFront();
  }

  /**
   * Prepara el inventario añadiendo los edificios que tengas a los botones
   * 
   * @param eventOrigen
   */
  public void openInventory(ActionEvent eventOrigen) {
    Button ubicacion = (Button) eventOrigen.getSource();
    ToggleButton[] toggleButtons = new ToggleButton[partida.getBuildings().size()];
    ToggleGroup toggleGroup = new ToggleGroup();
    int numColumns = 4;
    int totalNot = 0;
    for (Building building : partida.getBuildings()) {
      if (!building.isDesplegado()) {
        totalNot++;
      }
    }
    int numRows = totalNot / numColumns;
    if (totalNot % numColumns != 0) {
      numRows++;
    }
    addEdificioPane.setPrefHeight(numRows * altoCuadricula);
    addEdificio.setPrefHeight(numRows * altoCuadricula);
    pokedexGrid.getRowConstraints().clear();
    for (int i = 0; i < numRows; i++) {
      RowConstraints rowConstraints = new RowConstraints();
      rowConstraints.setPrefHeight(altoCuadricula);
      addEdificio.getRowConstraints().add(rowConstraints);
    }
    int i = 0;
    addEdificio.getChildren().clear();

    for (Building building : partida.getBuildings()) {
      if (!building.isDesplegado()) {
        ToggleButton toggleButton = new ToggleButton("");
        toggleButton.setUserData(building);
        toggleButton.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
          if (newToggle == null) {
            construir.setVisible(false);
          } else {
            construir.setVisible(true);
            partida.setSelectedBuilding((Building) newToggle.getUserData());
          }
        });
        toggleButtons[i] = toggleButton;
        toggleButton.setPrefWidth(anchoCuadricula);
        toggleButton.setPrefHeight(altoCuadricula - 8);
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
      construir(ubicacion, partida.getSelectedBuilding());
    });

    inventory.toFront();
  }

  private void demoler(Building edificio) {
    int row = edificio.getRow();
    int col = edificio.getCol();
    if (!edificio.getViviendo().isEmpty()) {
      for (Pokemon pokemon : edificio.getViviendo()) {
        edificio.removeViviendo(pokemon);
        pokemon.setCasa(false);
        for (Node node : superiorGrid.getChildren()) {
          if (node instanceof ImageView) {
            ImageView imageViewDeshaucio = (ImageView) node;
            if (pokemon.equals(imageViewDeshaucio.getUserData())) {
              superiorGrid.getChildren().remove(imageViewDeshaucio);
              break;
            }
          }
        }
      }
    }
    if (edificio.getTrabajador() != null) {
      Pokemon pokemon = edificio.getTrabajador();
      edificio.setTrabajador(null);
      pokemon.setTrabaja(false);
    }
    if (edificio.getTrabajador2() != null) {
      Pokemon pokemon = edificio.getTrabajador2();
      edificio.setTrabajador2(null);
      pokemon.setTrabaja(false);
    }
    if (edificio.getTrabajador3() != null) {
      Pokemon pokemon = edificio.getTrabajador3();
      edificio.setTrabajador3(null);
      pokemon.setTrabaja(false);
    }
    if (edificio.getTrabajador4() != null) {
      Pokemon pokemon = edificio.getTrabajador4();
      edificio.setTrabajador4(null);
      pokemon.setTrabaja(false);
    }
    partida.getBuildings().remove(edificio);
    borrarBotonEdificio(row, col);
    Button button = new Button("");
    button.setPrefWidth(anchoCuadricula);
    button.setPrefHeight(altoCuadricula - 2);
    button.setOnAction(event -> openInventory(event));
    GridPane.setHalignment(button, HPos.CENTER);
    Image image = new Image(getClass().getResourceAsStream("/img/button/sale.png"));
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(iconSize);
    imageView.setPreserveRatio(true);
    button.setOnMouseEntered(event -> button.setGraphic(imageView));
    button.setOnMouseExited(event -> button.setGraphic(null));
    button.getProperties().put("row", row);
    button.getProperties().put("col", col);
    cuadricula.add(button, col, row);
    closeEdificio();
    setPokeNumber();
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
    imageView.setFitHeight(altoCuadricula - 8);
    imageView.setPreserveRatio(true);
    ubicacion.setGraphic(imageView);
    ubicacion.setOnAction(event -> infoEdificio(event));
    ubicacion.setOnMouseEntered(null);
    ubicacion.setOnMouseExited(null);
    edificio.setDesplegado(true);
    edificio.setRow((int) ubicacion.getProperties().get("row"));
    edificio.setCol((int) ubicacion.getProperties().get("col"));
    ubicacion.setUserData(edificio);
    if (edificio.getClase().equals("Vivienda")) {
      Pokemon proximoCiudadano = null;
      for (Pokemon pokemon : partida.getPokemones()) {
        if (!pokemon.isCasa()) {
          proximoCiudadano = pokemon;
          pokemon.setCasa(true);
          edificio.addViviendo(pokemon);
          break;
        }
      }
      if (proximoCiudadano != null) {
        spawnPoke(proximoCiudadano);
        setPokeNumber();
      }
    }
    closeInventory();
  }

  /**
   * Rellena la cuadricula con los edificios donde los construiste
   * 
   * @param ubicacion
   * @param edificio
   */
  public void construirTrasGuardado(Button ubicacion, Building edificio) {
    Image image = new Image(getClass().getResourceAsStream("/img/" + edificio.getImage() + ".png"));
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(altoCuadricula - 8);
    imageView.setPreserveRatio(true);
    ubicacion.setGraphic(imageView);
    ubicacion.setOnAction(event -> infoEdificio(event));
    ubicacion.setOnMouseEntered(null);
    ubicacion.setOnMouseExited(null);
    edificio.setDesplegado(true);
    edificio.setRow((int) ubicacion.getProperties().get("row"));
    edificio.setCol((int) ubicacion.getProperties().get("col"));
    ubicacion.setUserData(edificio);
    closeInventory();
  }

  /**
   * Cierra el menú de info de edificio
   */
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
    partida.setSelectedBuilding(edificio);
    Image image = new Image(getClass().getResourceAsStream("/img/" + edificio.getImage() + ".png"));
    imagenEdificio.setFitHeight(altoCuadricula);
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
    if (edificio.getClase().equals("Vivienda")) {
      dislikePoke.setVisible(true);
      likePoke.setVisible(true);
      likeIcon.setVisible(true);
      dislikeIcon.setVisible(true);
      cambiarPokeDer.setVisible(false);
      cambiarPokeDer2.setVisible(false);
      cambiarPokeDer3.setVisible(false);
      cambiarPokeDer4.setVisible(false);
      cambiarPokeIzq.setVisible(false);
      cambiarPokeIzq2.setVisible(false);
      cambiarPokeIzq3.setVisible(false);
      cambiarPokeIzq4.setVisible(false);
      pokeTrabajador.setVisible(false);
      mejorarEdificio.setVisible(false);
      comprarRegaloEdificio.setVisible(false);
      trabajador.setVisible(false);
      pokeTrabajador2.setVisible(false);
      trabajador2.setVisible(false);
      pokeTrabajador3.setVisible(false);
      trabajador3.setVisible(false);
      pokeTrabajador4.setVisible(false);
      trabajador4.setVisible(false);
      if (!edificio.getViviendo().isEmpty()) {
        boxPoke.getChildren().clear();
        for (Pokemon viviendo : edificio.getViviendo()) {
          Image imageViviendo = new Image(
              getClass().getResourceAsStream("/img/poke/" + viviendo.getImage() + ".png"));
          ImageView imageViewViviendo = new ImageView(imageViviendo);
          imageViewViviendo.setFitHeight(175);
          imageViewViviendo.setPreserveRatio(true);
          Button button = new Button();
          button.setGraphic(imageViewViviendo);
          button.setMinSize(200, 200);
          button.setMaxSize(200, 200);
          button.setOnAction(event -> infoVivienda(edificio, viviendo));
          boxPoke.getChildren().add(button);
        }
        if (edificio.getIncubando().size() >= 1) {
          for (int i = 0; i < edificio.getIncubando().size(); i++) {
            Image imageHuevo = new Image(getClass().getResourceAsStream("/img/button/huevo.png"));
            ImageView imageViewHuevo = new ImageView(imageHuevo);
            imageViewHuevo.setFitHeight(iconSize);
            imageViewHuevo.setPreserveRatio(true);
            Button button = new Button("???", imageViewHuevo);
            button.setMinSize(200, 200);
            button.setMaxSize(200, 200);
            button.setContentDisplay(ContentDisplay.TOP);
            Font font = Font.font("System", FontWeight.BOLD, 34);
            button.setFont(font);
            button.setTextFill(Color.WHITE);
            button.setOnAction(null);
            boxPoke.getChildren().add(button);
          }
        }
        if (edificio.getViviendo().size() > 1 && edificio.getViviendo().size() < 6
            && partida.getHuevos() >= 1 && edificio.getIncubando().size() < 4) {
          Image imageCesta = new Image(getClass().getResourceAsStream("/img/button/cesta.png"));
          ImageView imageViewCesta = new ImageView(imageCesta);
          imageViewCesta.setFitHeight(iconSize);
          imageViewCesta.setPreserveRatio(true);
          Button button = new Button("Dar", imageViewCesta);
          button.setMinSize(200, 200);
          button.setMaxSize(200, 200);
          button.setContentDisplay(ContentDisplay.TOP);
          Font font = Font.font("System", FontWeight.BOLD, 34);
          button.setFont(font);
          button.setTextFill(Color.WHITE);
          button.setOnAction(event -> addHuevo(edificio));
          boxPoke.getChildren().add(button);
        }
        edificio.setSelectedViviendo(edificio.getViviendo().get(0));
        Image imageDado = new Image(getClass().getResourceAsStream("/img/button/dado.png"));
        ImageView imageViewDado = new ImageView(imageDado);
        imageViewDado.setFitHeight(iconSizePeque);
        imageViewDado.setPreserveRatio(true);
        nombreViviendo.setGraphic(imageViewDado);
        medidorAmistad.setVisible(true);
        amistadRegalo.setVisible(true);
        seleccionarCombate
            .setOnMouseClicked(event -> gastarRegalo(event, edificio.getSelectedViviendo()));
        seleccionarComida
            .setOnMouseClicked(event -> gastarRegalo(event, edificio.getSelectedViviendo()));
        seleccionarEspectaculos
            .setOnMouseClicked(event -> gastarRegalo(event, edificio.getSelectedViviendo()));
        seleccionarVentas
            .setOnMouseClicked(event -> gastarRegalo(event, edificio.getSelectedViviendo()));
        seleccionarMedicina
            .setOnMouseClicked(event -> gastarRegalo(event, edificio.getSelectedViviendo()));
        seleccionarServicios
            .setOnMouseClicked(event -> gastarRegalo(event, edificio.getSelectedViviendo()));
        if (edificio.getSelectedViviendo().getAmistad() >= 15) {
          amistadRegalo.setText("Máx.");
          medidorAmistad.setProgress(1);
          amistadRegalo.setOnAction(null);
        } else {
          amistadRegalo.setText(String.valueOf(edificio.getSelectedViviendo().getAmistad()));
          medidorAmistad.setProgress(edificio.getSelectedViviendo().getXp());
          amistadRegalo.setOnAction(eventNew -> openModalRegalo());
        }
        Image imageViviendo = new Image(getClass().getResourceAsStream(
            "/img/poke/" + edificio.getSelectedViviendo().getImage() + ".png"));
        pokeViviendo.setImage(imageViviendo);
        nombreViviendo.setText(fullName(edificio.getSelectedViviendo()));
        nombreViviendo.setOnMouseClicked(event -> rerollName(edificio.getSelectedViviendo()));
        Image imageLike = new Image(getClass().getResourceAsStream(
            "/img/clases/" + edificio.getSelectedViviendo().getLike() + ".png"));
        ImageView imageViewLike = new ImageView(imageLike);
        imageViewLike.setFitHeight(iconSizePeque);
        imageViewLike.setPreserveRatio(true);
        likePoke.setGraphic(imageViewLike);
        likePoke.setText(edificio.getSelectedViviendo().getLike());
        Image imageDislike = new Image(getClass().getResourceAsStream(
            "/img/clases/" + edificio.getSelectedViviendo().getDislike() + ".png"));
        ImageView imageViewDislike = new ImageView(imageDislike);
        imageViewDislike.setFitHeight(iconSizePeque);
        imageViewDislike.setPreserveRatio(true);
        dislikePoke.setGraphic(imageViewDislike);
        dislikePoke.setText(edificio.getSelectedViviendo().getDislike());
        descTrabajo.setVisible(true);
        descTrabajo.setText(getPokeWork(edificio.getSelectedViviendo()));
        if (edificio.getViviendo().size() == 1) {
          produccionEdificio.setText(edificio.getViviendo().size() + " Pokémon vive aquí");
          boxPoke.setVisible(false);
        } else {
          produccionEdificio.setText(edificio.getViviendo().size() + " Pokemones viven aquí");
          boxPoke.setVisible(true);
        }
        if (edificio.getSelectedViviendo().isTrabaja()) {
          verTrabajo.setVisible(true);
          verTrabajo.setOnAction(
              event -> infoEdificioTrue(getPokeBuilding(edificio.getSelectedViviendo())));
        } else {
          verTrabajo.setVisible(false);
        }
      } else {
        medidorAmistad.setVisible(false);
        amistadRegalo.setVisible(false);
        verTrabajo.setVisible(false);
        nombreViviendo.setGraphic(null);
        boxPoke.setVisible(false);
        produccionEdificio.setText("Ningún Pokémon vive aquí");
        Image imageViviendo = new Image(getClass().getResourceAsStream("/img/button/no.png"));
        pokeViviendo.setImage(imageViviendo);
        nombreViviendo.setText("Vacío");
        nombreViviendo.setOnMouseClicked(null);
        dislikePoke.setVisible(false);
        likePoke.setVisible(false);
        likeIcon.setVisible(false);
        dislikeIcon.setVisible(false);
        descTrabajo.setVisible(false);
      }
      pokeViviendo.setVisible(true);
      nombreViviendo.setVisible(true);
    } else {
      boxPoke.setVisible(false);
      calcularProduccion(edificio);
      trabajador.setVisible(true);
      pokeTrabajador.setVisible(true);
      stringMejoras(edificio);
      if ((edificio.getNumMejoras() == 0 && partida.getDinero() >= 50)
          || (edificio.getNumMejoras() == 1 && partida.getDinero() >= 100)
          || (edificio.getNumMejoras() == 2 && partida.getDinero() >= 200)) {
        mejorarEdificio.setOnAction(event -> mejorarEdificio(edificio));
        mejorarEdificio.setDisable(false);
      } else {
        mejorarEdificio.setDisable(true);
      }
      if (partida.getSelectedBuilding().getNumMejoras() > 0
          && partida.getDinero() >= precioRegalos(partida.getSelectedBuilding())) {
        comprarRegaloEdificio.setVisible(true);
        comprarRegaloEdificio.setDisable(false);
        comprarRegaloEdificio
            .setOnAction(event -> comprarRegaloEdificio(partida.getSelectedBuilding()));
      } else if (partida.getSelectedBuilding().getNumMejoras() > 0
          && partida.getDinero() < precioRegalos(partida.getSelectedBuilding())) {
        comprarRegaloEdificio.setVisible(true);
        comprarRegaloEdificio.setDisable(true);
      } else {
        comprarRegaloEdificio.setVisible(false);
      }
      stringRegalos(partida.getSelectedBuilding());
      mejorarEdificio.setVisible(true);
      medidorAmistad.setVisible(false);
      amistadRegalo.setVisible(false);
      descTrabajo.setVisible(false);
      dislikePoke.setVisible(false);
      likePoke.setVisible(false);
      likeIcon.setVisible(false);
      dislikeIcon.setVisible(false);
      trabajador.setVisible(true);
      cambiarPokeDer.setVisible(true);
      cambiarPokeIzq.setVisible(true);
      pokeViviendo.setVisible(false);
      verTrabajo.setVisible(false);
      if (edificio.getNumMejoras() >= 1) {
        cambiarPokeDer2.setVisible(true);
        cambiarPokeIzq2.setVisible(true);
        trabajador2.setVisible(true);
        pokeTrabajador2.setVisible(true);
      } else {
        cambiarPokeDer2.setVisible(false);
        cambiarPokeIzq2.setVisible(false);
        trabajador2.setVisible(false);
        pokeTrabajador2.setVisible(false);
      }
      if (edificio.getNumMejoras() >= 2) {
        cambiarPokeDer3.setVisible(true);
        cambiarPokeIzq3.setVisible(true);
        trabajador3.setVisible(true);
        pokeTrabajador3.setVisible(true);
      } else {
        cambiarPokeDer3.setVisible(false);
        cambiarPokeIzq3.setVisible(false);
        trabajador3.setVisible(false);
        pokeTrabajador3.setVisible(false);
      }
      if (edificio.getNumMejoras() >= 3) {
        cambiarPokeDer4.setVisible(true);
        cambiarPokeIzq4.setVisible(true);
        trabajador4.setVisible(true);
        pokeTrabajador4.setVisible(true);
      } else {
        cambiarPokeDer4.setVisible(false);
        cambiarPokeIzq4.setVisible(false);
        trabajador4.setVisible(false);
        pokeTrabajador4.setVisible(false);
      }
      nombreViviendo.setVisible(false);
      if (edificio.getTrabajador() == null) {
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador.setFitHeight(iconSize);
        pokeTrabajador.setPreserveRatio(true);
        pokeTrabajador.setImage(imageEmpty);
        trabajador.setText("Sin trabajador");
        trabajador.setGraphic(null);
        pokeTrabajador.setOnMouseClicked(null);
      } else {
        pokeTrabajador.setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador()));
        Image imagePoke = new Image(getClass()
            .getResourceAsStream("/img/poke/" + edificio.getTrabajador().getImage() + ".png"));
        pokeTrabajador.setFitHeight(iconSize);
        pokeTrabajador.setPreserveRatio(true);
        pokeTrabajador.setImage(imagePoke);
        trabajador.setText(edificio.getTrabajador().getNombre());
        Image imageEstado = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador(), edificio) + ".png"));
        ImageView imageView = new ImageView(imageEstado);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador.setGraphic(imageView);
      }
      if (edificio.getTrabajador2() == null) {
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador2.setFitHeight(iconSize);
        pokeTrabajador2.setPreserveRatio(true);
        pokeTrabajador2.setImage(imageEmpty);
        trabajador2.setText("Sin trabajador");
        trabajador2.setGraphic(null);
        pokeTrabajador2.setOnMouseClicked(null);
      } else {
        pokeTrabajador2
            .setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador2()));
        Image imagePoke = new Image(getClass()
            .getResourceAsStream("/img/poke/" + edificio.getTrabajador2().getImage() + ".png"));
        pokeTrabajador2.setFitHeight(iconSize);
        pokeTrabajador2.setPreserveRatio(true);
        pokeTrabajador2.setImage(imagePoke);
        trabajador2.setText(edificio.getTrabajador2().getNombre());
        Image imageEstado = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador2(), edificio) + ".png"));
        ImageView imageView = new ImageView(imageEstado);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador2.setGraphic(imageView);
      }
      if (edificio.getTrabajador3() == null) {
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador3.setFitHeight(iconSize);
        pokeTrabajador3.setPreserveRatio(true);
        pokeTrabajador3.setImage(imageEmpty);
        trabajador3.setText("Sin trabajador");
        trabajador3.setGraphic(null);
        pokeTrabajador3.setOnMouseClicked(null);
      } else {
        pokeTrabajador3
            .setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador3()));
        Image imagePoke = new Image(getClass()
            .getResourceAsStream("/img/poke/" + edificio.getTrabajador3().getImage() + ".png"));
        pokeTrabajador3.setFitHeight(iconSize);
        pokeTrabajador3.setPreserveRatio(true);
        pokeTrabajador3.setImage(imagePoke);
        trabajador3.setText(edificio.getTrabajador3().getNombre());
        Image imageEstado = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador3(), edificio) + ".png"));
        ImageView imageView = new ImageView(imageEstado);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador3.setGraphic(imageView);
      }
      if (edificio.getTrabajador4() == null) {
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador4.setFitHeight(iconSize);
        pokeTrabajador4.setPreserveRatio(true);
        pokeTrabajador4.setImage(imageEmpty);
        trabajador4.setText("Sin trabajador");
        trabajador4.setGraphic(null);
        pokeTrabajador4.setOnMouseClicked(null);
      } else {
        pokeTrabajador4
            .setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador4()));
        Image imagePoke = new Image(getClass()
            .getResourceAsStream("/img/poke/" + edificio.getTrabajador4().getImage() + ".png"));
        pokeTrabajador4.setFitHeight(iconSize);
        pokeTrabajador4.setPreserveRatio(true);
        pokeTrabajador4.setImage(imagePoke);
        trabajador4.setText(edificio.getTrabajador4().getNombre());
        Image imageEstado = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador4(), edificio) + ".png"));
        ImageView imageView = new ImageView(imageEstado);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador4.setGraphic(imageView);
      }
      cambiarPokeDer.setOnAction(event -> cambiarTrabajador(event, edificio));
      cambiarPokeIzq.setOnAction(event -> retrocederTrabajador(event, edificio));
      cambiarPokeDer2.setOnAction(event -> cambiarTrabajador2(event, edificio));
      cambiarPokeIzq2.setOnAction(event -> retrocederTrabajador2(event, edificio));
      cambiarPokeDer3.setOnAction(event -> cambiarTrabajador3(event, edificio));
      cambiarPokeIzq3.setOnAction(event -> retrocederTrabajador3(event, edificio));
      cambiarPokeDer4.setOnAction(event -> cambiarTrabajador4(event, edificio));
      cambiarPokeIzq4.setOnAction(event -> retrocederTrabajador4(event, edificio));
    }
    verEdificio.toFront();
  }

  /**
   * Muestra el menú de información del edificio seleccionado (empleado)
   * 
   * @param eventOrigen
   */
  public void infoEdificioTrue(Building edificio) {
    partida.setSelectedBuilding(edificio);
    Image image = new Image(getClass().getResourceAsStream("/img/" + edificio.getImage() + ".png"));
    imagenEdificio.setFitHeight(altoCuadricula);
    imagenEdificio.setPreserveRatio(true);
    nombreEdificio.setText(edificio.getImage());
    imagenEdificio.setImage(image);
    claseEdificio.setText(edificio.getClase());
    boxPoke.setVisible(false);
    Image imageClase =
        new Image(getClass().getResourceAsStream("/img/clases/" + edificio.getClase() + ".png"));
    ImageView imageViewClase = new ImageView(imageClase);
    imageViewClase.setFitHeight(iconSizePeque);
    imageViewClase.setPreserveRatio(true);
    claseEdificio.setGraphic(imageViewClase);
    descTrabajo.setVisible(false);
    dislikePoke.setVisible(false);
    likePoke.setVisible(false);
    likeIcon.setVisible(false);
    dislikeIcon.setVisible(false);
    trabajador.setVisible(true);
    cambiarPokeDer.setVisible(true);
    cambiarPokeIzq.setVisible(true);
    pokeViviendo.setVisible(false);
    verTrabajo.setVisible(false);
    nombreViviendo.setVisible(false);
    medidorAmistad.setVisible(false);
    amistadRegalo.setVisible(false);
    pokeTrabajador.setVisible(true);
    stringMejoras(edificio);
    mejorarEdificio.setVisible(true);
    if ((edificio.getNumMejoras() == 0 && partida.getDinero() >= 50)
        || (edificio.getNumMejoras() == 1 && partida.getDinero() >= 100)
        || (edificio.getNumMejoras() == 2 && partida.getDinero() >= 200)) {
      mejorarEdificio.setOnAction(event -> mejorarEdificio(edificio));
      mejorarEdificio.setDisable(false);
    } else {
      mejorarEdificio.setDisable(true);
    }
    if (partida.getSelectedBuilding().getNumMejoras() > 0
        && partida.getDinero() >= precioRegalos(partida.getSelectedBuilding())) {
      comprarRegaloEdificio.setVisible(true);
      comprarRegaloEdificio.setDisable(false);
      comprarRegaloEdificio
          .setOnAction(event -> comprarRegaloEdificio(partida.getSelectedBuilding()));
    } else if (partida.getSelectedBuilding().getNumMejoras() > 0
        && partida.getDinero() < precioRegalos(partida.getSelectedBuilding())) {
      comprarRegaloEdificio.setVisible(true);
      comprarRegaloEdificio.setDisable(true);
    } else {
      comprarRegaloEdificio.setVisible(false);
    }
    stringRegalos(partida.getSelectedBuilding());
    if (edificio.getNumMejoras() >= 1) {
      cambiarPokeDer2.setVisible(true);
      cambiarPokeIzq2.setVisible(true);
      trabajador2.setVisible(true);
      pokeTrabajador2.setVisible(true);
    } else {
      cambiarPokeDer2.setVisible(false);
      cambiarPokeIzq2.setVisible(false);
      trabajador2.setVisible(false);
      pokeTrabajador2.setVisible(false);
    }
    if (edificio.getNumMejoras() >= 2) {
      cambiarPokeDer3.setVisible(true);
      cambiarPokeIzq3.setVisible(true);
      trabajador3.setVisible(true);
      pokeTrabajador3.setVisible(true);
    } else {
      cambiarPokeDer3.setVisible(false);
      cambiarPokeIzq3.setVisible(false);
      trabajador3.setVisible(false);
      pokeTrabajador3.setVisible(false);
    }
    if (edificio.getNumMejoras() >= 3) {
      cambiarPokeDer4.setVisible(true);
      cambiarPokeIzq4.setVisible(true);
      trabajador4.setVisible(true);
      pokeTrabajador4.setVisible(true);
    } else {
      cambiarPokeDer4.setVisible(false);
      cambiarPokeIzq4.setVisible(false);
      trabajador4.setVisible(false);
      pokeTrabajador4.setVisible(false);
    }
    if (edificio.getTrabajador() == null) {
      Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
      pokeTrabajador.setFitHeight(iconSize);
      pokeTrabajador.setPreserveRatio(true);
      pokeTrabajador.setImage(imageEmpty);
      trabajador.setText("Sin trabajador");
      trabajador.setGraphic(null);
      pokeTrabajador.setOnMouseClicked(null);
    } else {
      pokeTrabajador.setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador()));
      Image imagePoke = new Image(getClass()
          .getResourceAsStream("/img/poke/" + edificio.getTrabajador().getImage() + ".png"));
      pokeTrabajador.setFitHeight(iconSize);
      pokeTrabajador.setPreserveRatio(true);
      pokeTrabajador.setImage(imagePoke);
      trabajador.setText(edificio.getTrabajador().getNombre());
      Image imageEstado = new Image(getClass().getResourceAsStream(
          "/img/button/" + calcularSatisfaccion(edificio.getTrabajador(), edificio) + ".png"));
      ImageView imageView = new ImageView(imageEstado);
      imageView.setFitHeight(iconSizePeque);
      imageView.setPreserveRatio(true);
      trabajador.setGraphic(imageView);
    }
    if (edificio.getTrabajador2() == null) {
      Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
      pokeTrabajador2.setFitHeight(iconSize);
      pokeTrabajador2.setPreserveRatio(true);
      pokeTrabajador2.setImage(imageEmpty);
      trabajador2.setText("Sin trabajador");
      trabajador2.setGraphic(null);
      pokeTrabajador2.setOnMouseClicked(null);
    } else {
      pokeTrabajador2.setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador2()));
      Image imagePoke = new Image(getClass()
          .getResourceAsStream("/img/poke/" + edificio.getTrabajador2().getImage() + ".png"));
      pokeTrabajador2.setFitHeight(iconSize);
      pokeTrabajador2.setPreserveRatio(true);
      pokeTrabajador2.setImage(imagePoke);
      trabajador2.setText(edificio.getTrabajador2().getNombre());
      Image imageEstado = new Image(getClass().getResourceAsStream(
          "/img/button/" + calcularSatisfaccion(edificio.getTrabajador2(), edificio) + ".png"));
      ImageView imageView = new ImageView(imageEstado);
      imageView.setFitHeight(iconSizePeque);
      imageView.setPreserveRatio(true);
      trabajador2.setGraphic(imageView);
    }
    if (edificio.getTrabajador3() == null) {
      Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
      pokeTrabajador3.setFitHeight(iconSize);
      pokeTrabajador3.setPreserveRatio(true);
      pokeTrabajador3.setImage(imageEmpty);
      trabajador3.setText("Sin trabajador");
      trabajador3.setGraphic(null);
      pokeTrabajador3.setOnMouseClicked(null);
    } else {
      pokeTrabajador3.setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador3()));
      Image imagePoke = new Image(getClass()
          .getResourceAsStream("/img/poke/" + edificio.getTrabajador3().getImage() + ".png"));
      pokeTrabajador3.setFitHeight(iconSize);
      pokeTrabajador3.setPreserveRatio(true);
      pokeTrabajador3.setImage(imagePoke);
      trabajador3.setText(edificio.getTrabajador3().getNombre());
      Image imageEstado = new Image(getClass().getResourceAsStream(
          "/img/button/" + calcularSatisfaccion(edificio.getTrabajador3(), edificio) + ".png"));
      ImageView imageView = new ImageView(imageEstado);
      imageView.setFitHeight(iconSizePeque);
      imageView.setPreserveRatio(true);
      trabajador3.setGraphic(imageView);
    }
    if (edificio.getTrabajador4() == null) {
      Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
      pokeTrabajador4.setFitHeight(iconSize);
      pokeTrabajador4.setPreserveRatio(true);
      pokeTrabajador4.setImage(imageEmpty);
      trabajador4.setText("Sin trabajador");
      trabajador4.setGraphic(null);
      pokeTrabajador4.setOnMouseClicked(null);
    } else {
      pokeTrabajador4.setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador4()));
      Image imagePoke = new Image(getClass()
          .getResourceAsStream("/img/poke/" + edificio.getTrabajador4().getImage() + ".png"));
      pokeTrabajador4.setFitHeight(iconSize);
      pokeTrabajador4.setPreserveRatio(true);
      pokeTrabajador4.setImage(imagePoke);
      trabajador4.setText(edificio.getTrabajador4().getNombre());
      Image imageEstado = new Image(getClass().getResourceAsStream(
          "/img/button/" + calcularSatisfaccion(edificio.getTrabajador4(), edificio) + ".png"));
      ImageView imageView = new ImageView(imageEstado);
      imageView.setFitHeight(iconSizePeque);
      imageView.setPreserveRatio(true);
      trabajador4.setGraphic(imageView);
    }
    cambiarPokeDer.setOnAction(event -> cambiarTrabajador(event, edificio));
    cambiarPokeIzq.setOnAction(event -> retrocederTrabajador(event, edificio));
    cambiarPokeDer2.setOnAction(event -> cambiarTrabajador2(event, edificio));
    cambiarPokeIzq2.setOnAction(event -> retrocederTrabajador2(event, edificio));
    cambiarPokeDer3.setOnAction(event -> cambiarTrabajador3(event, edificio));
    cambiarPokeIzq3.setOnAction(event -> retrocederTrabajador3(event, edificio));
    cambiarPokeDer4.setOnAction(event -> cambiarTrabajador4(event, edificio));
    cambiarPokeIzq4.setOnAction(event -> retrocederTrabajador4(event, edificio));
    calcularProduccion(edificio);
    verEdificio.toFront();
  }

  /**
   * Muestra la información de un edificio pero usando un Pokemon de base
   * 
   * @param eventOrigen
   */
  public void infoVivienda(Building edificio, Pokemon pokemon) {
    Image image = new Image(getClass().getResourceAsStream("/img/" + edificio.getImage() + ".png"));
    imagenEdificio.setFitHeight(altoCuadricula);
    imagenEdificio.setPreserveRatio(true);
    nombreEdificio.setText(edificio.getImage());
    imagenEdificio.setImage(image);
    claseEdificio.setText(edificio.getClase());
    partida.setSelectedBuilding(edificio);
    edificio
        .setSelectedViviendo(edificio.getViviendo().get(edificio.getViviendo().indexOf(pokemon)));
    Image imageClase =
        new Image(getClass().getResourceAsStream("/img/clases/" + edificio.getClase() + ".png"));
    ImageView imageViewClase = new ImageView(imageClase);
    imageViewClase.setFitHeight(iconSizePeque);
    imageViewClase.setPreserveRatio(true);
    claseEdificio.setGraphic(imageViewClase);
    cambiarPokeDer.setVisible(false);
    cambiarPokeIzq.setVisible(false);
    pokeTrabajador.setVisible(false);
    mejorarEdificio.setVisible(false);
    comprarRegaloEdificio.setVisible(false);
    pokeTrabajador2.setVisible(false);
    trabajador.setVisible(false);
    cambiarPokeDer2.setVisible(false);
    cambiarPokeIzq2.setVisible(false);
    pokeTrabajador2.setVisible(false);
    trabajador2.setVisible(false);
    cambiarPokeDer3.setVisible(false);
    cambiarPokeIzq3.setVisible(false);
    pokeTrabajador3.setVisible(false);
    trabajador3.setVisible(false);
    cambiarPokeDer4.setVisible(false);
    cambiarPokeIzq4.setVisible(false);
    pokeTrabajador4.setVisible(false);
    trabajador4.setVisible(false);
    if (edificio.getViviendo().size() == 1) {
      produccionEdificio.setText(edificio.getViviendo().size() + " Pokémon vive aquí");
      boxPoke.setVisible(false);
    } else {
      produccionEdificio.setText(edificio.getViviendo().size() + " Pokemones viven aquí");
      boxPoke.setVisible(true);
    }
    medidorAmistad.setVisible(true);
    amistadRegalo.setVisible(true);
    boxPoke.getChildren().clear();
    for (Pokemon viviendo : edificio.getViviendo()) {
      Image imageViviendo =
          new Image(getClass().getResourceAsStream("/img/poke/" + viviendo.getImage() + ".png"));
      ImageView imageViewViviendo = new ImageView(imageViviendo);
      imageViewViviendo.setFitHeight(175);
      imageViewViviendo.setPreserveRatio(true);
      Button button = new Button();
      button.setGraphic(imageViewViviendo);
      button.setMinSize(200, 200);
      button.setMaxSize(200, 200);
      boxPoke.getChildren().add(button);
      button.setOnAction(event -> infoVivienda(edificio, viviendo));
    }
    if (edificio.getIncubando().size() >= 1) {
      for (int i = 0; i < edificio.getIncubando().size(); i++) {
        Image imageHuevo = new Image(getClass().getResourceAsStream("/img/button/huevo.png"));
        ImageView imageViewHuevo = new ImageView(imageHuevo);
        imageViewHuevo.setFitHeight(iconSize);
        imageViewHuevo.setPreserveRatio(true);
        Button button = new Button("???", imageViewHuevo);
        button.setMinSize(200, 200);
        button.setMaxSize(200, 200);
        button.setContentDisplay(ContentDisplay.TOP);
        Font font = Font.font("System", FontWeight.BOLD, 34);
        button.setFont(font);
        button.setTextFill(Color.WHITE);
        button.setOnAction(null);
        boxPoke.getChildren().add(button);
      }
    }
    if (edificio.getViviendo().size() > 1 && edificio.getViviendo().size() < 6
        && partida.getHuevos() >= 1 && edificio.getIncubando().size() < 4) {
      Image imageCesta = new Image(getClass().getResourceAsStream("/img/button/cesta.png"));
      ImageView imageViewCesta = new ImageView(imageCesta);
      imageViewCesta.setFitHeight(iconSize);
      imageViewCesta.setPreserveRatio(true);
      Button button = new Button("Dar", imageViewCesta);
      button.setMinSize(200, 200);
      button.setMaxSize(200, 200);
      button.setContentDisplay(ContentDisplay.TOP);
      Font font = Font.font("System", FontWeight.BOLD, 34);
      button.setFont(font);
      button.setTextFill(Color.WHITE);
      button.setOnAction(event -> addHuevo(edificio));
      boxPoke.getChildren().add(button);
    }
    if (edificio.getSelectedViviendo().getAmistad() >= 15) {
      amistadRegalo.setText("Máx.");
      medidorAmistad.setProgress(1);
      amistadRegalo.setOnAction(null);
    } else {
      amistadRegalo.setText(String.valueOf(edificio.getSelectedViviendo().getAmistad()));
      medidorAmistad.setProgress(edificio.getSelectedViviendo().getXp());
      amistadRegalo.setOnAction(eventNew -> openModalRegalo());
    }
    seleccionarCombate
        .setOnMouseClicked(event -> gastarRegalo(event, edificio.getSelectedViviendo()));
    seleccionarComida
        .setOnMouseClicked(event -> gastarRegalo(event, edificio.getSelectedViviendo()));
    seleccionarEspectaculos
        .setOnMouseClicked(event -> gastarRegalo(event, edificio.getSelectedViviendo()));
    seleccionarVentas
        .setOnMouseClicked(event -> gastarRegalo(event, edificio.getSelectedViviendo()));
    seleccionarMedicina
        .setOnMouseClicked(event -> gastarRegalo(event, edificio.getSelectedViviendo()));
    seleccionarServicios
        .setOnMouseClicked(event -> gastarRegalo(event, edificio.getSelectedViviendo()));
    Image imageViviendo = new Image(getClass()
        .getResourceAsStream("/img/poke/" + edificio.getSelectedViviendo().getImage() + ".png"));
    pokeViviendo.setImage(imageViviendo);
    nombreViviendo.setText(fullName(edificio.getSelectedViviendo()));
    nombreViviendo.setOnMouseClicked(event -> rerollName(edificio.getSelectedViviendo()));
    pokeViviendo.setVisible(true);
    Image imageDado = new Image(getClass().getResourceAsStream("/img/button/dado.png"));
    ImageView imageViewDado = new ImageView(imageDado);
    imageViewDado.setFitHeight(iconSizePeque);
    imageViewDado.setPreserveRatio(true);
    nombreViviendo.setGraphic(imageViewDado);
    if (edificio.getSelectedViviendo().isTrabaja()) {
      verTrabajo.setVisible(true);
      verTrabajo
          .setOnAction(event -> infoEdificioTrue(getPokeBuilding(edificio.getSelectedViviendo())));
    } else {
      verTrabajo.setVisible(false);
    }
    nombreViviendo.setVisible(true);
    Image imageLike = new Image(getClass()
        .getResourceAsStream("/img/clases/" + edificio.getSelectedViviendo().getLike() + ".png"));
    ImageView imageViewLike = new ImageView(imageLike);
    imageViewLike.setFitHeight(iconSizePeque);
    imageViewLike.setPreserveRatio(true);
    likePoke.setGraphic(imageViewLike);
    likePoke.setText(edificio.getSelectedViviendo().getLike());
    Image imageDislike = new Image(getClass().getResourceAsStream(
        "/img/clases/" + edificio.getSelectedViviendo().getDislike() + ".png"));
    ImageView imageViewDislike = new ImageView(imageDislike);
    imageViewDislike.setFitHeight(iconSizePeque);
    imageViewDislike.setPreserveRatio(true);
    dislikePoke.setGraphic(imageViewDislike);
    dislikePoke.setText(edificio.getSelectedViviendo().getDislike());
    descTrabajo.setVisible(true);
    descTrabajo.setText(getPokeWork(edificio.getSelectedViviendo()));
    verEdificio.toFront();
  }

  private void addHuevo(Building edificio) {
    partida.setHuevos(partida.getHuevos() - 1);
    calcularTotalRegalos();
    boxPoke.getChildren().clear();
    for (Pokemon viviendo : edificio.getViviendo()) {
      Image imageViviendo =
          new Image(getClass().getResourceAsStream("/img/poke/" + viviendo.getImage() + ".png"));
      ImageView imageViewViviendo = new ImageView(imageViviendo);
      imageViewViviendo.setFitHeight(175);
      imageViewViviendo.setPreserveRatio(true);
      Button button = new Button();
      button.setGraphic(imageViewViviendo);
      button.setMinSize(200, 200);
      button.setMaxSize(200, 200);
      button.setOnAction(event -> infoVivienda(edificio, viviendo));
      boxPoke.getChildren().add(button);
    }
    edificio.addIncubando(new Huevo(PokeGenerator.newPoke(), partida.getHora()));
    for (int i = 0; i < edificio.getIncubando().size(); i++) {
      Image imageHuevo = new Image(getClass().getResourceAsStream("/img/button/huevo.png"));
      ImageView imageViewHuevo = new ImageView(imageHuevo);
      imageViewHuevo.setFitHeight(iconSize);
      imageViewHuevo.setPreserveRatio(true);
      Button button = new Button("???", imageViewHuevo);
      button.setMinSize(200, 200);
      button.setMaxSize(200, 200);
      button.setContentDisplay(ContentDisplay.TOP);
      Font font = Font.font("System", FontWeight.BOLD, 34);
      button.setFont(font);
      button.setTextFill(Color.WHITE);
      button.setOnAction(null);
      boxPoke.getChildren().add(button);
    }
    if (edificio.getViviendo().size() > 1 && edificio.getViviendo().size() < 6
        && partida.getHuevos() >= 1 && edificio.getIncubando().size() < 4) {
      Image imageCesta = new Image(getClass().getResourceAsStream("/img/button/cesta.png"));
      ImageView imageViewCesta = new ImageView(imageCesta);
      imageViewCesta.setFitHeight(iconSize);
      imageViewCesta.setPreserveRatio(true);
      Button button2 = new Button("Dar", imageViewCesta);
      button2.setMinSize(200, 200);
      button2.setMaxSize(200, 200);
      button2.setContentDisplay(ContentDisplay.TOP);
      Font font2 = Font.font("System", FontWeight.BOLD, 34);
      button2.setFont(font2);
      button2.setTextFill(Color.WHITE);
      button2.setOnAction(event -> addHuevo(edificio));
      boxPoke.getChildren().add(button2);
    }
  }

  private void nacer(Huevo huevo, Building edificio) {
    boxPoke.getChildren().clear();
    huevo.getFuturoHijo().setCasa(true);
    partida.getPokemones().add(huevo.getFuturoHijo());
    edificio.addViviendo(huevo.getFuturoHijo());
    for (Pokemon viviendo : edificio.getViviendo()) {
      Image imageViviendo =
          new Image(getClass().getResourceAsStream("/img/poke/" + viviendo.getImage() + ".png"));
      ImageView imageViewViviendo = new ImageView(imageViviendo);
      imageViewViviendo.setFitHeight(175);
      imageViewViviendo.setPreserveRatio(true);
      Button button = new Button();
      button.setGraphic(imageViewViviendo);
      button.setMinSize(200, 200);
      button.setMaxSize(200, 200);
      button.setOnAction(event -> infoVivienda(edificio, viviendo));
      boxPoke.getChildren().add(button);
    }
    if (edificio.getIncubando().size() >= 1) {
      for (int i = 0; i < edificio.getIncubando().size(); i++) {
        Image imageHuevo = new Image(getClass().getResourceAsStream("/img/button/huevo.png"));
        ImageView imageViewHuevo = new ImageView(imageHuevo);
        imageViewHuevo.setFitHeight(iconSize);
        imageViewHuevo.setPreserveRatio(true);
        Button button = new Button("???", imageViewHuevo);
        button.setMinSize(200, 200);
        button.setMaxSize(200, 200);
        button.setContentDisplay(ContentDisplay.TOP);
        Font font = Font.font("System", FontWeight.BOLD, 34);
        button.setFont(font);
        button.setTextFill(Color.WHITE);
        button.setOnAction(null);
        boxPoke.getChildren().add(button);
      }
    }
    if (edificio.getViviendo().size() > 1 && edificio.getViviendo().size() < 6
        && partida.getHuevos() >= 1 && edificio.getIncubando().size() < 4) {
      Image imageCesta = new Image(getClass().getResourceAsStream("/img/button/cesta.png"));
      ImageView imageViewCesta = new ImageView(imageCesta);
      imageViewCesta.setFitHeight(iconSize);
      imageViewCesta.setPreserveRatio(true);
      Button button = new Button("Dar", imageViewCesta);
      button.setMinSize(200, 200);
      button.setMaxSize(200, 200);
      button.setContentDisplay(ContentDisplay.TOP);
      Font font = Font.font("System", FontWeight.BOLD, 34);
      button.setFont(font);
      button.setTextFill(Color.WHITE);
      button.setOnAction(event -> addHuevo(edificio));
      boxPoke.getChildren().add(button);
    }
    spawnPoke(huevo.getFuturoHijo());
    setPokeNumber();
  }

  /**
   * Calcula que imagen mostrar en la satisfaccion del Poke trabajador
   * 
   * @param pokeTrabajador
   * @param edificio
   * @return
   */
  private String calcularSatisfaccion(Pokemon pokeTrabajador, Building edificio) {
    if (pokeTrabajador.getLike().equals(edificio.getClase())) {
      return "feliz";
    } else if (pokeTrabajador.getDislike().equals(edificio.getClase())) {
      return "triste";
    } else {
      return "normal";
    }
  }

  /**
   * Calcula el salario dependiendo de los gustos del Pokémon
   * 
   * @param pokeTrabajador
   * @param edificio
   * @return
   */
  private int calcularSalario(Pokemon pokeTrabajador, Building edificio) {
    if (pokeTrabajador.getLike().equals(edificio.getClase())) {
      return (1 + pokeTrabajador.getAmistad()) * 2;
    } else if (pokeTrabajador.getDislike().equals(edificio.getClase())) {
      return 0 + pokeTrabajador.getAmistad();
    } else {
      return 1 + pokeTrabajador.getAmistad();
    }
  }

  private void calcularXP(Pokemon pokeTrabajador, String claseRegalo) {
    if (pokeTrabajador.getLike().equals(claseRegalo)) {
      pokeTrabajador.setXp(pokeTrabajador.getXp() + 0.25);
    } else if (pokeTrabajador.getDislike().equals(claseRegalo)) {
      pokeTrabajador.setXp(pokeTrabajador.getXp() - 0.25);
    } else {
      pokeTrabajador.setXp(pokeTrabajador.getXp() + 0.10);
    }
  }

  /**
   * Desplaza hacia adelante el trabajador de la lista de disponibles
   * 
   * @param eventOrigen
   * @param edificio
   */
  public void cambiarTrabajador(ActionEvent eventOrigen, Building edificio) {
    if (edificio.getTrabajador() == null) {
      Pokemon proximoTrabajador = null;
      for (Pokemon pokemon : partida.getPokemones()) {
        if (!pokemon.isTrabaja() && pokemon.isCasa()) {
          proximoTrabajador = pokemon;
          break;
        }
      }
      if (proximoTrabajador != null) {
        edificio.setTrabajador(proximoTrabajador);
        pokeTrabajador.setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador()));
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador.setFitHeight(iconSize);
        pokeTrabajador.setPreserveRatio(true);
        pokeTrabajador.setImage(imagePoke);
        trabajador.setText(proximoTrabajador.getNombre());
        Image image = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador.setGraphic(imageView);
      } else {
        pokeTrabajador.setOnMouseClicked(null);
        edificio.setTrabajador(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador.setFitHeight(iconSize);
        pokeTrabajador.setPreserveRatio(true);
        pokeTrabajador.setImage(imageEmpty);
        trabajador.setText("Sin trabajador");
        trabajador.setGraphic(null);
      }
    } else {
      int currentIndex = partida.getPokemones().indexOf(edificio.getTrabajador());
      int nextIndex = (currentIndex + 1) % partida.getPokemones().size();
      Pokemon proximoTrabajador = null;
      if (currentIndex != partida.getPokemones().size() - 1) {
        for (int i = nextIndex; i != currentIndex; i = (i + 1) % partida.getPokemones().size()) {
          Pokemon pokemon = partida.getPokemones().get(i);
          if (i == partida.getPokemones().size() - 1
              && (pokemon.isTrabaja() || !pokemon.isCasa())) {
            break;
          } else if (!pokemon.isTrabaja() && pokemon.isCasa()) {
            proximoTrabajador = pokemon;
            break;
          }
        }
      }
      if (proximoTrabajador != null) {
        edificio.getTrabajador().setTrabaja(false);
        edificio.setTrabajador(proximoTrabajador);
        pokeTrabajador.setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador()));
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador.setFitHeight(iconSize);
        pokeTrabajador.setPreserveRatio(true);
        pokeTrabajador.setImage(imagePoke);
        trabajador.setText(proximoTrabajador.getNombre());
        Image image = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador.setGraphic(imageView);
      } else {
        pokeTrabajador.setOnMouseClicked(null);
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
    calcularProduccion(edificio);
  }

  public void cambiarTrabajador2(ActionEvent eventOrigen, Building edificio) {
    if (edificio.getTrabajador2() == null) {
      Pokemon proximoTrabajador = null;
      for (Pokemon pokemon : partida.getPokemones()) {
        if (!pokemon.isTrabaja() && pokemon.isCasa()) {
          proximoTrabajador = pokemon;
          break;
        }
      }
      if (proximoTrabajador != null) {
        edificio.setTrabajador2(proximoTrabajador);
        pokeTrabajador2
            .setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador2()));
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador2.setFitHeight(iconSize);
        pokeTrabajador2.setPreserveRatio(true);
        pokeTrabajador2.setImage(imagePoke);
        trabajador2.setText(proximoTrabajador.getNombre());
        Image image = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador2(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador2.setGraphic(imageView);
      } else {
        pokeTrabajador2.setOnMouseClicked(null);
        edificio.setTrabajador2(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador2.setFitHeight(iconSize);
        pokeTrabajador2.setPreserveRatio(true);
        pokeTrabajador2.setImage(imageEmpty);
        trabajador2.setText("Sin trabajador");
        trabajador2.setGraphic(null);
      }
    } else {
      int currentIndex = partida.getPokemones().indexOf(edificio.getTrabajador2());
      int nextIndex = (currentIndex + 1) % partida.getPokemones().size();
      Pokemon proximoTrabajador = null;
      if (currentIndex != partida.getPokemones().size() - 1) {
        for (int i = nextIndex; i != currentIndex; i = (i + 1) % partida.getPokemones().size()) {
          Pokemon pokemon = partida.getPokemones().get(i);
          if (i == partida.getPokemones().size() - 1
              && (pokemon.isTrabaja() || !pokemon.isCasa())) {
            break;
          } else if (!pokemon.isTrabaja() && pokemon.isCasa()) {
            proximoTrabajador = pokemon;
            break;
          }
        }
      }
      if (proximoTrabajador != null) {
        edificio.getTrabajador2().setTrabaja(false);
        edificio.setTrabajador2(proximoTrabajador);
        pokeTrabajador2
            .setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador2()));
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador2.setFitHeight(iconSize);
        pokeTrabajador2.setPreserveRatio(true);
        pokeTrabajador2.setImage(imagePoke);
        trabajador2.setText(proximoTrabajador.getNombre());
        Image image = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador2(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador2.setGraphic(imageView);
      } else {
        pokeTrabajador2.setOnMouseClicked(null);
        edificio.getTrabajador2().setTrabaja(false);
        edificio.setTrabajador2(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador2.setFitHeight(iconSize);
        pokeTrabajador2.setPreserveRatio(true);
        pokeTrabajador2.setImage(imageEmpty);
        trabajador2.setText("Sin trabajador");
        trabajador2.setGraphic(null);
      }
    }
    calcularProduccion(edificio);
  }

  public void cambiarTrabajador3(ActionEvent eventOrigen, Building edificio) {
    if (edificio.getTrabajador3() == null) {
      Pokemon proximoTrabajador = null;
      for (Pokemon pokemon : partida.getPokemones()) {
        if (!pokemon.isTrabaja() && pokemon.isCasa()) {
          proximoTrabajador = pokemon;
          break;
        }
      }
      if (proximoTrabajador != null) {
        edificio.setTrabajador3(proximoTrabajador);
        pokeTrabajador3
            .setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador3()));
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador3.setFitHeight(iconSize);
        pokeTrabajador3.setPreserveRatio(true);
        pokeTrabajador3.setImage(imagePoke);
        trabajador3.setText(proximoTrabajador.getNombre());
        Image image = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador3(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador3.setGraphic(imageView);
      } else {
        pokeTrabajador3.setOnMouseClicked(null);
        edificio.setTrabajador3(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador3.setFitHeight(iconSize);
        pokeTrabajador3.setPreserveRatio(true);
        pokeTrabajador3.setImage(imageEmpty);
        trabajador3.setText("Sin trabajador");
        trabajador3.setGraphic(null);
      }
    } else {
      int currentIndex = partida.getPokemones().indexOf(edificio.getTrabajador3());
      int nextIndex = (currentIndex + 1) % partida.getPokemones().size();
      Pokemon proximoTrabajador = null;
      if (currentIndex != partida.getPokemones().size() - 1) {
        for (int i = nextIndex; i != currentIndex; i = (i + 1) % partida.getPokemones().size()) {
          Pokemon pokemon = partida.getPokemones().get(i);
          if (i == partida.getPokemones().size() - 1
              && (pokemon.isTrabaja() || !pokemon.isCasa())) {
            break;
          } else if (!pokemon.isTrabaja() && pokemon.isCasa()) {
            proximoTrabajador = pokemon;
            break;
          }
        }
      }
      if (proximoTrabajador != null) {
        edificio.getTrabajador3().setTrabaja(false);
        edificio.setTrabajador3(proximoTrabajador);
        pokeTrabajador3
            .setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador3()));
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador3.setFitHeight(iconSize);
        pokeTrabajador3.setPreserveRatio(true);
        pokeTrabajador3.setImage(imagePoke);
        trabajador3.setText(proximoTrabajador.getNombre());
        Image image = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador3(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador3.setGraphic(imageView);
      } else {
        pokeTrabajador3.setOnMouseClicked(null);
        edificio.getTrabajador3().setTrabaja(false);
        edificio.setTrabajador3(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador3.setFitHeight(iconSize);
        pokeTrabajador3.setPreserveRatio(true);
        pokeTrabajador3.setImage(imageEmpty);
        trabajador3.setText("Sin trabajador");
        trabajador3.setGraphic(null);
      }
    }
    calcularProduccion(edificio);
  }

  public void cambiarTrabajador4(ActionEvent eventOrigen, Building edificio) {
    if (edificio.getTrabajador4() == null) {
      Pokemon proximoTrabajador = null;
      for (Pokemon pokemon : partida.getPokemones()) {
        if (!pokemon.isTrabaja() && pokemon.isCasa()) {
          proximoTrabajador = pokemon;
          break;
        }
      }
      if (proximoTrabajador != null) {
        edificio.setTrabajador4(proximoTrabajador);
        pokeTrabajador4
            .setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador4()));
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador4.setFitHeight(iconSize);
        pokeTrabajador4.setPreserveRatio(true);
        pokeTrabajador4.setImage(imagePoke);
        trabajador4.setText(proximoTrabajador.getNombre());
        Image image = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador4(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador4.setGraphic(imageView);
      } else {
        pokeTrabajador4.setOnMouseClicked(null);
        edificio.setTrabajador4(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador4.setFitHeight(iconSize);
        pokeTrabajador4.setPreserveRatio(true);
        pokeTrabajador4.setImage(imageEmpty);
        trabajador4.setText("Sin trabajador");
        trabajador4.setGraphic(null);
      }
    } else {
      int currentIndex = partida.getPokemones().indexOf(edificio.getTrabajador4());
      int nextIndex = (currentIndex + 1) % partida.getPokemones().size();
      Pokemon proximoTrabajador = null;
      if (currentIndex != partida.getPokemones().size() - 1) {
        for (int i = nextIndex; i != currentIndex; i = (i + 1) % partida.getPokemones().size()) {
          Pokemon pokemon = partida.getPokemones().get(i);
          if (i == partida.getPokemones().size() - 1
              && (pokemon.isTrabaja() || !pokemon.isCasa())) {
            break;
          } else if (!pokemon.isTrabaja() && pokemon.isCasa()) {
            proximoTrabajador = pokemon;
            break;
          }
        }
      }
      if (proximoTrabajador != null) {
        edificio.getTrabajador4().setTrabaja(false);
        edificio.setTrabajador4(proximoTrabajador);
        pokeTrabajador4
            .setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador4()));
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador4.setFitHeight(iconSize);
        pokeTrabajador4.setPreserveRatio(true);
        pokeTrabajador4.setImage(imagePoke);
        trabajador4.setText(proximoTrabajador.getNombre());
        Image image = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador4(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador4.setGraphic(imageView);
      } else {
        pokeTrabajador4.setOnMouseClicked(null);
        edificio.getTrabajador4().setTrabaja(false);
        edificio.setTrabajador4(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador4.setFitHeight(iconSize);
        pokeTrabajador4.setPreserveRatio(true);
        pokeTrabajador4.setImage(imageEmpty);
        trabajador4.setText("Sin trabajador");
        trabajador4.setGraphic(null);
      }
    }
    calcularProduccion(edificio);
  }


  /**
   * Desplaza hacia atrás el trabajador de la lista de disponibles
   * 
   * @param eventOrigen
   * @param edificio
   */
  public void retrocederTrabajador(ActionEvent eventOrigen, Building edificio) {
    if (edificio.getTrabajador() == null) {
      Pokemon proximoTrabajador = null;
      for (int i = partida.getPokemones().size() - 1; i >= 0; i--) {
        Pokemon pokemon = partida.getPokemones().get(i);
        if (!pokemon.isTrabaja() && pokemon.isCasa()) {
          proximoTrabajador = pokemon;
          break;
        }
      }
      if (proximoTrabajador != null) {
        edificio.setTrabajador(proximoTrabajador);
        pokeTrabajador.setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador()));
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador.setFitHeight(iconSize);
        pokeTrabajador.setPreserveRatio(true);
        pokeTrabajador.setImage(imagePoke);
        trabajador.setText(proximoTrabajador.getNombre());
        Image image = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador.setGraphic(imageView);
      } else {
        pokeTrabajador.setOnMouseClicked(null);
        edificio.setTrabajador(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador.setFitHeight(iconSize);
        pokeTrabajador.setPreserveRatio(true);
        pokeTrabajador.setImage(imageEmpty);
        trabajador.setText("Sin trabajador");
        trabajador.setGraphic(null);
      }
    } else {
      int currentIndex = partida.getPokemones().indexOf(edificio.getTrabajador());
      int previousIndex =
          (currentIndex - 1 + partida.getPokemones().size()) % partida.getPokemones().size();
      Pokemon proximoTrabajador = null;
      if (currentIndex != 0) {
        for (int i = previousIndex; i != currentIndex; i =
            (i - 1 + partida.getPokemones().size()) % partida.getPokemones().size()) {
          Pokemon pokemon = partida.getPokemones().get(i);
          if (i == 0 && pokemon.isTrabaja()) {
            break;
          } else if (!pokemon.isTrabaja() && pokemon.isCasa()) {
            proximoTrabajador = pokemon;
            break;
          }
        }
      }
      if (proximoTrabajador != null) {
        edificio.getTrabajador().setTrabaja(false);
        edificio.setTrabajador(proximoTrabajador);
        pokeTrabajador.setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador()));
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador.setFitHeight(iconSize);
        pokeTrabajador.setPreserveRatio(true);
        pokeTrabajador.setImage(imagePoke);
        trabajador.setText(proximoTrabajador.getNombre());
        Image image = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador.setGraphic(imageView);
      } else {
        pokeTrabajador.setOnMouseClicked(null);
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
    calcularProduccion(edificio);
  }

  public void retrocederTrabajador2(ActionEvent eventOrigen, Building edificio) {
    if (edificio.getTrabajador2() == null) {
      Pokemon proximoTrabajador = null;
      for (int i = partida.getPokemones().size() - 1; i >= 0; i--) {
        Pokemon pokemon = partida.getPokemones().get(i);
        if (!pokemon.isTrabaja() && pokemon.isCasa()) {
          proximoTrabajador = pokemon;
          break;
        }
      }
      if (proximoTrabajador != null) {
        edificio.setTrabajador2(proximoTrabajador);
        pokeTrabajador2
            .setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador2()));
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador2.setFitHeight(iconSize);
        pokeTrabajador2.setPreserveRatio(true);
        pokeTrabajador2.setImage(imagePoke);
        trabajador2.setText(proximoTrabajador.getNombre());
        Image image = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador2(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador2.setGraphic(imageView);
      } else {
        pokeTrabajador2.setOnMouseClicked(null);
        edificio.setTrabajador2(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador2.setFitHeight(iconSize);
        pokeTrabajador2.setPreserveRatio(true);
        pokeTrabajador2.setImage(imageEmpty);
        trabajador2.setText("Sin trabajador");
        trabajador2.setGraphic(null);
      }
    } else {
      int currentIndex = partida.getPokemones().indexOf(edificio.getTrabajador2());
      int previousIndex =
          (currentIndex - 1 + partida.getPokemones().size()) % partida.getPokemones().size();
      Pokemon proximoTrabajador = null;
      if (currentIndex != 0) {
        for (int i = previousIndex; i != currentIndex; i =
            (i - 1 + partida.getPokemones().size()) % partida.getPokemones().size()) {
          Pokemon pokemon = partida.getPokemones().get(i);
          if (i == 0 && pokemon.isTrabaja()) {
            break;
          } else if (!pokemon.isTrabaja() && pokemon.isCasa()) {
            proximoTrabajador = pokemon;
            break;
          }
        }
      }
      if (proximoTrabajador != null) {
        edificio.getTrabajador2().setTrabaja(false);
        edificio.setTrabajador2(proximoTrabajador);
        pokeTrabajador2
            .setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador2()));
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador2.setFitHeight(iconSize);
        pokeTrabajador2.setPreserveRatio(true);
        pokeTrabajador2.setImage(imagePoke);
        trabajador2.setText(proximoTrabajador.getNombre());
        Image image = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador2(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador2.setGraphic(imageView);
      } else {
        pokeTrabajador2.setOnMouseClicked(null);
        edificio.getTrabajador2().setTrabaja(false);
        edificio.setTrabajador2(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador2.setFitHeight(iconSize);
        pokeTrabajador2.setPreserveRatio(true);
        pokeTrabajador2.setImage(imageEmpty);
        trabajador2.setText("Sin trabajador");
        trabajador2.setGraphic(null);
      }

    }
    calcularProduccion(edificio);
  }

  public void retrocederTrabajador3(ActionEvent eventOrigen, Building edificio) {
    if (edificio.getTrabajador3() == null) {
      Pokemon proximoTrabajador = null;
      for (int i = partida.getPokemones().size() - 1; i >= 0; i--) {
        Pokemon pokemon = partida.getPokemones().get(i);
        if (!pokemon.isTrabaja() && pokemon.isCasa()) {
          proximoTrabajador = pokemon;
          break;
        }
      }
      if (proximoTrabajador != null) {
        edificio.setTrabajador3(proximoTrabajador);
        pokeTrabajador3
            .setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador3()));
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador3.setFitHeight(iconSize);
        pokeTrabajador3.setPreserveRatio(true);
        pokeTrabajador3.setImage(imagePoke);
        trabajador3.setText(proximoTrabajador.getNombre());
        Image image = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador3(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador3.setGraphic(imageView);
      } else {
        pokeTrabajador3.setOnMouseClicked(null);
        edificio.setTrabajador3(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador3.setFitHeight(iconSize);
        pokeTrabajador3.setPreserveRatio(true);
        pokeTrabajador3.setImage(imageEmpty);
        trabajador3.setText("Sin trabajador");
        trabajador3.setGraphic(null);
      }
    } else {
      int currentIndex = partida.getPokemones().indexOf(edificio.getTrabajador3());
      int previousIndex =
          (currentIndex - 1 + partida.getPokemones().size()) % partida.getPokemones().size();
      Pokemon proximoTrabajador = null;
      if (currentIndex != 0) {
        for (int i = previousIndex; i != currentIndex; i =
            (i - 1 + partida.getPokemones().size()) % partida.getPokemones().size()) {
          Pokemon pokemon = partida.getPokemones().get(i);
          if (i == 0 && pokemon.isTrabaja()) {
            break;
          } else if (!pokemon.isTrabaja() && pokemon.isCasa()) {
            proximoTrabajador = pokemon;
            break;
          }
        }
      }
      if (proximoTrabajador != null) {
        edificio.getTrabajador3().setTrabaja(false);
        edificio.setTrabajador3(proximoTrabajador);
        pokeTrabajador3
            .setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador3()));
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador3.setFitHeight(iconSize);
        pokeTrabajador3.setPreserveRatio(true);
        pokeTrabajador3.setImage(imagePoke);
        trabajador3.setText(proximoTrabajador.getNombre());
        Image image = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador3(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador3.setGraphic(imageView);
      } else {
        pokeTrabajador3.setOnMouseClicked(null);
        edificio.getTrabajador3().setTrabaja(false);
        edificio.setTrabajador3(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador3.setFitHeight(iconSize);
        pokeTrabajador3.setPreserveRatio(true);
        pokeTrabajador3.setImage(imageEmpty);
        trabajador3.setText("Sin trabajador");
        trabajador3.setGraphic(null);
      }

    }
    calcularProduccion(edificio);
  }

  public void retrocederTrabajador4(ActionEvent eventOrigen, Building edificio) {
    if (edificio.getTrabajador4() == null) {
      Pokemon proximoTrabajador = null;
      for (int i = partida.getPokemones().size() - 1; i >= 0; i--) {
        Pokemon pokemon = partida.getPokemones().get(i);
        if (!pokemon.isTrabaja() && pokemon.isCasa()) {
          proximoTrabajador = pokemon;
          break;
        }
      }
      if (proximoTrabajador != null) {
        edificio.setTrabajador4(proximoTrabajador);
        pokeTrabajador4
            .setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador4()));
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador4.setFitHeight(iconSize);
        pokeTrabajador4.setPreserveRatio(true);
        pokeTrabajador4.setImage(imagePoke);
        trabajador4.setText(proximoTrabajador.getNombre());
        Image image = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador4(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador4.setGraphic(imageView);
      } else {
        pokeTrabajador4.setOnMouseClicked(null);
        edificio.setTrabajador4(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador4.setFitHeight(iconSize);
        pokeTrabajador4.setPreserveRatio(true);
        pokeTrabajador4.setImage(imageEmpty);
        trabajador4.setText("Sin trabajador");
        trabajador4.setGraphic(null);
      }
    } else {
      int currentIndex = partida.getPokemones().indexOf(edificio.getTrabajador4());
      int previousIndex =
          (currentIndex - 1 + partida.getPokemones().size()) % partida.getPokemones().size();
      Pokemon proximoTrabajador = null;
      if (currentIndex != 0) {
        for (int i = previousIndex; i != currentIndex; i =
            (i - 1 + partida.getPokemones().size()) % partida.getPokemones().size()) {
          Pokemon pokemon = partida.getPokemones().get(i);
          if (i == 0 && pokemon.isTrabaja()) {
            break;
          } else if (!pokemon.isTrabaja() && pokemon.isCasa()) {
            proximoTrabajador = pokemon;
            break;
          }
        }
      }
      if (proximoTrabajador != null) {
        edificio.getTrabajador4().setTrabaja(false);
        edificio.setTrabajador4(proximoTrabajador);
        pokeTrabajador4
            .setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador4()));
        proximoTrabajador.setTrabaja(true);
        Image imagePoke = new Image(
            getClass().getResourceAsStream("/img/poke/" + proximoTrabajador.getImage() + ".png"));
        pokeTrabajador4.setFitHeight(iconSize);
        pokeTrabajador4.setPreserveRatio(true);
        pokeTrabajador4.setImage(imagePoke);
        trabajador4.setText(proximoTrabajador.getNombre());
        Image image = new Image(getClass().getResourceAsStream(
            "/img/button/" + calcularSatisfaccion(edificio.getTrabajador4(), edificio) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(iconSizePeque);
        imageView.setPreserveRatio(true);
        trabajador4.setGraphic(imageView);
      } else {
        pokeTrabajador4.setOnMouseClicked(null);
        edificio.getTrabajador4().setTrabaja(false);
        edificio.setTrabajador4(null);
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador4.setFitHeight(iconSize);
        pokeTrabajador4.setPreserveRatio(true);
        pokeTrabajador4.setImage(imageEmpty);
        trabajador4.setText("Sin trabajador");
        trabajador4.setGraphic(null);
      }

    }
    calcularProduccion(edificio);
  }

  public void calcularProduccion(Building edificio) {
    int total = 0;
    if (edificio.getTrabajador() != null) {
      total += calcularSalario(edificio.getTrabajador(), edificio);
    }
    if (edificio.getTrabajador2() != null) {
      total += calcularSalario(edificio.getTrabajador2(), edificio);
    }
    if (edificio.getTrabajador3() != null) {
      total += calcularSalario(edificio.getTrabajador3(), edificio);
    }
    if (edificio.getTrabajador4() != null) {
      total += calcularSalario(edificio.getTrabajador4(), edificio);
    }
    if (total >= 1) {
      produccionEdificio.setText("Produce " + total + "k cada 30min");
    } else {
      produccionEdificio.setText("Producción no activa");
    }
  }


  /**
   * Coloca el sprite del pokémon indicado y le da movimiento
   * 
   * @param spawnNumber
   */
  public void spawnPoke(Pokemon pokemon) {
    Image image =
        new Image(getClass().getResourceAsStream("/img/poke/" + pokemon.getImage() + ".png"));
    ImageView imageView = new ImageView(image);

    imageView.setFitWidth(iconSizePeque);
    imageView.setFitHeight(iconSizePeque);

    superiorGrid.getChildren().add(imageView);

    double centerX = gridAncho / 2 - imageView.getFitWidth() / 2;
    double centerY = gridAlto / 2 - imageView.getFitHeight() / 2;
    imageView.setX(centerX);
    imageView.setY(centerY);

    imageView.toFront();
    imageView.setUserData(pokemon);
    imageView.setOnMouseClicked(event -> clickPokeSpawn(event));

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2.1),
        event -> movePoke(imageView, anchoCuadricula, altoCuadricula)));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  /**
   * Determina en qué edificio vive un pokemon al hacerle click
   * 
   * @param event
   */
  private void clickPokeSpawn(MouseEvent event) {
    ImageView imagePoke = (ImageView) event.getSource();
    Pokemon pokemon = (Pokemon) imagePoke.getUserData();
    for (Building edificio : partida.getBuildings()) {
      for (Pokemon viviendo : edificio.getViviendo()) {
        if (viviendo == pokemon) {
          dislikePoke.setVisible(true);
          likePoke.setVisible(true);
          likeIcon.setVisible(true);
          dislikeIcon.setVisible(true);
          infoVivienda(edificio, pokemon);
          break;
        }
      }
    }
  }

  /**
   * Abre el menú de info de edificio en base a un Pokémon
   * 
   * @param pokemon
   */
  private void clickPokeSpawnNoSearch(Pokemon pokemon) {
    if (pokemon != null) {
      for (Building edificio : partida.getBuildings()) {
        for (Pokemon viviendo : edificio.getViviendo()) {
          if (viviendo == pokemon) {
            dislikePoke.setVisible(true);
            likePoke.setVisible(true);
            likeIcon.setVisible(true);
            dislikeIcon.setVisible(true);
            infoVivienda(edificio, pokemon);
            break;
          }
        }
      }
    }
  }

  /**
   * Determina en qué edificio trabaja un pokemon (texto)
   * 
   * @param event
   */
  private String getPokeWork(Pokemon pokemon) {
    for (Building edificio : partida.getBuildings()) {
      if (edificio.getTrabajador() == pokemon) {
        return "Trabaja en: " + edificio.getImage();
      } else if (edificio.getTrabajador2() == pokemon) {
        return "Trabaja en: " + edificio.getImage();
      } else if (edificio.getTrabajador3() == pokemon) {
        return "Trabaja en: " + edificio.getImage();
      } else if (edificio.getTrabajador4() == pokemon) {
        return "Trabaja en: " + edificio.getImage();
      }
    }
    return "Desempleado";
  }

  /**
   * Determina en qué edificio trabaja un pokemon (objeto)
   * 
   * @param event
   */
  private Building getPokeBuilding(Pokemon pokemon) {
    for (Building edificio : partida.getBuildings()) {
      if (edificio.getTrabajador() == pokemon) {
        return edificio;
      } else if (edificio.getTrabajador2() == pokemon) {
        return edificio;
      } else if (edificio.getTrabajador3() == pokemon) {
        return edificio;
      } else if (edificio.getTrabajador4() == pokemon) {
        return edificio;
      }
    }
    return null;
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
        if (currentX + 103 < gridAncho - moveX - imageView.getFitWidth() / 2) {
          transition.setByX(moveX);
        } else {
          transition.setByX(-moveX);
        }
        break;
      case 1:
        if (currentX - 103 > moveX - imageView.getFitWidth() / 2) {
          transition.setByX(-moveX);
        } else {
          transition.setByX(moveX);
        }
        break;
      case 2:
        if (currentY + 103 < gridAlto - moveY - imageView.getFitHeight() / 2) {
          transition.setByY(moveY);
        } else {
          transition.setByY(-moveY);
        }
        break;
      case 3:
        if (currentY - 103 > moveY - imageView.getFitHeight() / 2) {
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

  /**
   * Optimiza todos los nodos para evitar lag
   * 
   * @param node
   */
  private void applyCache(Node node) {
    node.setCache(true);
    node.setCacheHint(CacheHint.SPEED);

    if (node instanceof javafx.scene.Parent) {
      javafx.scene.Parent parent = (javafx.scene.Parent) node;
      for (Node child : parent.getChildrenUnmodifiable()) {
        applyCache(child);
      }
    }
  }

  /**
   * Cambia aleatoriamente el nombre de un pokemon ya existente
   * 
   * @param pokemon
   */
  private void rerollName(Pokemon pokemon) {
    pokemon.setNombre(PokeGenerator.name());
    nombreViviendo.setText(fullName(pokemon));
  }

  private void mejorarEdificio(Building edificio) {
    if (edificio.getNumMejoras() == 0) {
      edificio.setNumMejoras(edificio.getNumMejoras() + 1);
      partida.setDinero(partida.getDinero() - 50);
      cambiarPokeDer2.setVisible(true);
      cambiarPokeIzq2.setVisible(true);
      trabajador2.setVisible(true);
      pokeTrabajador2.setVisible(true);
    } else if (edificio.getNumMejoras() == 1) {
      edificio.setNumMejoras(edificio.getNumMejoras() + 1);
      partida.setDinero(partida.getDinero() - 100);
      cambiarPokeDer3.setVisible(true);
      cambiarPokeIzq3.setVisible(true);
      trabajador3.setVisible(true);
      pokeTrabajador3.setVisible(true);
    } else if (edificio.getNumMejoras() == 2) {
      edificio.setNumMejoras(edificio.getNumMejoras() + 1);
      partida.setDinero(partida.getDinero() - 200);
      cambiarPokeDer4.setVisible(true);
      cambiarPokeIzq4.setVisible(true);
      trabajador4.setVisible(true);
      pokeTrabajador4.setVisible(true);
    }
    dineroText.setText(partida.getDinero() + "k");
    stringMejoras(edificio);
    if ((edificio.getNumMejoras() == 0 && partida.getDinero() >= 50)
        || (edificio.getNumMejoras() == 1 && partida.getDinero() >= 100)
        || (edificio.getNumMejoras() == 2 && partida.getDinero() >= 200)) {
      mejorarEdificio.setOnAction(event -> mejorarEdificio(edificio));
      mejorarEdificio.setDisable(false);
    } else {
      mejorarEdificio.setDisable(true);
    }
    stringRegalos(edificio);
    if (partida.getSelectedBuilding().getNumMejoras() > 0
        && partida.getDinero() >= precioRegalos(partida.getSelectedBuilding())) {
      comprarRegaloEdificio.setVisible(true);
      comprarRegaloEdificio.setDisable(false);
      comprarRegaloEdificio
          .setOnAction(event -> comprarRegaloEdificio(partida.getSelectedBuilding()));
    } else if (partida.getSelectedBuilding().getNumMejoras() > 0
        && partida.getDinero() < precioRegalos(partida.getSelectedBuilding())) {
      comprarRegaloEdificio.setVisible(true);
      comprarRegaloEdificio.setDisable(true);
    } else {
      comprarRegaloEdificio.setVisible(false);
    }
  }

  private void stringMejoras(Building edificio) {
    if (edificio.getNumMejoras() == 0) {
      mejorarEdificio.setText("Mejorar edificio (50k)");
    } else if (edificio.getNumMejoras() == 1) {
      mejorarEdificio.setText("Mejorar edificio (100k)");
    } else if (edificio.getNumMejoras() == 2) {
      mejorarEdificio.setText("Mejorar edificio (200k)");
    } else if (edificio.getNumMejoras() == maxMejoras) {
      mejorarEdificio.setText("Máx. Mejoras");
    }
  }

  private void borrarBotonEdificio(int row, int col) {
    for (var node : cuadricula.getChildren()) {
      if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
        cuadricula.getChildren().remove(node);
        break;
      }
    }
  }

}

