package sample;

import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TripPane extends StackPane {

    public TripPane() {
    }

    public void start(Stage stage) {

        initUI(stage);
    }

    private void initUI(Stage stage) {

        // Create First TitledPane.
        TitledPane firstTitledPane = new TitledPane();
        firstTitledPane.setText("Trip Information");

        VBox content1 = new VBox();
        content1.getChildren().add(new Label("ID:"));
        content1.getChildren().add(new Label("Courier Name"));
        content1.getChildren().add(new Label("Priority"));
        firstTitledPane.setContent(content1);

        // Create Second TitledPane.
        TitledPane secondTitledPane = new TitledPane();
        secondTitledPane.setText("Item Information");

        VBox content2 = new VBox();
        content2.getChildren().add(new Label("ID:"));
        content2.getChildren().add(new Label("Name:"));
        content2.getChildren().add(new Label("Category:"));
        content2.getChildren().add(new Label("Destination:"));
        content2.getChildren().add(new Label("Delivered?"));
        content2.getChildren().add(new Label("priority:"));

        secondTitledPane.setContent(content2);

        Accordion root = new Accordion();
        root.getPanes().addAll(firstTitledPane, secondTitledPane);

        Scene scene = new Scene(root, 300, 200);
        stage.setTitle("UWGA Trip Monitoring System");
        stage.setScene(scene);
        stage.show();
    }
}



