package ultoi.command;

import ultoi.task.Task;
import ultoi.task.ToDo;
import ultoi.task.Deadline;
import ultoi.task.Event;

import ultoi.util.Ultoi;
import ultoi.util.UltoiUi;
import ultoi.util.UltoiException;
import ultoi.util.Storage;
import ultoi.util.TaskList;
import ultoi.util.Parser;
import ultoi.util.DateTime;

/**
 * Represents a list command.
 *
 * @author snoidetx
 * @version 0.0.0
 */
public class ListCommand implements Command {
    private static final String COMMAND_LIST = "list";

    /**
     * Creates a list command.
     *
     * @param input User input.
     * @throws UltoiException If there are extra characters in user input.
     */
    public ListCommand(String input) throws UltoiException {
        if (! input.equals(COMMAND_LIST)) {
            throw UltoiException.commandMismatchException();
        }
    }

    /**
     * Show the list of tasks.
     *
     * @param ui ultoi.util.UltoiUi used.
     * @param tasks List of tasks.
     * @param storage ultoi.util.Storage used to access memory.
     */
    @Override
    public void execute(UltoiUi ui, TaskList tasks, Storage storage) {
        ui.showMsg(tasks.toString());
        return;
    }
}