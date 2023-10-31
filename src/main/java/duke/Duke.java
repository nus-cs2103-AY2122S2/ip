package duke;

import duke.admin.Parser;
import duke.admin.Storage;
import duke.admin.TaskList;
import duke.admin.Ui;
import duke.commands.Command;
import duke.exceptions.DukeException;
/**
 * Duke class is the main class of the program.
 */
public class Duke {
    private static final String DEFAULT_FILE_PATH = "./dukeSaveLog.txt";
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor of Duke that uses a default file path as the specified location of the storage file.
     */
    public Duke() {
        storage = new Storage(DEFAULT_FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Constructor of Duke that takes in a file path specifying the location of the
     * storage file.
     * @param filePath string specifying location of storage file
     */
    public Duke(String filePath) {
        assert filePath != null;
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String fullCommand) throws DukeException {
        assert fullCommand != null;
        try {
            Command c = Parser.parse(fullCommand);
            String response = c.execute(tasks, storage);

            return response;
        } catch (DukeException e) {
            return Ui.showErrorMessage(e.getMessage());
        }

    }
}
