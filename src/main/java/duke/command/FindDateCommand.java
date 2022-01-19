package duke.command;

import java.io.IOException;
import java.time.LocalDate;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Represents command to find all the tasks in the task list that matches a date.
 *
 */
public class FindDateCommand extends Command {
    private LocalDate keyword;

    public FindDateCommand(String keyword) {
        this.keyword = LocalDate.parse(keyword);
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
