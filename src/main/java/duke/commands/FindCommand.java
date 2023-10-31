package duke.commands;

import duke.admin.Storage;
import duke.admin.TaskList;
import duke.exceptions.DukeException;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand that takes in a keyword to be used to filter the matching results.
     * @param keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (keyword == null) {
            throw new DukeException(DukeException.INVALID_FORMAT);
        }

        return tasks.find(keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
