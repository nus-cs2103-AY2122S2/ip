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
     * Executes the command.
     *
     * @param ui User interface to be used.
     * @param tasks Task list to be used.
     * @param storage Storage to be used.
     * @throws UltoiException If any Ultoi exception happens.
     */
    @Override
    public String execute(UltoiUi ui, TaskList tasks, Storage storage) {
        return ui.showMsg(tasks.toString());
    }
}