package duke;

/**
 * Represents a command to mark a task as completed
 */
public class MarkCommand extends Command {
    private final int index;

    MarkCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index >= taskList.length()) {
            throw new OutOfBoundsException("Out of bounds!");
        } else {
            taskList.mark(index);
            return ui.markString(taskList.getTask(index));
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
