package seedu.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;

import java.io.IOException;

public class MarkCommand extends Command{
    public static String run(int taskId, TaskList tasksList, Storage storage) throws DukeException, IOException {
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
