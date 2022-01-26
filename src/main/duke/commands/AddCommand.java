package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class AddCommand extends Command {
    protected Task task;
    private static final String MESSAGE = "Got it. I've added this task:";

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.saveTaskList(tasks);
        ui.showMessage(MESSAGE + "\n  " + task.toString() + "\n" + tasks.printTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
