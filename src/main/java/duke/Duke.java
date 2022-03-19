package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Duke is the main class that houses a 'run' command to call on other classes to process the user input.
 * It helps the user to record down tasks such as Events, Deadlines, and Todos.
 * It can also save the user's tasks into a text file, and load it up when the user interacts with Ducky again.
 *
 * @author Pun Hui Min
 */
public class Duke {
    private Storage storage;
    private String filename;
    private Ui ui;
    private TaskList tasks;
    private boolean isError;

    /**
     * Constructor method for Duke.
     *
     * @param filepath text file name. Typically called 'duke.txt'.
     */
    public Duke(String filepath) {
        filename = filepath;
        storage = new Storage(filepath);
        ui = new Ui();
        try {
            tasks = storage.readFile(filepath);
        } catch (DukeException e) {
            isError = true;
            ui.showError(e.getMessage());
        }
    }

    /**
     * Gets the response of Ducky upon user input.
     *
     * @param input User input in GUI (i.e. commands).
     * @return Ducky's response the user's input.
     */
    public String getResponse(String input) {
        try {
            Command c = new Parser(input).parse();
            String response = c.execute(tasks, ui, storage);
            storage.saveFile(tasks.formatTasks());
            isError = false;
            return response;
        } catch (DukeException e) {
            isError = true;
            return (e.getMessage());
        }
    }

    public boolean isError() {
        return isError;
    }
}
