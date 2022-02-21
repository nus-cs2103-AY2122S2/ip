package seedu.command;

import java.io.IOException;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.exception.DukeException;

/**
 * Deletes task from task list based on provided task ID.
 */
public class DeleteTaskCommand extends Command {
    private final int taskId;

    public DeleteTaskCommand(String taskId) throws DukeException {
        assert taskId != null : "DeleteTaskCommand->DeleteTaskCommand: Task ID cannot be null.";
        assert taskId.length() > 0 : "DeleteTaskCommand->DeleteTaskCommand: Task ID cannot be empty.";

        try {
            this.taskId = Integer.parseInt(taskId);
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry your task ID is invalid, please try again!");
        }
    }

    /**
     * Executes the delete command and writes the modified task list back to storage location.
     *
     * @param taskList Current list of tasks.
     * @param storage Storage object to write tasks back to.
     * @return Display message if the task has been deleted from the list successfully.
     * @throws DukeException  If task list cannot be written back to storage location.
     */
    public String run(TaskList taskList, Storage storage) throws DukeException {
        assert taskList != null : "DeleteTaskCommand->run: Task list cannot be null.";
        assert storage != null : "DeleteTaskCommand->run: Storage cannot be null.";

        String result = "Noted. I've removed this task:\n";

        try {
            result += taskList.getTasks(taskId - 1).toString();
            taskList.delete(taskId - 1);
            result += "\nNow you have " + taskList.size() + " tasks in the list.";
            storage.write(taskList.getTaskList());
        } catch (IOException e) {
            throw new DukeException("Something went wrong when I tried to write your task list back to storage :(");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry your task ID is not valid, please try again! :(");
        }

        return result;
    }
}
