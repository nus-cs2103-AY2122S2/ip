package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.TaskList;
import seedu.duke.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return taskList.unmark(this.index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
