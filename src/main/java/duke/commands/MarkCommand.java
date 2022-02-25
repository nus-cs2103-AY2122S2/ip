package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command{
    private final int task;

    public MarkCommand(int task) {
        super();
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        try {
            tasks.markDone(task, true);
            ui.showMessage("OK. I've marked this task as done:");
            ui.showMessage(tasks.get(task).toString());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Please enter a valid task. Task " + this.task + " does not exist.");
        }
    }
}
