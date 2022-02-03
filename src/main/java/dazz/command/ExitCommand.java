package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;

/**
 * Represents the logic of exiting <code>Dazz</code>.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exiting of <code>Dazz</code>,
     * @param taskList The <code>TaskList</code> that contains the <code>Task</code> to mark.
     * @param ui The <code>Ui</code> of Dazz.
     * @param storage The <code>Storage</code> of Dazz.
     * @return The execution message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //ui.showExit();
        return ui.messageForExit();
    }

    /**
     * Gets whether the <code>Command</code> is exit.
     * @return true if user inputs 'bye'.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
