package juke.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Manages the graphical user interface using JavaFX.
 */
public class Gui {

    private Scene scene;
    private AnchorPane mainPane;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    /**
     * Initializes the components of the UI.
     *
     * @param stage Main stage.
     */
    public void initializeUiComponents(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainPane = new AnchorPane();
        mainPane.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainPane);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Formats the UI components to look as expected.
     *
     * @param stage Main stage.
     */
    public void formatUiComponents(Stage stage) {
        stage.setTitle("Juke");
        stage.setResizable(false);
        stage.setMinWidth(400.0);
        stage.setMinHeight(600.0);

        mainPane.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }
}
