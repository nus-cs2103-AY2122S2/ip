package duke;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.MainWindow;
import duke.ui.Ui;

/**
 * Represents a Duke chatbot object.
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Creates a new Duke chatbot
     */
    public Duke() {
        try {
            ui = new Ui();

            storage = new Storage("/ip/data");
            assert storage != null : "storage should exist";

            tasks = new TaskList(storage.load());
            assert tasks != null : "tasklist should exist";
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Sets the ui, tasks and storage into the main window.
     *
     * @param window the window to be shown to the user
     */
    public void run(MainWindow window) {
        window.setUi(ui);
        window.setTaskList(tasks);
        window.setStorage(storage);
    }
}
