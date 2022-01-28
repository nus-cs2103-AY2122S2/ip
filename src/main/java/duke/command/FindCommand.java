package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class FindCommand extends Command {
    String findTask;

    public FindCommand(String findTask) {
        this.findTask = findTask;
    }

    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.find(findTask);
        return true;
    }
}
