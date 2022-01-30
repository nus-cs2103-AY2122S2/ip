package apollo.commands;

/**
 * Default destination for unknown commands.
 * Extends {@code Command} superclass.
 */
public class InvalidCommand extends Command {

    /**
     * Sends message for unknown command.
     *
     * @return Message for successful execution.
     */
    @Override
    public String execute() {
        return "Apologies, I do not understand that. ";
    }
}
