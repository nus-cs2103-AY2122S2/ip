package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;

/**
 * Represents the logic of listing <code>Task</code> in <code>TaskList</code>.
 */
public class ListCommand extends Command {

    /**
     * Executes the listing of <code>Task</code> from <code>TaskList</code>
     * and returns the execution message.
     * @param taskList The <code>TaskList</code> that contains the <code>Task</code> to be listed.
     * @param ui The <code>Ui</code> of Dazz.
     * @param storage The <code>Storage</code> of Dazz.
     * @return The execution message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //ui.showList(taskList);
        return ui.messageForList(taskList);
    }

}
