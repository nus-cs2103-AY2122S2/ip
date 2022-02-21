package seedu.command;

import seedu.duke.Storage;
import seedu.exception.DukeException;
import seedu.duke.TaskList;

public class FindTasksCommand extends Command {
    private final String keyword;

    public FindTasksCommand(String keyword) {
        assert keyword != null : "FindTasksCommand->FindTasksCommand: Keyword cannot be null.";
        assert keyword.length() > 0 : "FindTasksCommand->FindTasksCommand: Keyword cannot be empty.";

        this.keyword = keyword;
    }

    public String run(TaskList tasksList, Storage storage) throws DukeException {
        assert tasksList != null : "FindTasksCommand->run: Tasks list cannot be null.";
        assert storage != null : "FindTasksCommand->run: Storage cannot be null.";

        String result = "Here are the matching tasks in your list:\n";
        result += tasksList.findTasks(keyword).toString();
        return result;
    }
}
