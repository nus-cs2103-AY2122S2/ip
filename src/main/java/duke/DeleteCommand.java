package duke;

import duke.Command;

public class DeleteCommand extends Command {
    private final int index;

    DeleteCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.index < 0 || this.index > taskList.length()) {
            ui.showOutOfBounds();
        } else {
            Task task = taskList.getTask(index);
            taskList.delete(index);
            ui.showDelete(task, taskList.getSize());
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
