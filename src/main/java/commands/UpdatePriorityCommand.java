package commands;

import java.util.NoSuchElementException;

import tasklist.TaskList;
import tasklist.TaskListException;
import tasks.Task;
import tasks.TaskPriority;
import ui.Ui;

/**
 * Represents a user's instruction for the bot to change the priority of a task.
 */
public class UpdatePriorityCommand extends Command {
    /** Represents the command word to change the priority of a task to LOW. */
    public static final String SET_LOW_COMMAND = "low";
    /** Represents the command word to change the priority of a task to MEDIUM. */
    public static final String SET_MEDIUM_COMMAND = "medium";
    /** Represents the command word to change the priority of a task to HIGH. */
    public static final String SET_HIGH_COMMAND = "high";

    private final int id;
    private final TaskPriority newPriority;

    /**
     * Returns an UpdatePriorityCommand object that can execute a user's
     * instruction to change the priority of a task with a given identifier
     * in the given task list.
     *
     * @param id the identifier of the task to change the priority for.
     * @param newPriority the priority to change to for a task.
     */
    public UpdatePriorityCommand(int id, TaskPriority newPriority) {
        this.id = id;
        this.newPriority = newPriority;
    }

    /**
     * Executes the changing of priority for a task with a given identifier
     * in the given task list and provides a relevant interface for the whole
     * process.
     *
     * @param ui       the interface to utilise for the current instruction.
     * @param taskList the tasks to operate on for the current instruction.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        try {
            final Task taskToUpdate = taskList.getById(this.id).get();
            taskToUpdate.setPriority(this.newPriority);
            taskList.update(this.id, taskToUpdate);
            ui.showUpdatePriority(taskToUpdate);
        } catch (TaskListException.TaskNotFoundException | NoSuchElementException ex) {
            ui.showError("Task to change priority for doesn't exist");
        } catch (TaskListException ex) {
            ui.showError(ex.getMessage());
        }
    }

    /**
     * The update priority command does not require an exit after its execution.
     *
     * @return Always returns false to indicate that an exit is not required after execution.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
