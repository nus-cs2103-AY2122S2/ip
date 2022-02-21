package commands;

import java.util.NoSuchElementException;

import tasklist.TaskList;
import tasklist.TaskListException;
import tasklist.TaskListException.TaskNotFoundException;
import tasks.Task;
import ui.Ui;

/**
 * Represents a user's instruction for the bot to mark a task as uncompleted.
 */
public class UnmarkCommand extends Command {
    /** Represents the command word to mark a task as uncompleted. */
    public static final String COMMAND = "unmark";

    private final int id;

    /**
     * Returns an UnmarkCommand object that can execute a user's instruction
     * to mark a task with a given identifier as uncompleted in the given
     * task list.
     *
     * @param id the identifier of the task to be marked as uncompleted in the task list.
     */
    public UnmarkCommand(int id) {
        this.id = id;
    }

    /**
     * Executes the marking of a given task by its identifier as uncompleted in
     * the given task list and provides a relevant interface for the whole process.
     *
     * @param ui       the interface to utilise for the current instruction.
     * @param taskList the tasks to operate on for the current instruction.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        try {
            final Task taskToUnmark = taskList.getById(this.id).get();
            taskToUnmark.markAsUndone();
            taskList.update(this.id, taskToUnmark);
            ui.showUnmarkTask(taskToUnmark);
        } catch (TaskNotFoundException | NoSuchElementException ex) {
            ui.showError("Task to unmark doesn't exist");
        } catch (TaskListException ex) {
            ui.showError(ex.getMessage());
        }
    }

    /**
     * The unmark command does not require an exit after its execution.
     *
     * @return Always returns false to indicate that an exit is not required after execution.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
