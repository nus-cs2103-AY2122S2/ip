package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.mark(index);
        ui.showMark(tasks.get(index));
    }
}
