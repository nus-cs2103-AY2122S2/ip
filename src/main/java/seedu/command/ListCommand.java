package seedu.command;

import java.io.IOException;

import seedu.duke.Storage;
import seedu.exception.DukeException;
import seedu.duke.TaskList;

public class ListCommand extends Command {

    public ListCommand() {
    }

    public String run(TaskList taskList, Storage storage) {
        String result = "Here are the tasks in your list:\n";
        result += taskList.toString();
        return result;
    }
}
