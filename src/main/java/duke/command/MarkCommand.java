package duke.command;

import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

/**
 * An extension from Command.
 * Deals with the marking of a task.
 */
public class MarkCommand extends Command {

    private final int taskNo;

    /**
     * Constructs a new MarkCommand with a variable, taskNo.
     * @param taskNo arrayList index of a task in a taskList
     */
    public MarkCommand(int taskNo) {
        this.taskNo = taskNo - 1;
    }

    /**
     * Returns a string indicating the result of marking
     * a task on the given taskList.
     * Executes a method which sets the status of the
     * task as done.
     * @param taskList a list of the current tasks
     * @param ui user interface
     * @param storage file storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.setDone(taskNo);
        String statement = "Nice! I have marked this task as done:\n  ";
        return statement + taskList.getAction(taskNo);
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
