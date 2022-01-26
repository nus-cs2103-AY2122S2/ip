import java.io.IOException;

public class DeleteCommand extends Command {
    int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        if (taskId > taskList.getTotalTasks() || taskId < 0) {
            throw new DukeException(ui.MSG_INVALIDTASKID);
        } else {
            Task task = taskList.getTask(taskId);
            int totalTask = taskList.deleteTask(taskId);
            ui.print((Ui.deleteTaskMsg(task.toString(), totalTask + 1)));
            storage.writeToFile(taskList);
        }
    }
}
