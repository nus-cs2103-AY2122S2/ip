package commands;

import tasklist.TaskList;
import tasklist.TaskListException;
import ui.Ui;

/**
 * Represents a user's instruction for the bot to list out all existing tasks.
 */
public class ListCommand extends Command {
    /** Represents the command word to list out all existing tasks. */
    public static final String COMMAND = "list";

    /**
     * Returns a ListCommand object that can execute a user's instruction
     * to list out all existing tasks from a task list.
     */
    public ListCommand() {}

    /**
     * Executes the listing out of all existing tasks in the given task list
     * and provides a relevant interface for the whole process.
     *
     * @param ui       the interface to utilise for the current instruction.
     * @param taskList the tasks to operate on for the current instruction.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        try {
            ui.showList(taskList.get());
        } catch (TaskListException ex) {
            ui.showError(ex.getMessage());
        }
    }

    /**
     * The list command does not require an exit after its execution.
     *
     * @return Always returns false to indicate that an exit is not required after execution.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
