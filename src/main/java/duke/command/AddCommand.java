package duke.command;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.task.Task;

public class AddCommand extends Command {
    Task task;
    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.task);
        storage.saveAddedTask(this.task);
        ui.print(this.task.printTask());
        return true;
    }
}