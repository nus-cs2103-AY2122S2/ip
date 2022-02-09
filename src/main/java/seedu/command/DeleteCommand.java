package seedu.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Task;
import seedu.duke.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {
    /**
     * Executes the delete command.
     *
     * @return Output message for GUI.
     */
    public static String run(int taskId, TaskList tasksList, Storage storage) throws DukeException, IOException {
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