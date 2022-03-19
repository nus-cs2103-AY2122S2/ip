import java.io.IOException;

public class MarkCommand extends Command{

    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.mark(taskIndex);
        ui.showMark(tasks.get(taskIndex));
        storage.writeTasks(tasks);
    }
}
