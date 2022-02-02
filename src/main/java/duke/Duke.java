package duke;

import duke.command.Command;
import duke.command.SetupCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This class is the main controller of the application.
 */
public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Coordinates the interactions between various other classes to accomplish the logic
     * of the application.
     */
    public Duke() {
        storage = new Storage();
        taskList = new TaskList();
        ui = new Ui();
        new SetupCommand().execute(ui, taskList, storage);
    }

    public String getResponse() {
        return ui.getMessage();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(ui, taskList, storage);
        } catch (DukeException exception) {
            ui.showErrorMessage(exception.getMessage());
        }

        return getResponse();
    }
}
