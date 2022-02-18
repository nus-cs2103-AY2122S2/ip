package commands;

import java.util.Optional;

import tasklist.TaskList;
import tasklist.TaskListException;
import tasklist.TaskListException.TaskNotFoundException;
import tasks.Task;
import ui.Ui;

/**
 * Represents a user's instruction for the bot to remove an existing task.
 */
public class DeleteCommand extends Command {
    /** Represents the command word to remove an existing task. */
    public static final String COMMAND = "delete";

    private final int id;

    /**
     * Returns a DeleteCommand object that can execute a user's instruction
     * to delete a given task by its identifier from a task list.
     *
     * @param id the identifier of the task to delete from the task list.
     */
    public DeleteCommand(int id) {
        this.id = id;
    }

    /**
     * Executes the deletion of a given task by its identifier from the given
     * task list and provides a relevant interface for the whole process.
     *
     * @param ui       the interface to utilise for the current instruction.
     * @param taskList the tasks to operate on for the current instruction.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        try {
            final Optional<Task> taskToRemove = taskList.getById(this.id);
            taskList.remove(this.id);
            ui.showDeleteTask(taskToRemove.get(), taskList.size());
        } catch (TaskNotFoundException ex) {
            ui.showError("Task to remove doesn't exist");
        } catch (TaskListException ex) {
            ui.showError(ex.getMessage());
        }
    }

    /**
     * The delete command does not require an exit after its execution.
     *
     * @return Always returns false to indicate that an exit is not required after execution.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
