package bobby.command;

import bobby.exception.BobbyException;
import bobby.Storage;
import bobby.task.TaskList;
import bobby.Ui;

/**
 * Represents the parent class of each command that Bobby can interpret.
 */
public abstract class Command {

    /**
     * Creates a Command object.
     */
    public Command() {
    }

    /**
     * Carries out the respective command's actions.
     *
     * @param tasks TaskList object containing a list of Tasks.
     * @param ui Ui object to allow for Bobby to print messages.
     * @param storage Storage object that handles the reading/writing of TaskList into a specified file.
     * @throws BobbyException if an invalid command is given by the user's input.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException;

    /**
     * Overrides the default equals() method. Compares if 2 objects are of the same Command type.
     *
     * @param obj The other Command object to compare with.
     * @return True if both are of the same Command type. False otherwise.
     */
    @Override
    public abstract boolean equals(Object obj);
}