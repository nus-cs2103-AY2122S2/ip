package seedu.command;

import java.io.IOException;

import seedu.exception.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;

public class MarkCommand extends Command {
    private final int taskId;

    public MarkCommand(String taskId) {
        assert taskId != null : "MarkCommand->MarkCommand: Task ID cannot be null.";

        this.taskId = Integer.valueOf(taskId);
    }

    public String run(TaskList tasksList, Storage storage) throws DukeException {
        assert tasksList != null : "MarkCommand->run: Tasks list cannot be null.";
        assert storage != null : "MarkCommand->run: Storage cannot be null.";

        tasksList.getTasks(taskId - 1).markDone();
        try {
            storage.write(tasksList.getTaskList());
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }

        String result = "Nice! I've marked this task as done:\n";
        result += tasksList.getTasks(taskId - 1).toString();
        return result;
    }
}
