package duke;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {

    /**
     * Constructs an instance of the InvalidCommand class.
     *
     * @param command A string containing the original invalid command
     */
    public InvalidCommand(String command) {
        super(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return this.getFirstWord();
    }
}
