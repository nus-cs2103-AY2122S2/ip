package seedu.command;

import java.io.IOException;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.exception.DukeException;

public class ListTasksCommand extends Command {

    public ListTasksCommand() {
    }

    public String run(TaskList tasksList, Storage storage) throws DukeException {
        assert tasksList != null : "ListTasksCommand->run: Tasks list cannot be null.";
        assert storage != null : "ListTasksCommand->run: Storage cannot be null.";

        try {
            storage.write(tasksList.getTaskList());
        } catch (IOException e) {
            throw new DukeException("Something went wrong when I tried to write your task list back to storage :(");
        }

        String result = "Here are the tasks in your list:\n";
        result += tasksList.toString();
        return result;
    }
}
