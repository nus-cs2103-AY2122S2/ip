package duke;

import java.io.FileNotFoundException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a chat-bot program named Duke.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke chat-bot program which initialises with a saved file (if applicable),
     * list of tasks, and system Ui.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("src/main/java/Duke/data/duke.txt");
        try { // Load existing task-list
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a default response after receiving user input.
     *
     * @param input User input.
     * @return Response.
     */
    public String getResponse(String input) throws DukeException {
        try {
            // Need to change this
            Command c = Parser.parseCommand(input);
            String output = c.execute(tasks, ui, storage);
            if (c instanceof ByeCommand) {
                return "exit";
            }
            return output;
        } catch (DukeException e) {
            throw new DukeException("I'm sorry matey, that's an invalid input. Please try again :'(\n");
        }
    }
}
