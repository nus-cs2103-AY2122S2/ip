package duke;

import java.io.IOException;
import duke.commands.Command;
import duke.data.TaskList;
import duke.data.TasksEditor;
import duke.parser.Parser;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private Storage storage;
    private TasksEditor tasksEditor;
    private Ui ui;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        parser = new Parser();
        try {
            tasksEditor = new TasksEditor(new TaskList(storage.load()));
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasksEditor = new TasksEditor(new TaskList());
        }
    }

    /**
     * Executes the command and provides feedback to user.
     *
     * @param input The command to run.
     * @return The response message.
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(tasksEditor, ui, storage);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}
