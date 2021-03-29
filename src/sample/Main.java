package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;


import java.awt.*;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("UWGA Trip Monitoring System");
    primaryStage.setScene(new Scene(root, 1000, 200));
    primaryStage.show();

    Accordion accordion = new Accordion();

    TitledPane pane1 = new TitledPane("Trip Information", this.createGridPane());
    TitledPane pane2 = new TitledPane("Item Information", new Label("Show all cars available"));
    accordion.getPanes().add(pane1);
    accordion.getPanes().add(pane2);

    VBox vBox = new VBox(accordion);
    Scene scene = new Scene(vBox);

    primaryStage.setScene(scene);

    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }


  public Label createNewLabel(String labelName) {
    Label label1 = new Label(labelName);
    return label1;
  }

  public TextField createNewTextField() {
    TextField textField = new TextField();
    textField.setDisable(true);
    return textField;
  }

  public GridPane createGridPane() {

    GridPane grd = new GridPane();
    grd.getColumnConstraints().add(new ColumnConstraints(300));
    grd.getColumnConstraints().add(new ColumnConstraints(300));

    grd.add(this.createNewLabel("ID:"), 0, 0);
    grd.add(this.createNewTextField(), 1, 0);

    grd.add(this.createNewLabel("Courier Name:"), 0, 1);
    grd.add(this.createNewTextField(), 1, 1);


    grd.add(this.createNewLabel("Priority:"), 0, 2);
    grd.add(this.createNewTextField(), 1, 2);
    grd.setPrefHeight(100);

    return grd;
  }
}
