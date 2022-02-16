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
    public String execute(TaskList tasks, Storage storage) {
        try {
            TaskList filteredTasks = tasks.filterTask(keywords);
            return TextUi.showTasks(filteredTasks);
        } catch (DukeException e) {
            return TextUi.showError(e.getMessage());
        }
    }
}