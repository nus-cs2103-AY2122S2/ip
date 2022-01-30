package duke.command;

import duke.Response;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class CommandDelete extends Command {
    TaskList taskList;
    int taskNo;

    public CommandDelete(TaskList taskList, int taskNo) {
        this.taskList = taskList;
        this.taskNo = taskNo;
    }

    @Override
    public void execute() {
        Task deletee = taskList.deleteTask(taskNo);
        Ui.wrapPrint(Response.RESPONSE_DELETED + "\n" + deletee.toString() + "\n" + Response.taskNo(taskList.size()));
    }
}
