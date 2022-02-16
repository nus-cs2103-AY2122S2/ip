package duke.modules;

/**
 * Represents the command to close the chatbot
 */
public class CommandBye extends Command {

    /**
     * Constructor for a CommandBye object.
     */
    public CommandBye() {

    }

    /**
     * Handles the execution of a bye command.
     *
     * @return A String message regarding the execution status of the bye command.
     */
    @Override
    public String execute() {
        return "goodbye!\n";
    }
}
