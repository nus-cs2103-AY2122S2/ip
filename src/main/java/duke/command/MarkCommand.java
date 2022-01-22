package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private int taskNo;

    public MarkCommand(int taskNo) {
        this.taskNo = taskNo - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.setDone(taskNo);
        String statement = "Nice! I have marked this task as done:\n  ";
        System.out.println(statement + taskList.getAction(taskNo));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
