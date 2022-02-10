package duke.command;

import duke.TaskList;

public class CommandUnmark extends Command {
    private TaskList taskList;
    private int taskNo;

    public CommandUnmark(TaskList taskList, int taskNo) {
        assert taskList != null;
        this.taskList = taskList;
        this.taskNo = taskNo;
    }

    @Override
    public String execute() {
        String change = taskList.unmarkFinished(taskNo);
        return change;
    }
}
