package sample;

import csc2a.uwga.file.TripFileHandler;
import csc2a.uwga.model.Item;
import csc2a.uwga.model.Trip;

import java.io.File;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TripPane extends StackPane {

    private final String dataDirectory = Paths.get("./data/trips").toAbsolutePath().normalize().toString();
    private Trip trip = null;
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
                            openFile(file, stackPane);
                        }
                    }
                });

        stackPane.getChildren().add(openButton);
        return scene;
    }


    private void openFile(File file, StackPane stackPane) {
        try {
            this.trip = TripFileHandler.readTrip(file);
            stackPane.getChildren().remove(0);
            //stackPane.getChildren().add(new Label(trip.getTripID()));
            stackPane.getChildren().add(AddAccordions());
        } catch (Exception ex) {
            System.out.println("Oops file not found");
        }
    }


    public VBox AddAccordions() {
        Accordion accordion = new Accordion();

        TitledPane pane1 = new TitledPane("Trip Information", this.createTripGridPane());
        TitledPane pane2 = new TitledPane("Item Information", this.createItemGridPane());
        accordion.getPanes().add(pane1);
        accordion.getPanes().add(pane2);
        VBox vBox = new VBox(accordion);
        return vBox;
    }


    public Label createNewLabel(String labelName) {
        Label label1 = new Label(labelName);
        return label1;
    }

    public TextField createNewTextField(String item) {
        TextField textField = new TextField();
        textField.setDisable(true);
        return textField;
    }

    public GridPane createTripGridPane() {

        GridPane grd = new GridPane();
        grd.getColumnConstraints().add(new ColumnConstraints(300));
        grd.getColumnConstraints().add(new ColumnConstraints(300));

        grd.add(this.createNewLabel("ID:"), 0, 0);
        grd.add(this.createNewTextField(this.trip.getTripID()), 1, 0);

        grd.add(this.createNewLabel("Courier Name:"), 0, 1);
        grd.add(this.createNewTextField(this.trip.getTripCourierName()), 1, 1);


        grd.add(this.createNewLabel("Priority:"), 0, 2);
        ProgressBar pb = new ProgressBar();
        pb.setProgress(this.trip.getTripPriority());
        grd.setPrefHeight(100);

        return grd;
    }

    public GridPane createItemGridPane(Item item) {

        GridPane grd = new GridPane();
        grd.getColumnConstraints().add(new ColumnConstraints(300));
        grd.getColumnConstraints().add(new ColumnConstraints(300));
            grd.add(this.createNewLabel("ID:"), 0, 0);
            grd.add(this.createNewTextField(item.getItemID()), 1, 0);

            grd.add(this.createNewLabel("Name:"), 0, 1);
            grd.add(this.createNewTextField(item.getItemName()), 1, 1);


            grd.add(this.createNewLabel("Category:"), 0, 2);
            grd.add(this.createNewTextField(item.getItemCategory()), 1, 2);

            grd.add(this.createNewLabel("Destination:"), 0, 3);
            grd.add(this.createNewTextField(item.getItemDestX(), item.getItemDestY()), 1, 3);

            grd.add(this.createNewLabel("Delivered?:"), 0, 4);
            grd.add(this.createNewTextField(item.isItemDelivered()), 1, 4);

            grd.add(this.createNewLabel("Priority:"), 0, 5);
            grd.add(this.createNewTextField(item.getItemPriority()), 1, 5);

            grd.setPrefHeight(100);

        return grd;
    }
}







