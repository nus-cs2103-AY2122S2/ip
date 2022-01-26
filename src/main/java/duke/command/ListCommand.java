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
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        try {
            for (int i = 0; i < tasks.getSize(); i++) {
                ui.showToUser((i + 1) + ". " + tasks.getTask(i));
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
