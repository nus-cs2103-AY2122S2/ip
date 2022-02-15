package mickey.app;

import mickey.command.Command;
import mickey.task.TaskList;

/**
 * Mickey task manager
 */
public class Mickey {

    /** Path of storage file. */
    private static final String filePath = "src/main/data/save.txt";

    /** Storage to save and load previous tasks. */
    private final Storage storage;

    /** TaskList to store tasks. */
    private TaskList tasks;

    /** Ui to generate terminal output. */
    private final Ui ui;

    /**
     * Constructor.
     */
    public Mickey() {
        ui = new Ui();
        storage = new Storage(Mickey.filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (MickeyException e) {
            return ui.showError(e.getMessage());
        }
    }
}
