package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;

public class ExitCommand extends Command {

    public void execute(TaskList taskList, Storage storage) throws DukeException {
    }

    @Override
    public boolean isExit() {
        return !super.isExit();
    }
}
