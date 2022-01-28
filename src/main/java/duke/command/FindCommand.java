package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String k) {
        keyword = k;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        TaskList filteredTasks = tasks.filter(keyword);
        ui.showFoundTaskList(filteredTasks);
    }
}
