package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }
    public void execute(TaskList taskList, Storage storage) throws IOException, DukeException {
        if (taskId > taskList.getTotalTasks() || taskId < 0) {
            throw new DukeException(Ui.MSG_INVALIDTASKID);
        } else {
            Task task = taskList.getTask(taskId);
            int totalTask = taskList.deleteTask(taskId);
            Ui.print((Ui.deleteTaskMsg(task.toString(), totalTask + 1)));
            storage.writeToFile(taskList);
        }
    }
}
