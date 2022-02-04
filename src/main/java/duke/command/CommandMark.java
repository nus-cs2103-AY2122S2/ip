package duke.command;

import duke.TaskList;
import duke.Ui;

public class CommandMark extends Command {
    private TaskList taskList;
    private int taskNo;

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
