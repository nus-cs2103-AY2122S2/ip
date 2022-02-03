package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an abstraction of sub-types.
 * A command is a parent to the different kinds of commands
 * a user can execute - todo/deadline/event/delete/exit/incorrect/list/mark/unmark.
 */
public abstract class Command {

    private static boolean isExit = false;

    /**
     * Default class constructor.
     */
    public Command() {

    }

    /**
     * Requires child classes to implement the method to ensure that every command has an executable function.
     * If an exception occurs, throws Duke Exception.
     *
     * @param tasks TaskList which stores the list of tasks
     * @param ui Ui to display necessary responses
     * @param storage Storage to perform caching features
     * @throws DukeException Throws exception related commands
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Updates the program exit status.
     *
     * @param state boolean to change the state of the program.
     */
    public static void setIsExit(boolean state) {
        isExit = state;
    }

    /**
     * Checks if the program should stop.
     *
     * @return exit state.
     */
    public boolean isExit() {
        return isExit;
    }
}
