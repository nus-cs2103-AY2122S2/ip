package baron.commands;

/**
 * Represents the bye/exit command.
 */
public class ByeCommand extends Command {

    /**
     * Returns true if this is a bye command.
     *
     * @return true if this is a bye command.
     */
    @Override
    public boolean isByeCommand() {
        return true;
    }

    /**
     * Returns an exit message.
     *
     * @return an exit message.
     */
    @Override
    public String execute() {
        return "Bye. Hope to see you again soon!";
    }
}
