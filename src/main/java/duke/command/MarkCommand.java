package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        super("mark");
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, StringBuilder response) {
        if (index < 0 || index >= tasks.size()) {
            response.append("Invalid index, please try again.");
        } else {
            Task task = tasks.mark(index);
            response.append(ui.taskMarkedMessage(task));
            storage.save(tasks.list());
        }
    }
}
