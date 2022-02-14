import java.io.IOException;

public class DeleteCommand extends Command {
    private final int taskNum;

    /**
     *
     * @param taskNum
     */
    public DeleteCommand(int taskNum) {

        this.taskNum = taskNum;
    }

    /**
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        assert this.taskNum > 0 : "Task number must be greater than zero";
        Task toDelete = taskList.getTasks().get(this.taskNum - 1);
        taskList.deleteTask(this.taskNum - 1);
        storage.writeTasks(taskList);
        return  ui.printDeleteTask(toDelete, taskList);
    }
}