package seedu.command;

import java.io.IOException;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.exception.DukeException;

/**
 * Retrieves the task with the stated task ID to mark it as undone (i.e. []).
 */
public class UnmarkTaskCommand extends Command {
    private final int taskId;

    public UnmarkTaskCommand(String taskId) throws DukeException {
        assert taskId != null : "UnmarkTaskCommand->UnmarkTaskCommand: Task ID cannot be null.";
        assert taskId.length() > 0 : "UnmarkTaskCommand->UnmarkTaskCommand: Task ID cannot be empty.";

        try {
            this.taskId = Integer.parseInt(taskId);
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry your task ID is invalid, please try again!");
        }
    }

    /**
     * Executes the unmark task command to unmark the task from the task list as undone.
     * Writes the modified task list back to the storage location.
     *
     * @param taskList Current list of tasks.
     * @param storage Storage object to write tasks back to.
     * @return The task that has been marked as undone if successfully run.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeException {
        assert taskList != null : "UnmarkTaskCommand->run: Tasks list cannot be null.";
        assert storage != null : "UnmarkTaskCommand->run: Storage cannot be null.";

        try {
            taskList.getTasks(taskId - 1).markUndone();
            storage.write(taskList.getTaskList());
        } catch (IOException e) {
            throw new DukeException("Something went wrong when I tried to write your task list back to storage :(");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry your task ID is not valid, please try again! :(");
        }

        return "OK, I've marked this task as not done yet:\n" + taskList.getTasks(taskId - 1).toString();
    }
}
