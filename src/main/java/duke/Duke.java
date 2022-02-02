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
    private boolean f;


    public Duke() {
    }

    /**
     *
     * @param filePath Directory of text file.
     * @throws DukeException
     */
    public Duke(String filePath) throws DukeException {
        ui = new MessageUi();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFileContents());
            f = true;
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public String getFileLoadingMessage() {
        if (f) {
            return ui.showFileFoundMessage(tasks);
        } else {
            return ui.showFileNotFoundMessage();
        }
    }

    public String getResponse(String input) throws DukeException {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage, ui);
        } catch (DukeException e) {
            throw e;
        }
    }
}
