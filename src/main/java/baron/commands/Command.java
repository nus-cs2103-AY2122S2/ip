package baron.commands;

/**
 * Represents a command given by user of Baron application, and it consists of one main
 * function: execute.
 */
public abstract class Command {

    /**
     * Returns true if this is a bye command.
     *
     * @return true if this is a bye command.
     */
    public boolean isByeCommand() {
        return false;
    }

    /**
     * Executes the command and returns a string.
     *
     * @return the command output of {@code String} type.
     */
    public abstract String execute();
}
