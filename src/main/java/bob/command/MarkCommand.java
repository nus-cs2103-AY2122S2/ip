package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.BobException;
import bob.task.Task;
/**
 * {@inheritDoc}
 */
public class MarkCommand extends Command {
    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    /**
     * {@inheritDoc}
     * Marks the task as done and updates the store.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        Task toMark = tasks.getTask(index);
        if (toMark.getStatus() == 1) {
            return ui.doneBefore();
        } else {
            StringBuilder reply = new StringBuilder();
            toMark.setStatus(1);
            assert toMark.getStatus() == 1: "Tasks status should be 1 but it is " + toMark.getStatus();
            reply.append(ui.finishTask() + "\n");
            reply.append(ui.printTask(toMark) + "\n");
            store.updateStore(tasks);
            return reply.toString();
        }
    }
}
