package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.MessageUi;

/**
 * Represents the main class that Ekud will run on.
 */
public class Duke {

    private MessageUi ui;
    private Storage storage;
    private TaskList tasks;
    private boolean canLoad;

    /**
     * Constructor for Duke class.
     * @param filePath Directory of text file.
     * @throws DukeException
     */
    public Duke(String filePath) throws DukeException {
        ui = new MessageUi();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFileContents());
            canLoad = true;
        } catch (DukeException error) {
            tasks = new TaskList();
            storage.createNewFolderAndTextFile();
            canLoad = false;
        }
    }

    /**
     * If file can be successfully loaded into the task list, a successful loading
     * of file to task list will be shown to the user. Otherwise, a file not found
     * message will be shown to the user.
     * @return Successful file found message if file can be found, else unsuccessful file
     * found, and file creation message.
     */
    public String getFileLoadingMessage() {
        if (canLoad) {
            return ui.showFileFoundMessage(tasks);
        } else {
            assert !canLoad;
            return ui.showFileNotFoundMessage();
        }
    }

    public String getResponse(String input) throws DukeException {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage, ui);
        } catch (DukeException error) {
            throw error;
        }
    }
}
