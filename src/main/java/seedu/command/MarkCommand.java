package seedu.command;

import java.io.IOException;

import seedu.exception.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;

public class MarkCommand extends Command {
    private final int taskId;

    public MarkCommand(String taskId) {
        this.taskId = Integer.valueOf(taskId);
    }

    public String run(TaskList tasksList, Storage storage) throws DukeException {
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
