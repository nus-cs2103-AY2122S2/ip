package duke;

import duke.Command;

/**
 * Represents a command to delete tasks in the task list
 */
public class DeleteCommand extends Command {
    private final int index;

    DeleteCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.index < 0 || this.index >= taskList.length()) {
            return ui.outOfBoundsString();
        } else {
            Task task = taskList.getTask(index);
            taskList.delete(index);
            return ui.deleteString(task, taskList.getSize());
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
