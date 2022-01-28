package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.unmark(index);
        ui.showUnmark(tasks.get(index));
    }

    public boolean isExit() {
        return false;
    }
}
