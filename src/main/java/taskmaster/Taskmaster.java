package taskmaster;

import taskmaster.commands.Commands;
import taskmaster.exception.DukeExceptions;
import taskmaster.userinterface.UserInterface;
import taskmaster.util.Parser;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;

/**
 * This class encapsulates the main program, Taskmaster.
 */

public class Taskmaster {
    /** The file path of the data file. **/
    private static final String FILE_PATH = "./data/Duke.txt";
    /** The user interface that allows the user to communicate with Taskmaster. **/
    protected UserInterface ui;
    /** The task list which contains the list of tasks. **/
    protected TaskList taskList;
    /** Storage object which handles storing and loading of tasks. **/
    protected Storage storage;
    /** True if bye command is executed. **/
    protected boolean isExit = false;

    /**
     * Constructor for Taskmaster the chatbot.
     */

    public Taskmaster () {
        this.ui = new UserInterface();
        this.taskList = new TaskList();
        this.storage = new Storage(FILE_PATH);
        try {
            storage.loadFile(taskList);
        } catch (DukeExceptions e) {
            ui.displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Returns
     */
    public String getOpeningMessage() {
        return ui.displayOpeningMessage();
    }

    /**
     * Returns True if the bye command has been performed.
     * @return Returns True if the bye command is performed.
     */

    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Returns a String response based on the user's input.
     * @param input string of the user input
     * @return String response based on given input.
     */
    public String getResponse(String input) {
        StringBuilder sb = new StringBuilder();
        try {
            Commands command = Parser.parse(input);
            sb.append(command.execute(this.taskList, this.ui, this.storage));
            isExit = command.isExit();

        } catch (DukeExceptions e) {
            sb.append(ui.displayErrorMessage(e.getMessage()));
        }
        return sb.toString();
    }
}
