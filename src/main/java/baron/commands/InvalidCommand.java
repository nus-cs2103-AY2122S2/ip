package baron.commands;

import baron.exceptions.BaronException;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {

    /**
     * Returns a warning that Baron is unable to understand the command.
     *
     * @return a warning that Baron is unable to understand the command.
     */
    @Override
    public String execute() {
        return (new BaronException("I'm sorry, but I beg your pardon? :-(")).toString();
    }
}
