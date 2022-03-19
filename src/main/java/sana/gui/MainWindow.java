package sana.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sana.Sana;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Sana sana;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image sanaImage = new Image(this.getClass().getResourceAsStream("/images/DaSana.png"));

    /**
     * Initializes the main window for the sana gui
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setSpacing(10);
    }

    public void setSana(Sana s) {
        sana = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sana.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, sanaImage)
        );
        userInput.clear();
        if (response.equals("See you next time!\n")) {
            exitProgram();
        }
    }

    private void exitProgram() {
        try {
            Thread.sleep(1000);
            System.exit(0);
        } catch (InterruptedException e) {
            exitProgram();
        }
    }
}
