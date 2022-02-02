package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;
import duke.Ui;

import java.io.IOException;

public class DoneCommand extends Command {

    int taskId;

    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    public void execute(TaskList taskList, Storage storage) throws IOException, DukeException {
        if (taskList.isDone(taskId)) {
            throw new DukeException(Ui.MSG_TASKALREADYDONE);
        }
        else if (taskId > taskList.getTotalTasks() || taskId < 0) {
            throw new DukeException(Ui.MSG_INVALIDTASKID);
        } else {
            taskList.completeTask(taskId);
            Ui.print(Ui.completeTaskMsg(taskList.getTask(taskId).toString()));
            storage.writeToFile(taskList);
        }
    }
}
