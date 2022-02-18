package duke.Gui;

import duke.Duke;
import duke.Exceptions.DukeException;
import duke.util.Ui;

import java.io.IOException;

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

/**
 * DukeGui is a class that manages and formats hwo the Graphical User Interface
 * is shown to the user
 */
public class DukeGui extends Application {

    private final Image userImage = new Image(this.getClass()
            .getResourceAsStream("/images/Doge.png"));
    private final Image dukeImage = new Image(this.getClass()
            .getResourceAsStream("/images/Cat.png"));
    private Duke duke = new Duke("data/duke.txt");
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public DukeGui() throws IOException {
    }

    @Override
    public void start(Stage stage) {

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
    private void handleUserInput(Stage stage) throws IOException, DukeException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(duke.executeCommand(userInput.getText()));
        if (userText.getText().equals("bye")) {
            stage.close();
        }
        dialogContainer.getChildren()
                .addAll(DukeDialogBox.getUserDialog(userText,
                                new ImageView(userImage)),
                        DukeDialogBox.getDukeDialog(dukeText,
                                new ImageView(dukeImage)));
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
        stage.setMinWidth(450.0);

        mainLayout.setPrefSize(450.0,
                600.0);
        scrollPane.setPrefSize(450,
                570);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(375.0);
        sendButton.setPrefWidth(70.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        Ui ui = new Ui();
        Label dukeText = new Label(ui.showWelcome());
        dialogContainer.getChildren()
                .addAll(DukeDialogBox.getDukeDialog(dukeText,
                        new ImageView(dukeImage)));
    }

    /**
     * Formats the GUI that deals with user input such as submit and click
     * @param stage
     */
    public void formatUserInput(Stage stage) {
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput(stage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DukeException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput(stage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DukeException e) {
                e.printStackTrace();
            }
        });

        dialogContainer.heightProperty()
                .addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.setScene(scene);
        stage.show();
    }
}