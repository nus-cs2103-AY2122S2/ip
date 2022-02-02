package taskmaster.commands;

import taskmaster.exception.DukeExceptions;
import taskmaster.userinterface.UserInterface;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;


/*
 * This class encapsulates commands that executed
 * according to the user's input
 */

public abstract class Commands {
    protected String command;

    /**
     * Constructor for Command Objects.
     *
     * @param command - The command the user entered
     */

    public Commands(String command) {
        this.command = command;
    }


    /**
     * Execute the command.
     * @param tasklist The task list that contains the task.
     * @param ui The User interface.
     * @param storage The file that is storing the task information.
     * @return Returns a string confirmation that the task has been executed.
     * @throws DukeExceptions Throws an exception if execution fails.
     */

    public abstract String execute(TaskList tasklist, UserInterface ui, Storage storage) throws DukeExceptions;

    /**
     * Checks whether the command is a terminating
     * bye command.
     * @return true if it is a bye command.
     */
    public boolean isExit() {
        return this instanceof ByeCommands;
    }
}
