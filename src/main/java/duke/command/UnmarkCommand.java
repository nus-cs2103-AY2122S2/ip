package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private int taskNo;

    public UnmarkCommand(int taskNo) {
        this.taskNo = taskNo - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.setUnDone(taskNo);
        String statement = "Ok, I have marked this task as not done yet:\n  ";
        System.out.println(statement + taskList.getAction(taskNo));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
