package sample;

import csc2a.uwga.model.Trip;
import java.awt.Button;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("UWGA Trip Monitoring System");
    primaryStage.setScene(new Scene(root, 1000, 200));
    TripPane tripPane = new TripPane(primaryStage);
    primaryStage.setMaximized(true);
    primaryStage.setScene(tripPane.createScene(primaryStage));
    primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }
}
