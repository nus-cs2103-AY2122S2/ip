package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;

public class FindCommand extends Command {

    private final String findTask;

    /**
     * Constructor for Find command.
     * @param findTask the task to be found.
     */
    public FindCommand(String findTask) {
        this.findTask = findTask;
    }

    @Override
    public String exec(TaskList taskList, Storage storage) throws DukeException {
        return taskList.find(findTask);
    }
}
