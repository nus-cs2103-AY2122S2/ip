package duke.command;

/**
 * An abstract class representing a Command.
 */
public abstract class Command {

    /**
     * Executes the command and returns a {@code CommandResult}.
     *
     * @return {@code CommandResult} type containing the results from executing the command.
     */
    public abstract CommandResult runCommand();
}
