package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task, String[] commandArray) {
        super(commandArray);
        this.task = task;
    }
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        storage.save(taskList);
    }
}
