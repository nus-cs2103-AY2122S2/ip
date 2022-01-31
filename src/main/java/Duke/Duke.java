package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.DukeException;
import duke.ui.Ui;
import javafx.scene.image.Image;

/**
 * Represent the main class of the program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for Duke.
     *
     * @param filePath the directory of save file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns the response base on the input.
     */
    public String getResponse(String input) {
        try {
            ui.showLine(); // show the divider line ("_______")
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException | IOException e) {
            return ui.showError(e.getMessage());
        }
    }
}
