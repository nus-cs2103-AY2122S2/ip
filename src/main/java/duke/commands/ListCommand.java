package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Lists out all tasks in the TaskList.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return "Your outstanding tasks as of now are as listed:\n" +
                tasks.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}