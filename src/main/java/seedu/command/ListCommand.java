package seedu.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;

import java.io.IOException;

public class ListCommand extends Command {
    public static String run(TaskList taskList) throws DukeException, IOException {
        String result = "Here are the tasks in your list:\n";
        result += taskList.toString();
        return result;
    }
}
