package duke;

import duke.Command;

/**
 * Command to mark task as completed
 */
public class MarkCommand extends Command {
    private final int index;

    MarkCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index > taskList.length()) {
            throw new OutOfBoundsException("Out of bounds!");
        } else {
            taskList.mark(index);
            ui.showMark(taskList.getTask(index));
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
