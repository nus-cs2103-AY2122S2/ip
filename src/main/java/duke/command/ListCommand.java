package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class ListCommand extends Command {
    private static final boolean IS_EXIT = false;

    public ListCommand() {
        super(IS_EXIT);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            return TextUi.showTasks(tasks);
        } catch (DukeException e) {
            return TextUi.showError(e.getMessage());
        }
    }
}
