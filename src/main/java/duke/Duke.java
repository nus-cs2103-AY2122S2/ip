package duke;

import duke.command.Command;
import duke.data.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Connects Ui Storage and TaskList Main class of the Duke application
     *
     * @param filePath the file location.
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
     * Generates a response to user input.
     */
    public String getResponse(String input) throws DukeException {
        try {
            String output;
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            output = ui.getOutput();
            if (c.isExit()) {
                return ui.getOutput();
            }
            assert output.equals("") : "Error empty output";
            return output;
        } catch (DukeException e) {
            throw new DukeException("User Input error");
        }
    }
}
