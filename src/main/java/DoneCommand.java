import java.io.IOException;

public class DoneCommand extends Command {

    int taskId;

    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        if (taskList.isDone(taskId)) {
            throw new DukeException(ui.MSG_TASKALREADYDONE);
        }
        else if (taskId > taskList.getTotalTasks() || taskId < 0) {
            throw new DukeException(ui.MSG_INVALIDTASKID);
        } else {
            taskList.completeTask(taskId);
            ui.print(Ui.completeTaskMsg(taskList.getTask(taskId).toString()));
            storage.writeToFile(taskList);
        }
    }
}
