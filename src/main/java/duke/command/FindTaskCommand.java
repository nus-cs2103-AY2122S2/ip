package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class FindTaskCommand extends Command {
    private static final boolean IS_EXIT = false;
    private final String keywords;

    public FindTaskCommand(String keywords) {
        super(IS_EXIT);
        this.keywords = keywords;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        try {
            TaskList filteredTasks = tasks.filterTask(keywords);
            for (int i = 0; i < filteredTasks.getSize(); i++) {
                ui.showToUser((i + 1) + ". " + filteredTasks.getTask(i));
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}