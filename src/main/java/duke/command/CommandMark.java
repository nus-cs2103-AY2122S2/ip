package duke.command;

import duke.TaskList;

public class CommandMark extends Command {
    private TaskList taskList;
    private int taskNo;

    public CommandMark(TaskList taskList, int taskNo) {
        assert taskList != null;
        this.taskList = taskList;
        this.taskNo = taskNo;
    }

    @Override
    public String execute() {
        String change = taskList.markFinished(taskNo);
        return change;
    }
}
