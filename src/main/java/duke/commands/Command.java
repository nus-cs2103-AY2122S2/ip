package duke.commands;


import duke.exceptions.InvalidOperationException;

/**
 * Skeletal implementation of a Command Object.
 */
public abstract class Command {

    /**
     * Checks and executes specific command.
     *
     * @throws InvalidOperationException if operation is invalid
     */
    public abstract void execute() throws InvalidOperationException;


}
