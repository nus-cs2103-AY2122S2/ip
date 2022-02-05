package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.gui.Ui;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Duke is a personal assistant chatbot that helps a person to keep track of tasks.
 *
 * @author yl-ang
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object.
     *
     * @param filePath FilePath of the location of duke.txt.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.loadingErrorResponse();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String fullCommand) {

        String response;
        try {
            Command c = Parser.parse(fullCommand);
            response = c.execute(tasks, ui, storage);

        } catch (DukeException e) {
            response = ui.errorResponse(e.getMessage());
        }
        return response;
    }
}
