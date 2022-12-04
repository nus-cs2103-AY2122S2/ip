package duke.ui;

/**
 * Previously responsible for running the chatbot program,
 * now simply a container of Storage, TaskList and Ui variables.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException dukeError) {
            ui.showLoadingError(dukeError);
            tasks = new TaskList();
        }
    }

    /**
     * Returns the storage
     *
     * @return storage
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Returns taskList.
     *
     * @return taskList
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Returns Ui.
     *
     * @return Ui
     */
    public Ui getUi() {
        return ui;
    }
}
