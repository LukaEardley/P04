package sample;

import csc2a.uwga.file.TripFileHandler;
import csc2a.uwga.model.Trip;
import java.io.File;
import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TripPane extends StackPane {

 private final String dataDirectory = Paths.get("./data/trips").toAbsolutePath().normalize().toString();

  public TripPane(Stage stage) {

  }

  public Scene createScene(Stage stage) {

    StackPane stackPane = new StackPane();
    Scene scene = new Scene(stackPane);
    final FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
    fileChooser.getExtensionFilters().add(extFilter);
    fileChooser.setInitialDirectory(new File(dataDirectory));
    final Button openButton = new Button("Select a Trip");

    openButton.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(final ActionEvent e) {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
              openFile(file);
            }
          }
        });

    stackPane.getChildren().add(openButton);
    return scene;
  }


  private void openFile(File file) {
    try {
      Trip trip = TripFileHandler.readTrip(file);
    } catch (Exception ex) {
      System.out.println("Oops file not found");
    }
  }

}





