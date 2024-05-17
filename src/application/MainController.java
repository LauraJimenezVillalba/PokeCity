package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Building;
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
  private Button restart;

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
  private Button closeRegalos;
  
  @FXML
  private Button amistadRegalo;
  
  @FXML
  private ProgressBar medidorAmistad;
  
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

  private final int size = 10;
  private int iconSize = 100;
  private int iconSizeGrande = 160;
  private int iconSizePeque = 75;
  private double gridAncho = 2866.67;
  private double gridAlto = 1700;
  private boolean menu = true;
  private int anchoCuadricula = 267;
  private int altoCuadricula = 150;

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
    if (partida.getPendiente() > 0 && !menu) {
      notifica.setText(String.valueOf(partida.getPendiente()));
      notifica.setVisible(true);
      notificaCircle.setVisible(true);
    }
    hora.setText("Día " + partida.getDay() + " / " + nuevaHora);
    setPokeNumber();
    if (!partida.isIntercalado()) {
      for (Building edificio : partida.getBuildings()) {
        if (edificio.isDesplegado() && edificio.getTrabajador() != null) {
          partida
              .setDinero(partida.getDinero() + calcularSalario(edificio.getTrabajador(), edificio));
        }
      }
      dineroText.setText(partida.getDinero() + "k");
      partida.setIntercalado(true);
    } else {
      partida.setIntercalado(false);
    }
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
    openPrincipal();

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

    Image image6 = new Image(getClass().getResourceAsStream("/img/button/derecha.png"));
    ImageView imageView6 = new ImageView(image6);
    imageView6.setFitHeight(iconSize);
    imageView6.setPreserveRatio(true);
    cambiarPokeDer.setGraphic(imageView6);

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
    
    Image imageRegaloServicios = new Image(getClass().getResourceAsStream("/img/clases/Servicios.png"));
    ImageView imageViewRegaloServicios = new ImageView(imageRegaloServicios);
    imageViewRegaloServicios.setFitHeight(iconSize);
    imageViewRegaloServicios.setPreserveRatio(true);
    regalosServicios.setGraphic(imageViewRegaloServicios);
    
    Image imageRegaloMedicina = new Image(getClass().getResourceAsStream("/img/clases/Medicina.png"));
    ImageView imageViewRegaloMedicina = new ImageView(imageRegaloMedicina);
    imageViewRegaloMedicina.setFitHeight(iconSize);
    imageViewRegaloMedicina.setPreserveRatio(true);
    regalosMedicina.setGraphic(imageViewRegaloMedicina);
    
    Image imageRegaloEspectaculos = new Image(getClass().getResourceAsStream("/img/clases/Espectáculos.png"));
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
    
    Image imageRegaloAleatorio = new Image(getClass().getResourceAsStream("/img/button/regalo.png"));
    ImageView imageViewRegaloAleatorio = new ImageView(imageRegaloAleatorio);
    imageViewRegaloAleatorio.setFitHeight(iconSize);
    imageViewRegaloAleatorio.setPreserveRatio(true);
    regaloAleatorio.setGraphic(imageViewRegaloAleatorio);

    inventory.toBack();
    verEdificio.toBack();
    decision.toBack();
    pantallaRegalos.toBack();
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
        int numeroAleatorio = rand.nextInt(3) + 1;
        String[] clasesDecisiones = BuildingGenerator.randomClases();

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

        switch (numeroAleatorio) {
          case 1:
            decision1.setText("+1 Edificio de clase " + clasesDecisiones[0]);
            decision2.setText("+1 Edificio de clase " + clasesDecisiones[1]);
            decision1.setGraphic(imageView);
            decision2.setGraphic(imageView2);
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
        }
        toggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
          if (newToggle == null) {
            decidirAceptar.setVisible(false);
          } else {
            ToggleButton selectedToggleButton = (ToggleButton) newToggle;
            decidirAceptar.setVisible(true);
            decidirAceptar.setOnAction(
                event -> aceptarFinal(selectedToggleButton.getText(), newPoke1, newPoke2));
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
      if (edificio.getViviendo() == null && edificio.getClase().equals("Vivienda")
          && edificio.isDesplegado()) {
        pokemon.setCasa(true);
        edificio.setViviendo(pokemon);
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
  public void aceptarFinal(String texto, Pokemon new1, Pokemon new2) {
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
    pantallaRegalos.toFront();
    if (partida.getDinero() >= 50) {
      regaloAleatorio.setVisible(true);
    } else {
      regaloAleatorio.setVisible(false);
    }
  }
  
  public void addRegaloRandom() {
    String[] selectedStrings = PokeGenerator.likesAndDislikes();
    addRegalo(selectedStrings[0]);
    partida.setDinero(partida.getDinero() - 50);
    dineroText.setText(partida.getDinero() + "k");
  }
  
  /**
   * Añade un regalo del tipo indicado al inventario
   * @param type
   */
  public void addRegalo(String type) {
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
    openRegalos();
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
      Image image =
          new Image(getClass().getResourceAsStream("/img/poke/" + pokedexMon + ".png"));
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
          edificio.setViviendo(pokemon);
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
      cambiarPokeIzq.setVisible(false);
      pokeTrabajador.setImage(null);
      trabajador.setVisible(false);
      if (edificio.getViviendo() != null) {
        medidorAmistad.setVisible(true);
        amistadRegalo.setVisible(true);
        amistadRegalo.setText(String.valueOf(edificio.getViviendo().getAmistad()));
        Image imageViviendo = new Image(getClass()
            .getResourceAsStream("/img/poke/" + edificio.getViviendo().getImage() + ".png"));
        pokeViviendo.setImage(imageViviendo);
        nombreViviendo.setText(fullName(edificio.getViviendo()));
        Image imageLike = new Image(getClass()
            .getResourceAsStream("/img/clases/" + edificio.getViviendo().getLike() + ".png"));
        ImageView imageViewLike = new ImageView(imageLike);
        imageViewLike.setFitHeight(iconSizePeque);
        imageViewLike.setPreserveRatio(true);
        likePoke.setGraphic(imageViewLike);
        likePoke.setText(edificio.getViviendo().getLike());
        Image imageDislike = new Image(getClass()
            .getResourceAsStream("/img/clases/" + edificio.getViviendo().getDislike() + ".png"));
        ImageView imageViewDislike = new ImageView(imageDislike);
        imageViewDislike.setFitHeight(iconSizePeque);
        imageViewDislike.setPreserveRatio(true);
        dislikePoke.setGraphic(imageViewDislike);
        dislikePoke.setText(edificio.getViviendo().getDislike());
        descTrabajo.setVisible(true);
        descTrabajo.setText(getPokeWork(edificio.getViviendo()));
        produccionEdificio.setText("1 Pokémon vive aquí");
        if (edificio.getViviendo().isTrabaja()) {
          verTrabajo.setVisible(true);
          verTrabajo.setOnAction(event -> infoEdificioTrue(getPokeBuilding(edificio.getViviendo())));
        } else {
          verTrabajo.setVisible(false);
        }
      } else {
        medidorAmistad.setVisible(false);
        amistadRegalo.setVisible(false);
        verTrabajo.setVisible(false);
        produccionEdificio.setText("Ningún Pokémon vive aquí");
        Image imageViviendo = new Image(getClass().getResourceAsStream("/img/button/no.png"));
        pokeViviendo.setImage(imageViviendo);
        nombreViviendo.setText("Vacío");
        dislikePoke.setVisible(false);
        likePoke.setVisible(false);
        likeIcon.setVisible(false);
        dislikeIcon.setVisible(false);
        descTrabajo.setVisible(false);
      }
      pokeViviendo.setVisible(true);
      nombreViviendo.setVisible(true);
    } else {
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
      nombreViviendo.setVisible(false);
      if (edificio.getTrabajador() == null) {
        produccionEdificio.setText("Producción no activa");
        Image imageEmpty = new Image(getClass().getResourceAsStream("/img/button/construir.png"));
        pokeTrabajador.setFitHeight(iconSize);
        pokeTrabajador.setPreserveRatio(true);
        pokeTrabajador.setImage(imageEmpty);
        trabajador.setText("Sin trabajador");
        trabajador.setGraphic(null);
        pokeTrabajador.setOnMouseClicked(null);
      } else {
        pokeTrabajador.setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador()));
        produccionEdificio.setText(
            "Produce " + calcularSalario(edificio.getTrabajador(), edificio) + "k cada 30min");
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
      cambiarPokeDer.setOnAction(event -> cambiarTrabajador(event, edificio));
      cambiarPokeIzq.setOnAction(event -> retrocederTrabajador(event, edificio));
    }
    verEdificio.toFront();
  }
  
  /**
   * Muestra el menú de información del edificio seleccionado (empleado)
   * 
   * @param eventOrigen
   */
  public void infoEdificioTrue(Building edificio) {
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
    pokeTrabajador.setOnMouseClicked(event -> clickPokeSpawnNoSearch(edificio.getTrabajador()));
    produccionEdificio.setText(
        "Produce " + calcularSalario(edificio.getTrabajador(), edificio) + "k cada 30min");
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
    cambiarPokeDer.setOnAction(event -> cambiarTrabajador(event, edificio));
    cambiarPokeIzq.setOnAction(event -> retrocederTrabajador(event, edificio));
    verEdificio.toFront();
  }

  /**
   * Muestra la información de un edificio pero usando un Pokemon de base
   * 
   * @param eventOrigen
   */
  public void infoVivienda(Building edificio) {
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
    cambiarPokeDer.setVisible(false);
    cambiarPokeIzq.setVisible(false);
    pokeTrabajador.setImage(null);
    trabajador.setVisible(false);
    produccionEdificio.setText("1 Pokémon vive aquí");
    medidorAmistad.setVisible(true);
    amistadRegalo.setVisible(true);
    amistadRegalo.setText(String.valueOf(edificio.getViviendo().getAmistad()));
    Image imageViviendo = new Image(
        getClass().getResourceAsStream("/img/poke/" + edificio.getViviendo().getImage() + ".png"));
    pokeViviendo.setImage(imageViviendo);
    nombreViviendo.setText(fullName(edificio.getViviendo()));
    pokeViviendo.setVisible(true);
    if (edificio.getViviendo().isTrabaja()) {
      verTrabajo.setVisible(true);
      verTrabajo.setOnAction(event -> infoEdificioTrue(getPokeBuilding(edificio.getViviendo())));
    } else {
      verTrabajo.setVisible(false);
    }
    nombreViviendo.setVisible(true);
    Image imageLike = new Image(
        getClass().getResourceAsStream("/img/clases/" + edificio.getViviendo().getLike() + ".png"));
    ImageView imageViewLike = new ImageView(imageLike);
    imageViewLike.setFitHeight(iconSizePeque);
    imageViewLike.setPreserveRatio(true);
    likePoke.setGraphic(imageViewLike);
    likePoke.setText(edificio.getViviendo().getLike());
    Image imageDislike = new Image(getClass()
        .getResourceAsStream("/img/clases/" + edificio.getViviendo().getDislike() + ".png"));
    ImageView imageViewDislike = new ImageView(imageDislike);
    imageViewDislike.setFitHeight(iconSizePeque);
    imageViewDislike.setPreserveRatio(true);
    dislikePoke.setGraphic(imageViewDislike);
    dislikePoke.setText(edificio.getViviendo().getDislike());
    descTrabajo.setVisible(true);
    descTrabajo.setText(getPokeWork(edificio.getViviendo()));
    verEdificio.toFront();
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
        produccionEdificio.setText(
            "Produce " + calcularSalario(edificio.getTrabajador(), edificio) + "k cada 30min");
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
        produccionEdificio.setText("Producción no activa");
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
        produccionEdificio.setText(
            "Produce " + calcularSalario(edificio.getTrabajador(), edificio) + "k cada 30min");
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
        produccionEdificio.setText("Producción no activa");
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
        produccionEdificio.setText(
            "Produce " + calcularSalario(edificio.getTrabajador(), edificio) + "k cada 30min");
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
        produccionEdificio.setText("Producción no activa");
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
        produccionEdificio.setText(
            "Produce " + calcularSalario(edificio.getTrabajador(), edificio) + "k cada 30min");
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
        produccionEdificio.setText("Producción no activa");
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
      if (edificio.getViviendo() == pokemon) {
        dislikePoke.setVisible(true);
        likePoke.setVisible(true);
        likeIcon.setVisible(true);
        dislikeIcon.setVisible(true);
        infoVivienda(edificio);
        break;
      }
    }
  }
  
  /**
   * Abre el menú de info de edificio en base a un Pokémon
   * @param pokemon
   */
  private void clickPokeSpawnNoSearch(Pokemon pokemon) {
    if (pokemon != null) {
      for (Building edificio : partida.getBuildings()) {
        if (edificio.getViviendo() == pokemon) {
          dislikePoke.setVisible(true);
          likePoke.setVisible(true);
          likeIcon.setVisible(true);
          dislikeIcon.setVisible(true);
          infoVivienda(edificio);
          break;
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

}

