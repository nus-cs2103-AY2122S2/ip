package duke.ui;

import duke.Duke;
import duke.io.Storage;
import duke.parser.Parser;
import duke.task.TaskStore;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

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
    @FXML
    private Button sendButton;

//    private Duke duke;
    private TaskStore tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//    TODO: Change if got time
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public VBox getDialogContainer() {
        return this.dialogContainer;
    }

//    public void setDuke(Duke d) {
//        this.duke = d;
//    }

    public void setUi(Ui u) {
        this.ui = u;
    }

    public void setTasks(TaskStore tasks) {
        this.tasks = tasks;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void setParser(Parser p){
        this.parser = p;
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
//        String response = this.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
//                DialogBox.getDukeDialog(response, dukeImage)
        );
        boolean isSuccessful = parser.processInput(userInput.getText(),this.ui, this.tasks);

        if (isSuccessful) {
            this.storage.writeToFile(this.tasks);
        }

        userInput.clear();
    }

//    Temp - to be deleted
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
