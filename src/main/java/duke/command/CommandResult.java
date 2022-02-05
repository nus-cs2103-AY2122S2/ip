package duke.command;

/**
 * Represents the completion status of the <code>Commands</code>. A <code>CommandResult</code>
 * corresponds to a result represented by a string e.g., <code>"Command successful!"</code>
 */
public class CommandResult {
    private final String commandResult;

    public CommandResult(String commandResult) {
        this.commandResult = commandResult;
    }

    /**
     * Returns the result after execution of the command.
     *
     * @return result executing the command.
     */
    public String toString() {
        return commandResult;
    }
}
