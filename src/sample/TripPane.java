package sample;

import csc2a.uwga.file.TripFileHandler;
import csc2a.uwga.model.Item;
import csc2a.uwga.model.Trip;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;

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
        TitledPane pane2 = new TitledPane("Item Information", this.createItemList());
        accordion.getPanes().add(pane1);
        accordion.getPanes().add(pane2);
        return new VBox(accordion);
    }


    public Label createNewLabel(String labelName) {
        return new Label(labelName);
    }

    public TextField createNewTextField(String item) {
        TextField textField = new TextField();
        textField.setText(item);
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

        grd.add(this.createProgressBar(this.trip.getTripPriority()), 1, 2);
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
        grd.add(this.createNewTextField(item.getItemCategory().toString()), 1, 2);

        grd.add(this.createNewLabel("Destination:"), 0, 3);
        grd.add(this.createNewTextField(String.format("%d, %d ", item.getItemDestX(), item.getItemDestY())), 1, 3);

        grd.add(this.createNewLabel("Delivered?:"), 0, 4);
        grd.add(this.createNewTextField(this.convertBool(item.isItemDelivered())), 1, 4);

        grd.add(this.createNewLabel("Priority:"), 0, 5);
        grd.add(this.createProgressBar(item.getItemPriority()), 1, 5);

        grd.setPrefHeight(180);

        return grd;
    }

    public ListView createItemList() {

        ObservableList<GridPane> listItems = FXCollections.observableArrayList();

        for (Item item : trip.getTripItems()) {

            listItems.add(this.createItemGridPane(item));
        }

        ListView listView = new ListView<GridPane>(listItems);

        listView.setMinHeight(1000);
        listView.setMouseTransparent( true );
        listView.setFocusTraversable( false );
        return listView;
    }

    public String convertBool(Boolean flag) {
        if (flag) {
            return "Yes";
        }
        return "No";
    }

    public ProgressBar createProgressBar(Integer itemPriority) {
        ProgressBar pb = new ProgressBar(0);
        pb.setProgress(itemPriority / 10F);
        return pb;
    }
}







