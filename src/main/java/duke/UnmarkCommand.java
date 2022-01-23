package duke;

import duke.Command;
import duke.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    UnmarkCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.unmark(index);
        ui.showUnmark(taskList.getTask(index));
    }

    @Override
    boolean isExit() {
        return false;
    }
}
