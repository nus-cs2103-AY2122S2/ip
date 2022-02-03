package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;
import dazz.exception.InvalidTaskIndexException;

/**
 * Represents the logic of marking a <code>Task</code>.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a <code>MarkCommand</code> based on
     * the index of the <code>Task</code>.
     * @param index The index of the <code>Task</code> to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the marking of a <code>Task</code>,
     * updates the text file and returns the execution message.
     * @param taskList The <code>TaskList</code> that contains the <code>Task</code> to mark.
     * @param ui The <code>Ui</code> of Dazz.
     * @param storage The <code>Storage</code> of Dazz.
     * @return The execution message.
     * @throws InvalidTaskIndexException If the index is out of bound.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskIndexException {
        taskList.mark(this.index);
        //ui.showMark(taskList.getTask(this.index));
        String message = ui.messageForMark(taskList.getTask(this.index));
        storage.updateList(taskList);
        return message;
    }
}
