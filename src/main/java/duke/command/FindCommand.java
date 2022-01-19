package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents command to find all the tasks in the task list that matches a keyword.
 *
 */
public class FindCommand extends Command{
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) throws IOException {
        ui.printList(task, keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
