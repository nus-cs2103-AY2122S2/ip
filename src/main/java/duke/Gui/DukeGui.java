package duke.Gui;

import duke.exceptions.DukeExceptions;
import duke.Ui;
import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * DukeGui is a class that manages and formats hwo the Graphical User Interface
 * is shown to the user
 */
public class DukeGui extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/bulbasaur.png")));
    private Image duke = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/pikachu.png")));


    public DukeGui() throws IOException {
    }

    @Override
    public void start(Stage stage) {

        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        this.formatWindow(stage, mainLayout, scrollPane);
        this.formatUserInput(stage);
    }
    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(Stage stage) throws IOException, DukeExceptions {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        if (userText.getText().equals("bye")) {
            stage.close();
        }
        dialogContainer.getChildren()
                .addAll(DukeDialogBox.getUserDialog(userText,
                                new ImageView(user)),
                        DukeDialogBox.getDukeDialog(dukeText,
                                new ImageView(duke)));
        userInput.clear();
    }

    /**
     * Formats the window shown to the user
     * @param stage
     * @param mainLayout
     * @param scrollPane
     */
    public void formatWindow(Stage stage, AnchorPane mainLayout, ScrollPane scrollPane) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

    }

    /**
     * Formats the GUI that deals with user input such as submit and click
     * @param stage
     */
    public void formatUserInput(Stage stage) {
        sendButton.setOnMouseClicked((event) -> {
            try{
                handleUserInput(stage);
            }
            catch (DukeExceptions e) {
            }
            catch (IOException e){
            }
        });

        userInput.setOnAction((event) -> {
            try{
                handleUserInput(stage);
            }
            catch (DukeExceptions e) {
            }
            catch (IOException e){
            }
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


        stage.setScene(scene);
        stage.show();
    }



}

