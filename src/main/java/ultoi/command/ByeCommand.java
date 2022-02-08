package ultoi.command;

import ultoi.util.Storage;
import ultoi.util.TaskList;
import ultoi.util.UltoiException;
import ultoi.util.UltoiUi;

/**
 * Represents a command that ends the bot.
 *
 * @author snoidetx
 * @version 0.0.0
 */
public class ByeCommand implements Command {
    private static final String COMMAND_BYE = "bye";
    private static final String MESSAGE = "Bye. See you. <O_O>";

    /**
     * Creates an bye command.
     *
     * @param input User input.
     * @throws UltoiException If the input cannot be identified.
     */
    public ByeCommand(String input) throws UltoiException {
        if (!input.equals(COMMAND_BYE)) {
            throw UltoiException.commandMismatchException();
        }
    }

    /**
     * Executes the command.
     *
     * @param ui User interface to be used.
     * @param tasks Task list to be used.
     * @param storage Storage to be used.
     * @throws UltoiException If any Ultoi exception happens.
     */
    @Override
    public String execute(UltoiUi ui, TaskList tasks, Storage storage) {
        return ui.showMsg(this.MESSAGE);
    }
}
