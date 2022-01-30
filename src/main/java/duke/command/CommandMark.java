package duke.command;

import duke.TaskList;
import duke.Ui;

public class CommandMark extends Command {
    TaskList taskList;
    int taskNo;

    public CommandMark(TaskList taskList, int taskNo) {
        this.taskList = taskList;
        this.taskNo = taskNo;
    }

    @Override
    public void execute() {
        String change = taskList.markFinished(taskNo);
        Ui.wrapPrint(change);
    }
}
