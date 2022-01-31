package funbox.command;

import funbox.exception.FunBoxExceptions;
import funbox.task.Task;
import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;

/**
 * Deal with handling command for Mark.
 */
public class MarkCommand extends Command {
    int index;

    /**
     * The constructor for MarkCommand.
     *
     * @param index The index of the task which should be marked as done.
     */
    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Execute the command to mark the tasks in the list.
     *
     * @param taskList List of tasks.
     * @param ui Interface which interact with users.
     * @param storage Stores user tasks locally.
     * @return Returns a string to be displayed to the user.
     * @throws FunBoxExceptions If (index - 1) > taskList.getSize() or (index - 1) < 0.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions {
        String result = "";
        if ((index - 1) > taskList.getSize() || (index - 1) < 0) {
            throw new FunBoxExceptions("Wrong index!");
        } else {
            int index = this.index - 1;
            taskList.setTaskDone(index);
            result = ui.printMarkDone() + "\n" + taskList.getTask(ui, index);
        }
        return result;
    }
}
