package duke;

import duke.command.Command;
import duke.utils.CortanaException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Duke the todolist helper, it's actual name is Cortana in this particular implementation.
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private boolean isReturnError = false;

    /**
     * Instantiates a new Duke.
     *
     * @param filePath the file path for storing/retrieving the todolist
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        assert storage.loadFile() != null;
        tasks = new TaskList(storage.loadFile());
    }

    public String getResponse(String input) {
        try {
            setIsReturnError(false);
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (CortanaException e) {
            setIsReturnError(true);
            return ui.showErrorMessage(e.getMessage());
        }
    }

    public boolean getIsReturnError() {
        return isReturnError;
    }

    public void setIsReturnError(boolean b) {
        isReturnError = b;
    }
}
