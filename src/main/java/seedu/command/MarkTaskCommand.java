package seedu.command;

import java.io.IOException;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.exception.DukeException;

/**
 * Retrieves the task with the stated task ID to mark it as done (i.e. [X]).
 */
public class MarkTaskCommand extends Command {
    private final int taskId;

    public MarkTaskCommand(String taskId) throws DukeException {
        assert taskId != null : "MarkTaskCommand->MarkTaskCommand: Task ID cannot be null.";
        assert taskId.length() > 0 : "MarkTaskCommand->MarkTaskCommand: Task ID cannot be empty.";

        try {
            this.taskId = Integer.parseInt(taskId);
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry your task ID is invalid, please try again!");
        }
    }

    /**
     * Executes the mark task command to mark the task from the task list as done.
     * Writes the modified task list back to the storage location.
     *
     * @param taskList Current list of tasks.
     * @param storage Storage object to write tasks back to.
     * @return The task that has been marked as done if successfully run.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeException {
        assert taskList != null : "MarkTaskCommand->run: Task list cannot be null.";
        assert storage != null : "MarkTaskCommand->run: Storage cannot be null.";

        try {
            taskList.getTasks(taskId - 1).markDone();
            storage.write(taskList.getTaskList());
        } catch (IOException e) {
            throw new DukeException("Something went wrong when I tried to write your task list back to storage :(");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry your task ID is not valid, please try again! :(");
        }

        return "Nice! I've marked this task as done:\n" + taskList.getTasks(taskId - 1).toString();
    }
}
