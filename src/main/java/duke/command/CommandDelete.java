package duke.command;

import duke.Response;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class CommandDelete extends Command {
    private final TaskList taskList;
    private final int taskNo;

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public CommandDelete(TaskList taskList, int taskNo) {
        this.taskList = taskList;
        this.taskNo = taskNo;
    }

    @Override
    public String execute() {
        Task deletee = taskList.deleteTask(taskNo);
        return Response.RESPONSE_DELETED + "\n" + deletee.toString() + "\n" + Response.taskNo(taskList.size());
    }
}
