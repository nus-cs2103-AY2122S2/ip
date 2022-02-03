package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;
import dazz.exception.InvalidTaskIndexException;

/**
 * Represents the logic of deleting a <code>Task</code>.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a <code>DeleteCommand</code> based on the
     * index of the <code>Task</code> to be deleted.
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the deleting of a <code>Task</code>,
     * updates the text file and returns the execution message.
     * @param taskList The <code>TaskList</code> that contains the <code>Task</code> to mark.
     * @param ui The <code>Ui</code> of Dazz.
     * @param storage The <code>Storage</code> of Dazz.
     * @return The execution message.
     * @throws InvalidTaskIndexException If the index is out of bound.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskIndexException {
        String message = ui.messageForDelete(taskList.getTask(this.index));
        //ui.showDelete(taskList.getTask(this.index));
        taskList.delete(this.index);
        storage.updateList(taskList);
        return message;
    }
}
