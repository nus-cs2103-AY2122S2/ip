package ultoi.command;

import ultoi.util.Storage;
import ultoi.util.TaskList;
import ultoi.util.UltoiException;
import ultoi.util.UltoiUi;

/**
 * Represents find command.
 *
 * @author: snoidetx
 * @version 0.1.0
 */
public class FindCommand implements Command {
    private static final int COMMAND_LENGTH = 4;
    private static final String MESSAGE = "Got it! I have found the following matching tasks:";

    private final String keyword;

    /**
     * Creates a find command.
     *
     * @param input User input.
     * @throws UltoiException If input is not valid.
     */
    public FindCommand(String input) throws UltoiException {
        try {
            this.keyword = input.substring(this.COMMAND_LENGTH + 1);
        } catch (Exception e) {
            throw UltoiException.commandMismatchException();
        }
    }

    /**
     * Executes the command.
     *
     * @param ui User interface.
     * @param tasks Task list.
     * @param storage Storage for writing to hard disk.
     * @return Response message.
     * @throws UltoiException If any Ultoi exception occurs.
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
