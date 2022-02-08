package ultoi.command;

import ultoi.util.Storage;
import ultoi.util.TaskList;
import ultoi.util.UltoiException;
import ultoi.util.UltoiUi;

/**
 * Represents a find command.
 *
 * @author snoidetx
 * @version 0.1.0
 */
public class FindCommand implements Command {
    private static final int COMMAND_LENGTH = 4;
    private static final String MESSAGE = "Got it! I have found the following matching tasks:";

    private final String keyword;

    /**
     * Creates a new FindCommand object.
     *
     * @param input String input from user.
     * @throws UltoiException If the put cannot be identified.
     */
    public FindCommand(String input) throws UltoiException {
        try {
            this.keyword = input.substring(this.COMMAND_LENGTH + 1);
        } catch (Exception e) {
            throw UltoiException.commandMismatchException();
        }
    }

    /**
     * Returns the response message to the user input.
     *
     * @param ui User interface to be used.
     * @param tasks Task list to be used.
     * @param storage Storage to be used.
     * @return String representation of the response message.
     * @throws UltoiException If any UltoiException happens.
     */
    public String execute(UltoiUi ui, TaskList tasks, Storage storage) throws UltoiException {
        return ui.showMsg(generateMsg(tasks));
    }

    /**
     * Generates a message for the command.
     *
     * @param tasks Task list.
     * @return Message for the command.
     */
    private String generateMsg(TaskList tasks) {
        return this.MESSAGE + "\n"
                + tasks.findTasksWith(this.keyword).toString();
    }
}
