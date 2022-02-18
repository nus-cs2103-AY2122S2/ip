package seedu.command;

import seedu.duke.Storage;
import seedu.exception.DukeException;
import seedu.duke.TaskList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        assert keyword != null : "FindCommand->FindCommand: Keyword cannot be null.";

        this.keyword = keyword;
    }

    public String run(TaskList tasksList, Storage storage) throws DukeException {
        assert tasksList != null : "FindCommand->run: Tasks list cannot be null.";
        assert storage != null : "FindCommand->run: Storage cannot be null.";

        String result = "Here are the matching tasks in your list:\n";
        result += tasksList.findTasks(keyword).toString();
        return result;
    }
}
