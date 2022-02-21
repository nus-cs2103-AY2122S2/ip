package seedu.command;

import java.io.IOException;

import seedu.exception.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;

public class MarkTaskCommand extends Command {
    private final int taskId;

    public MarkTaskCommand(String taskId) throws DukeException {
        assert taskId != null : "MarkTaskCommand->MarkTaskCommand: Task ID cannot be null.";
        assert taskId.length() > 0 : "MarkTaskCommand->MarkTaskCommand: Task ID cannot be empty.";

        try {
            this.taskId = Integer.valueOf(taskId);
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry your task ID is invalid, please try again!");
        }
    }

    public String run(TaskList tasksList, Storage storage) throws DukeException {
        assert tasksList != null : "MarkTaskCommand->run: Tasks list cannot be null.";
        assert storage != null : "MarkTaskCommand->run: Storage cannot be null.";

        tasksList.getTasks(taskId - 1).markDone();
        try {
            storage.write(tasksList.getTaskList());
        } catch (IOException e) {
            throw new DukeException("Something went wrong when I tried to write your task list back to storage :(");
        }

        String result = "Nice! I've marked this task as done:\n";
        result += tasksList.getTasks(taskId - 1).toString();
        return result;
    }
}
