package duke;

/**
 * Represents an exit command, terminating the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an instance of the ExitCommand class.
     *
     * @param command A string containing the word "bye".
     */
    public ExitCommand(String command) {
        super(command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }
}
