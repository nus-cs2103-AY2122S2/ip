package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        super("unmark");
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid index, please try again.");
        } else {
            Task task = tasks.unmark(index);
            ui.taskUnmarkedMessage(task);
            storage.save(tasks.list());
        }
    }
}
