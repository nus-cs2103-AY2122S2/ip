package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;
import dazz.exception.InvalidTaskIndexException;

/**
 * Represents the logic of unmarking a task.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an <code>UnmarkCommand</code> based on the
     * <code>Task</code> to be unmarked.
     * @param index
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmarking of a <code>Task</code>,
     * updates the text file and returns the execution message.
     * @param taskList The <code>TaskList</code> that contains the <code>Task</code> to unmark.
     * @param ui The <code>Ui</code> of Dazz.
     * @param storage The <code>Storage</code> of Dazz.
     * @return The execution message.
     * @throws InvalidTaskIndexException If the index provided is out of bound.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskIndexException {
        taskList.unmark(this.index);
        //ui.showUnmark(taskList.getTask(this.index));
        String message = ui.messageForUnmark(taskList.getTask(this.index));
        storage.updateList(taskList);
        return message;
    }
}
