package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;

public class FindDateCommand extends Command{
    LocalDate keyword;

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
