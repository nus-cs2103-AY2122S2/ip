package duke;

import duke.command.Parser;
import duke.io.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/tom.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public VBox getDialogContainer() {
        return this.dialogContainer;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, (userImage))
        );
        int runResult = parser.run(userInput.getText(), ui, taskList);
        if (runResult == 1) {
            Storage.saveFile("data", "duke.txt", taskList.getList());
        }
        userInput.clear();
    }
}
