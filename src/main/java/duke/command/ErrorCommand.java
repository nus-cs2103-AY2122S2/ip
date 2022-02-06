package duke.command;

/**
 * A class representing an incorrect command.
 */
public class ErrorCommand extends Command {
    private final String message;

    public ErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public CommandResult runCommand() {
        return new CommandResult(message);
    }
}
