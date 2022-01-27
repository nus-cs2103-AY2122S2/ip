package bob.command;

import bob.exception.BobException;
import bob.Storage;
import bob.Task.Task;
import bob.TaskList;
import bob.Ui;

public class MarkCommand extends Command {
    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        Task toMark = tasks.getTask(index);
        if (toMark.getStatus() == 1) {
            ui.doneBefore();
        } else {
            toMark.setStatus(1);
            ui.finishTask();
            ui.printTask(toMark);
            store.updateStore(tasks);
        }
    }
}
