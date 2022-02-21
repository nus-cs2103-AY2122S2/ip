package duke.command;

/**
 * Represents the 'bye' command that shuts off Duke.
 */
public class CommandBye extends Command {

    /**
     * Creates a new CommandBye instance.
     */
    public CommandBye() {
    }

    /**
     * Execution of this command.
     */
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public String getResponse() {
        return null;
    }
}
