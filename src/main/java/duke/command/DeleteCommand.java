package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.showDelete(tasks.delete(index));
        ui.showNumberOfTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
