package juke.ui;

import java.io.IOException;

import com.sun.tools.javac.Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import juke.command.Result;
import juke.ui.controller.MainWindow;

/**
 * Manages the graphical user interface using JavaFX.
 */
public class Gui {

    private Scene scene;
    private MainWindow mainWindow;

    /**
     * Initializes the components of the UI.
     *
     * @param stage Main stage.
     */
    public void initializeUiComponents(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
            this.mainWindow = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setGui(this);

            this.scene = new Scene(mainWindow);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats the UI components to look as expected.
     *
     * @param stage Main stage.
     */
    public void formatUiComponents(Stage stage) {
        /*stage.setTitle("Juke");
        stage.setResizable(false);
        stage.setMinWidth(400.0);
        stage.setMinHeight(600.0);

        this.mainWindow.setPrefSize(400.0, 600.0);

        this.scrollPane.setPrefSize(385, 535);
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        this.scrollPane.setVvalue(1.0);
        this.scrollPane.setFitToWidth(true);

        this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        this.userInput.setPrefWidth(325.0);

        this.sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);*/
    }

    /**
     * Handles the listeners for events.
     *
     * @param stage Main stage.
     */
    public void handleEventListeners(Stage stage) {
        /*this.sendButton.setOnMouseClicked((event) -> {
            this.handleUserInput();
        });

        this.userInput.setOnAction((event) -> {
            this.handleUserInput();
        });

        this.dialogContainer.heightProperty().addListener((observable) -> this.scrollPane.setVvalue(1.0));*/
    }

    public String getResponse(String input) {
        return ">>>>>> " + input;
    }

    /**
     * Prints the message associated with a success, or the error message otherwise.
     *
     * @param result Result of a command execution.
     */
    public String displayResult(Result result) {
        try {
            String[] string = result.getOrThrow();
            //this.formattedPrint(string);
            return String.join(System.lineSeparator(), string);
        } catch (Exception e) {
            //this.formattedPrint(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Prints a string with decorative formatting.
     *
     * @param texts Strings to print.
     */
    public void formattedPrint(String... texts) {
        this.mainWindow.addJukeDialog(String.join(System.lineSeparator(), texts));
    }
}
