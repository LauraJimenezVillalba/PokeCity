package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import models.Partida;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import java.io.*;

public class Main extends Application {

  private static String savefile = "savefile.ser";
  private FXMLLoader loader;

  @Override
  public void start(Stage primaryStage) {
    try {
      Partida partida = null;
      File partidaFile = new File(savefile);
      if (partidaFile.exists()) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(partidaFile))) {
          partida = (Partida) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
          e.printStackTrace();
        }
      }

      loader = new FXMLLoader();
      loader.setLocation(Main.class.getResource("/views/Main.fxml"));
      loader.setController(new MainController(partida));
      Pane ventana = (Pane) loader.load();
      Scene scene = new Scene(ventana);
      scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.setResizable(false);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void stop() {
    MainController controller = loader.getController();
    Partida partida = controller.getPartida();
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savefile))) {
      oos.writeObject(partida);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
