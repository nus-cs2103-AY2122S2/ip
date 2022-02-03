package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;

/**
 * Represents the logic of the default behavior of <code>Dazz</code>.
 */
public class DefaultCommand extends Command {

    /**
     * Executes the default behavior of <code>Dazz</code>.
     * @param taskList The <code>TaskList</code>.
     * @param ui The <code>Ui</code> of Dazz.
     * @param storage The <code>Storage</code> of Dazz.
     * @return The execution message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //ui.showDefault();
        return ui.messageForDefault();
    }
}
