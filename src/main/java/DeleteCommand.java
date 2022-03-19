import java.io.IOException;

public class DeleteCommand extends Command {

    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showDelete(tasks.get(taskIndex), tasks);
        tasks.delete(taskIndex);
        storage.writeTasks(tasks);
    }
}
