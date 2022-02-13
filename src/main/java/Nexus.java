import component.Storage;
import component.TaskList;
import component.Ui;

/**
 *  A class that encapsulates Nexus the logic of  - A todo list program
 *  that helps user keep track of tasks.
 */
public class Nexus {
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
     * Constructs Nexus
     */
    public Nexus() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Initialises the Nexus Ui.
     */
    public String initUi() {
        return ui.initUi();
    }

    /**
     * Gets the response from Nexus with the userInput.
     * @param userInput User input in the form of a string.
     * @return String showing the user input.
     */
    public String getResponse(String userInput) {
        //runs the ui to retrieve the output based on userInput.
        return ui.generateNexusReply(tasks, storage, userInput);
    }
}
