package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private final int markIndex;

    public MarkCommand(int markIndex) {
        super();
        this.markIndex = markIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks = tasks.set(markIndex, tasks.getByIndex(markIndex).mark());
            storage.saveTaskList(tasks);
            ui.showMessage("Nice! I've marked this task as done:\n        "
                    + tasks.getByIndex(markIndex));
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            ui.showInvalidIndex();
        }
    }
}
