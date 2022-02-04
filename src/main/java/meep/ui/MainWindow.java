package meep.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import meep.commands.ExitCommand;
import meep.exception.InvalidInputException;


/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends BorderPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Gui gui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image meepImage = new Image(this.getClass().getResourceAsStream("/images/meep.jpeg"));


    /**
     * Init.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setMeep(Gui d) {
        gui = d;
        try {
            gui.setup();
            dialogContainer.getChildren().addAll(
                    DialogBox.getMeepDialog(Messages.MESSAGE_HI, meepImage),
                    DialogBox.getMeepDialog(Messages.MESSAGE_INSTRUCTIONS, meepImage),
                    DialogBox.getMeepDialog(gui.showTasks(), meepImage)
            );
        } catch (InvalidInputException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getMeepDialog(e.getMessage(), meepImage)
            );
        }

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Gui's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InvalidInputException {
        String input = userInput.getText();
        String response = gui.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog("\n" + input + "         \n", userImage),
                DialogBox.getMeepDialog(response, meepImage)
        );

        userInput.clear();

        if (isExit(response)) {
            boolean isSuccess = gui.saveTaskToFile();
            Platform.exit();
            System.exit(0);
        }
    }

    /**
     * Checks data file existence.
     */
    private boolean isExit(String response) {
        return response.equals(ExitCommand.MESSAGE_EXIT);
    }
}
