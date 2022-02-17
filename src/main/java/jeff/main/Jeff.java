package jeff.main;

import jeff.command.Command;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.ui.Ui;

/**
 * Jeff class is a task manager with a variety of commands.
 * To see the full list of commands, check the readme.txt.
 */
public class Jeff {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Jeff class.
     *
     * @param filePath Path of saved file.
     */
    public Jeff(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JeffException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Get the response for Jeff to display in the GUI, response can be
     * either a confirmation that the intended task is done, or in the case an exception
     * was thrown, display what went wrong.
     *
     * @param input user input.
     * @return Jeff's response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (JeffException e) {
            return ui.showError(e.getMessage());
        }
    }
}
