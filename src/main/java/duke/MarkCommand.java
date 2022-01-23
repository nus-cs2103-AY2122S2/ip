package duke;

public class MarkCommand extends Command {
    private final int index;

    MarkCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        if (index > taskList.length()) {
            ui.showOutOfBounds();
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
