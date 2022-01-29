package duke;

import duke.command.Command;
import duke.helptool.DukeException;
import duke.helptool.Parser;
import duke.helptool.Storage;
import duke.helptool.TaskList;
import duke.helptool.Ui;

/**
 * The type Duke.
 *
 * @author Dai Tianle
 * @version 1.0
 */
public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Create a chatterbot with record file located at filePath
     *
     * @param filePath The file's storage path
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
     * Generate response text based on user input
     *
     * @param input the input
     * @return the response
     */
    public String getResponse(String input) {
        String tempResult = "";
        try {
            Command c = Parser.parse(input);
            if (c != null) {
                tempResult = c.execute(tasks, ui, storage);
            }
        } catch (DukeException e) {
            tempResult = ui.showExceptionError(e);
        }
        return tempResult;
    }

}
