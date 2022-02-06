package duke;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;


/**
 * The Duke class is a chatbot that controls and manages your todo list application.
 */
public class Duke {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    TaskList tasks = new TaskList();

     public Duke() {
        try {
            tasks = Storage.readFile();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String enterCommand(String input) {
        Ui ui = new Ui(tasks);
        return ui.run(input);
    }

    public String getResponse(String input) {
        return enterCommand(input);
    }
}
