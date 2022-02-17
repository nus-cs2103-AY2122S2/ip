package seedu.command;

import java.io.IOException;

import seedu.duke.Duke;
import seedu.duke.Storage;
import seedu.exception.DukeException;
import seedu.duke.TaskList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String run(TaskList tasksList, Storage storage) throws DukeException {
        String result = "Here are the matching tasks in your list:\n";
        result += tasksList.findTasks(keyword).toString();
        return result;
    }
}
