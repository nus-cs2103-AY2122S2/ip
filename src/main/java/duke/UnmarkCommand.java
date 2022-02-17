package duke;

/**
 * Represents a command to unmark a task
 */
public class UnmarkCommand extends Command {
    private final int index;

    UnmarkCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index > taskList.length()) {
            throw new OutOfBoundsException("Out of bounds!");
        } else {
            taskList.unmark(index);
            return ui.unmarkString(taskList.getTask(index));
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
