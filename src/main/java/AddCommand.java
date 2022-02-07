import java.io.IOException;

public class AddCommand extends Command {
    private final Task toAdd;

    /**
     *
     * @param toAdd
     */
    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    /**
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTask(this.toAdd);
        storage.writeTasks(taskList);
        ui.printAddTaskMessage(this.toAdd, taskList);
    }
}