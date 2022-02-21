package seedu.command;

import java.io.IOException;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.exception.DukeException;

public class DeleteTaskCommand extends Command {
    private final int taskId;

    public DeleteTaskCommand(String taskId) throws DukeException{
        assert taskId != null : "DeleteTaskCommand->DeleteTaskCommand: Task ID cannot be null.";
        assert taskId.length() > 0 : "DeleteTaskCommand->DeleteTaskCommand: Task ID cannot be empty.";

        try {
            this.taskId = Integer.valueOf(taskId);
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry your task ID is invalid, please try again!");
        }
    }

        /**
         * Executes the delete command.
         *
         * @return Output message for GUI.
         */
    public String run(TaskList tasksList, Storage storage) throws DukeException {
        assert tasksList != null : "DeleteTaskCommand->run: Tasks list cannot be null.";
        assert storage != null : "DeleteTaskCommand->run: Storage cannot be null.";

        String result = "Noted. I've removed this task:\n";
        result += tasksList.getTasks(taskId - 1).toString();
        tasksList.delete(taskId - 1);
        result += "\nNow you have ";
        result += tasksList.size();
        result += " tasks in the list.";
        try {
            storage.write(tasksList.getTaskList());
        } catch (IOException e) {
            throw new DukeException("Something went wrong when I tried to write your task list back to storage :(");
        }
        return result;
    }
}
