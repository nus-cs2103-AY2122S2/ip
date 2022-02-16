package duke.modules;

/**
 * Represents any invalid command passed to the chatbot by the user.
 */
public class CommandUnknown extends Command {

    /**
     * Constructor for a CommandUnknown object.
     */
    public CommandUnknown() {

    }

    /**
     * Handles the execution of an invalid command.
     *
     * @return A String message telling the user that the command is invalid.
     */
    @Override
    public String execute() {
        return "invalid command! try 'help' for list of commands\n";
    }
}
