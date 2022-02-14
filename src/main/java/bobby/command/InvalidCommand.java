package bobby.command;

import bobby.Storage;
import bobby.Ui;
import bobby.exception.InvalidCommandException;
import bobby.task.TaskList;

/**
 * Represents a command that Bobby cannot understand
 */
public class InvalidCommand extends Command {
    /**
     * Creates an InvalidCommand object.
     */
    public InvalidCommand() {
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks TaskList object containing a list of Tasks.
     * @param ui Ui object to allow for Bobby to print messages.
     * @param storage Storage object that handles the reading/writing of TaskList into a specified file.
     * @return Nothing, throws an exception for parent class to handle.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        throw new InvalidCommandException("invalid");
    }

    /**
     * Overrides the default equals() method. Compares if 2 objects are of the same Command type.
     *
     * @param obj The other Command object to compare with.
     * @return True if both are InvalidCommand objects. False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof InvalidCommand;
    }
}
