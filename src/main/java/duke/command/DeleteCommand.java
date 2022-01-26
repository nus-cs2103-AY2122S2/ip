package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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
