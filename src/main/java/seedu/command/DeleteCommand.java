package seedu.command;

import java.io.IOException;

import seedu.duke.Storage;
import seedu.duke.TaskList;

public class DeleteCommand extends Command {
    private final int taskId;

    public DeleteCommand(String taskId) {
        assert taskId != null : "DeleteCommand->DeleteCommand: Task ID cannot be null.";

        this.taskId = Integer.valueOf(taskId);
    }

        /**
         * Executes the delete command.
         *
         * @return Output message for GUI.
         */
    public String run(TaskList tasksList, Storage storage) {
        assert tasksList != null : "DeleteCommand->run: Tasks list cannot be null.";
        assert storage != null : "DeleteCommand->run: Storage cannot be null.";

        String result = "Noted. I've removed this task:\n";
        result += tasksList.getTasks(taskId - 1).toString();
        tasksList.delete(taskId - 1);
        result += "\nNow you have ";
        result += tasksList.size();
        result += " tasks in the list.";
        try {
            storage.write(tasksList.getTaskList());
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return result;
    }
}
