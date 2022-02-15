package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

/**
 * Represents a command that marks the specified task as done.
 */
public class MarkCommand extends Command {
    private static final String MESSAGE_TO_SHOW = "Meow! Task is done!\n%s\n";
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markAsDone(index);
        return ui.showMessage(String.format(MESSAGE_TO_SHOW, tasks.getTask(index)));
    }
}
