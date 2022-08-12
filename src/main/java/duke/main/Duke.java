package duke.main;

import java.io.IOException;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class Duke {

    private VBox dialogContainer;
    // create the image, by getting its directory
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private String filePath = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog("No file found", duke));
            tasks = new TaskList();
        }

    }

    /**
     * Returns the response for the user input.
     * @throws IOException
     */
    public String getResponse(String input) throws IOException {
        Parser parser = new Parser(tasks);

        try {
            String commandType = parser.scanInput(input);
            if (commandType.equals("bye")) {
                // return a string value indicating what the command type is
                return commandType;
            } else {
                // parser will handle the commands
                return commandType;
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}