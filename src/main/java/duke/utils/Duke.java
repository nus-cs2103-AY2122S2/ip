package duke.utils;

/**
 * Represents an instance of the task
 * manager
 */
public class Duke {

    /**
     * Instance of TaskList to store and
     * manage list of tasks.
     */
    private TaskList tl;


    /**
     * Constructor Method for Duke
     */
    public Duke() {
        tl = new TaskList(Storage.getSavedList());
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            return Parser.parse(input, this.tl);
        } catch (DukeException e) {
            return Ui.showError(e);
        }
    }

    public void saveListToDisk() {
        this.tl.saveListToStorage();
    }

}
