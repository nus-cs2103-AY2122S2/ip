package duke;

import duke.command.Command;
import duke.helptool.DukeException;
import duke.helptool.Parser;
import duke.helptool.Storage;
import duke.helptool.TaskList;
import duke.helptool.Ui;

/**
 * The type Duke.
 */
public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Instantiates a new Duke with file storage at filePath.
     *
     * @param filePath the file path
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showExceptionError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Gets response.
     *
     * @param input the input
     * @return the response
     */
    public String getResponse(String input) {
        String result = "";
        try {
            Command c = Parser.parse(input);
            if (c != null) {
                result = c.execute(tasks, ui, storage);
            }
        } catch (DukeException e) {
            result = ui.showExceptionError(e);
        }
        return result;
    }

    /**
     * Is error boolean.
     *
     * @param input the input
     * @return the boolean
     */
    public Boolean isError(String input) {
        boolean result = false;
        try {
            Parser.parse(input);
            return false;
        } catch (DukeException e) {
            return true;
        }
    }

}
