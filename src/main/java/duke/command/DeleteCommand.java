package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.action.Action;

public class DeleteCommand extends Command {
    private int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Action action = taskList.delete(taskNo);
        System.out.println("Noted. I have removed this task:\n  " +
                action + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
