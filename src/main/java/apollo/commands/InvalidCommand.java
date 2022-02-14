package apollo.commands;

import static apollo.messages.Messages.INVALID_COMMAND;

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
        return INVALID_COMMAND;
    }
}
