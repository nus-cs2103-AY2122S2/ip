package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

/**
 * Represents a command that unmarks the specified task as not done.
 */
public class UnmarkCommand extends Command {
    private static final String MESSAGE_TO_SHOW = "Meow! Task is not done!\n%s\n";
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.unmarkAsDone(index);
        return ui.showMessage(String.format(MESSAGE_TO_SHOW, tasks.getTask(index)));
    }
}
