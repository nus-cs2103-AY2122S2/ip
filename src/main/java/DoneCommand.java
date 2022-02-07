import java.io.IOException;

public class DoneCommand extends Command {
    private final int taskNum;

    /**
     *
     * @param taskNum
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.markDone(this.taskNum - 1);
        storage.writeTasks(taskList);
        ui.printMarkDone(taskList.getTasks().get(this.taskNum - 1));
    }
}