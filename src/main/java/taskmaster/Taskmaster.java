package taskmaster;

import taskmaster.commands.Commands;
import taskmaster.exception.DukeExceptions;
import taskmaster.userinterface.UserInterface;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;

import java.lang.StringBuilder;

/**
 * This class encapsulates the main program, Taskmaster.
 */

public class Taskmaster {
    /** The user interface that allows the user to communicate with Taskmaster. **/
    protected UserInterface ui;
    protected TaskList taskList;
    protected Storage storage;
    public boolean isExit = false;

    /**
     * Constructor for Taskmaster the chatbot.
     */

    public Taskmaster () {
        this.ui = new UserInterface();
        this.taskList = new TaskList();
        this.storage = new Storage();
        storage.loadFile(taskList);
    }

    public String getResponse(String input) {
        StringBuilder sb = new StringBuilder();
        try {
            Commands command = ui.performCommand(input, taskList);
            sb.append(command.execute(this.taskList, this.ui, this.storage));
            isExit = command.isExit();
        } catch (DukeExceptions e) {
            sb.append(ui.displayErrorMessage(e.getMessage()));
        } finally {
            return sb.toString();
        }
    }

}
