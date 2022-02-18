package seedu.command;

import java.io.IOException;

import seedu.duke.Storage;
import seedu.exception.DukeException;
import seedu.duke.TaskList;

public class ListCommand extends Command {

    public ListCommand() {
    }

    public String run(TaskList tasksList, Storage storage) {
        assert tasksList != null : "ListCommand->run: Tasks list cannot be null.";
        assert storage != null : "ListCommand->run: Storage cannot be null.";

        try {
            storage.write(tasksList.getTaskList());
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }

        String result = "Here are the tasks in your list:\n";
        result += tasksList.toString();
        return result;
    }
}
