package seedu.command;

import java.io.IOException;

import seedu.exception.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;

public class UnmarkCommand extends Command {
    private final int taskId;

    public UnmarkCommand(String taskId) {
        assert taskId != null : "UnmarkCommand->UnmarkCommand: Task ID cannot be null.";

        this.taskId = Integer.valueOf(taskId);
    }

    public String run(TaskList tasksList, Storage storage) throws DukeException {
        assert tasksList != null : "UnmarkCommand->run: Tasks list cannot be null.";
        assert storage != null : "UnmarkCommand->run: Storage cannot be null.";

        tasksList.getTasks(taskId - 1).markUndone();
        try {
            storage.write(tasksList.getTaskList());
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }

        String result = "OK, I've marked this task as not done yet:\n";
        result += tasksList.getTasks(taskId - 1).toString();
        return result;
    }
}
