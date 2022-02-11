import component.Storage;
import component.TaskList;
import component.Ui;

/**
 *  A class that encapsulates the logic of Duke - A todo list program
 *  that helps user keep track of tasks.
 */
public class Duke {
    /**
     * Default path name to retrieve the data from previous instance of duke.
     */
    private static final String DEFAULT_STORAGE_PATH_NAME = "data/duke.txt";
    /**
     * {@link Storage}
     */
    private final Storage storage;
    /**
     * {@link TaskList}
     */
    private final TaskList tasks;

    /**
     * {@link Ui}
     */
    private final Ui ui;

    /**
     * Constructor for Duke
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(DEFAULT_STORAGE_PATH_NAME);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Initialises the Duke Ui.
     */
    public String initUi() {
        return ui.initUi();
    }

    /**
     * Gets the response from duke with the userInput.
     * @param userInput User input in the form of a string.
     * @return String showing the user input.
     */
    public String getResponse(String userInput) {
        //runs the ui to retrieve the output based on userInput.
        return ui.generateDukeReply(tasks, storage, userInput);
    }
}
