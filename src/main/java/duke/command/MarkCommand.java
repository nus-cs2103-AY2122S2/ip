package java.duke.command;

import java.duke.ui.Storage;
import java.duke.ui.TaskList;
import java.duke.ui.Ui;

public class MarkCommand extends Command {

    private final int taskNo;

    public MarkCommand(int taskNo) {
        this.taskNo = taskNo - 1;
    }

    /**
     * Executes a method which sets the status of the
     * task as done.
     * @param taskList a list of the current tasks
     * @param ui user interface
     * @param storage file storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.setDone(taskNo);
        String statement = "Nice! I have marked this task as done:\n  ";
        System.out.println(statement + taskList.getAction(taskNo));
    }

    /**
     * Returns false for non-Exit
     * commands.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
