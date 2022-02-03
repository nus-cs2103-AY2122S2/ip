package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super("delete");
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, StringBuilder response) {
        if (index < 0 || index >= tasks.size()) {
            response.append("Invalid index, please try again.");
        } else {
            Task task = tasks.remove(index);
            response.append(ui.taskDeleteMessage(task, tasks.size()));
            storage.save(tasks.list());
        }
    }
}

