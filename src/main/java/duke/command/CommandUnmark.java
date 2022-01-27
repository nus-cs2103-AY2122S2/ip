package duke.command;
import duke.TaskList;
import duke.Ui;
public class CommandUnmark extends Command {
    TaskList taskList;
    int taskNo;

    public CommandUnmark(TaskList taskList, int taskNo) {
        this.taskList = taskList;
        this.taskNo = taskNo;
    }

    @Override
    public void execute() {
        String change = taskList.unmarkFinished(taskNo);
        Ui.wrapPrint(change);
    }
}
