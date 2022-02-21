package duke.command;

/**
 * A class representing an incorrect command.
 */
public class ErrorCommand extends Command {
    private final String message;

    /**
     * Creates an ErrorCommand object.
     *
     * @param message Error message that will be displayed to the user.
     */
    public ErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public CommandResult runCommand() {
        return new CommandResult(message, false, true);
    }
}
