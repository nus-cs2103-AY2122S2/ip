package seedu.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;

import java.io.IOException;

public class UnmarkCommand extends Command{
    public static String run(int taskId, TaskList tasksList, Storage storage) throws DukeException, IOException {
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