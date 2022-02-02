package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class FindCommand extends Command {
    private final String findTask;

    public FindCommand(String findTask) {
        this.findTask = findTask;
    }

    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String printStr = taskList.find(findTask);
        ui.print(printStr);
        return true;
    }
}
