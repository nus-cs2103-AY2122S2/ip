package seedu.command;

import java.io.IOException;

import seedu.exception.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;

public class UnmarkTaskCommand extends Command {
    private final int taskId;

    public UnmarkTaskCommand(String taskId) throws DukeException {
        assert taskId != null : "UnmarkTaskCommand->UnmarkTaskCommand: Task ID cannot be null.";
        assert taskId.length() > 0 : "UnmarkTaskCommand->UnmarkTaskCommand: Task ID cannot be empty.";

        try {
            this.taskId = Integer.valueOf(taskId);
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry your task ID is invalid, please try again!");
        }
    }

    public String run(TaskList tasksList, Storage storage) throws DukeException {
        assert tasksList != null : "UnmarkTaskCommand->run: Tasks list cannot be null.";
        assert storage != null : "UnmarkTaskCommand->run: Storage cannot be null.";

        tasksList.getTasks(taskId - 1).markUndone();
        try {
            storage.write(tasksList.getTaskList());
        } catch (IOException e) {
            throw new DukeException("Something went wrong when I tried to write your task list back to storage :(");
        }

        String result = "OK, I've marked this task as not done yet:\n";
        result += tasksList.getTasks(taskId - 1).toString();
        return result;
    }
}
