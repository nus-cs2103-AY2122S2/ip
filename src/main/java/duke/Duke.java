package duke;

import duke.command.Command;
import duke.exception.BingChillingException;
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
     * @throws BingChillingException
     */
    public Duke(String filePath) throws BingChillingException {
        ui = new MessageUi();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFileContents());
            canLoad = true;
        } catch (BingChillingException error) {
            tasks = new TaskList();
            storage.createNewFolderAndTextFile();
            canLoad = false;
        }
    }

    /**
     * Returns a load successful message if task list is able to load data from file,
     * Otherwise, a file not found message will be shown to the user.
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

    public String getResponse(String input) throws BingChillingException {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage, ui);
        } catch (BingChillingException error) {
            throw error;
        }
    }
}
