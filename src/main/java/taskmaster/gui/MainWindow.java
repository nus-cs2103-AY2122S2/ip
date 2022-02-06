package taskmaster.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import taskmaster.Taskmaster;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    /** The Scroll Pane. **/
    @FXML
    private ScrollPane scrollPane;

    /** Vertical Box will be used for the dialog container. **/
    @FXML
    private VBox dialogContainer;

    /** The text field used for user's input. **/
    @FXML
    private TextField userInput;

    /** The button that will be used to send message. **/
    @FXML
    private Button sendButton;

    /** The taskmaster object that will be used in the program. **/
    private Taskmaster taskmaster;

    /** User's image. **/
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/lord.png"));

    /** task master's image. **/
    private Image taskMasterImage = new Image(this.getClass().getResourceAsStream("/images/alphamale.png"));

    /** Initializes the scroll pane. **/
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Sets the taskmaster for the program. **/
    public void setTaskmaster(Taskmaster t) {
        taskmaster = t;
    }


    /**
     *  Method to create a dialog container with the opening message.
     */
    @FXML
    public void greet() {
        dialogContainer.getChildren().addAll(
                DialogBox.getTaskmasterDialog(taskmaster.getOpeningMessage(), taskMasterImage)
        );
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = taskmaster.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTaskmasterDialog(response, taskMasterImage)
        );
        userInput.clear();
        if (taskmaster.isExit()) {
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Platform.exit();
            }).start();
        }
    }
}
