package duke;

import duke.command.Command;
import duke.ui.Ui;

/**
 * Represents Duke.
 */
public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;
    /**
     * Constructs a Duke object.
     * @param filePath The file path to where tasks should be saved.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Parses the user's input and generates the proper response.
     *
     * @param input The user's input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(storage, tasks, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
