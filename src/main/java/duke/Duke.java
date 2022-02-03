package duke;

import duke.command.Command;
import duke.logic.DukeException;
import duke.logic.Parser;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.Ui;

/**
 * Encapsulates the main Duke programme.
 */
public class Duke {
    /** Storage responsible for reading from and writing to local file. */
    private Storage storage;

    /** List of tasks stored at run-time. */
    private TaskList taskList;

    /** UI responsible for displaying messages to user. */
    private Ui ui;

    /**
     * Constructor for an instance of Duke.
     *
     * @param filePath Path at which local file is stored.
     */
    public Duke(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            this.taskList = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            ui.showError(e);
            this.taskList = new TaskList();
        }
    }

    /**
     * Boots up instance of Duke programme.
     */
    public void run() {
        this.ui.showIntro();
        boolean hasExited = false;

        do {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                hasExited = !command.execute(this.taskList, this.ui, this.storage);
            } catch (DukeException e) {
                ui.showError(e);
            }
        } while (!hasExited);

        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Duke("./data/saved.txt").run();
    }
}
