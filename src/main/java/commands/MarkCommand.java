package commands;

import java.util.NoSuchElementException;

import tasklist.TaskList;
import tasklist.TaskListException;
import tasklist.TaskListException.TaskNotFoundException;
import tasks.Task;
import ui.Ui;

/**
 * Represents a user's instruction for the bot to mark a task as completed.
 */
public class MarkCommand extends Command {
    /** Represents the command word to mark a task as completed. */
    public static final String COMMAND = "mark";

    private final int id;

    /**
     * Returns a MarkCommand object that can execute a user's instruction
     * to mark a task with a given identifier as completed in the given
     * task list.
     *
     * @param id the identifier of the task to be marked as completed in the task list.
     */
    public MarkCommand(int id) {
        this.id = id;
    }

    /**
     * Executes the marking of a given task by its identifier as completed in
     * the given task list and provides a relevant interface for the whole process.
     *
     * @param ui       the interface to utilise for the current instruction.
     * @param taskList the tasks to operate on for the current instruction.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        try {
            final Task taskToMark = taskList.getById(this.id).get();
            taskToMark.markAsDone();
            taskList.update(this.id, taskToMark);
            ui.showMarkTask(taskToMark);
        } catch (TaskNotFoundException | NoSuchElementException ex) {
            ui.showError("Task to mark doesn't exist");
        } catch (TaskListException ex) {
            ui.showError(ex.getMessage());
        }
    }

    /**
     * The mark command does not require an exit after its execution.
     *
     * @return Always returns false to indicate that an exit is not required after execution.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
