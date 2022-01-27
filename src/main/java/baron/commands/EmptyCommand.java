package baron.commands;

import baron.exceptions.BaronException;

/**
 * Represents an empty command.
 */
public class EmptyCommand extends Command {

    /**
     * Returns the prompt to request a command.
     *
     * @return the prompt to request a command.
     */
    @Override
    public String execute() {
        return (new BaronException("Please enter a command!")).toString();
    }
}
