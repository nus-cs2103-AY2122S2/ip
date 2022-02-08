package ultoi.command;

import ultoi.util.Storage;
import ultoi.util.TaskList;
import ultoi.util.UltoiException;
import ultoi.util.UltoiUi;

/**
 * Represents a sort command.
 *
 * @author snoidetx
 * @version 0.0.0
 */
public class SortCommand implements Command {
    private static final String COMMAND_SORT = "sort";

    /**
     * Creates a sort command.
     *
     * @param input User input.
     * @throws UltoiException If there are extra characters in user input.
     */
    public SortCommand(String input) throws UltoiException {
        if (!input.equals(COMMAND_SORT)) {
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
    public String execute(UltoiUi ui, TaskList tasks, Storage storage) throws UltoiException {
        tasks.sort();
        storage.save(tasks);
        return ui.showMsg(tasks.toString());
    }
}
