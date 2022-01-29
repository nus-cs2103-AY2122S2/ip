package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    /**
     * Output entire TaskList.
     *
     * @param ui Ui for outputting message.
     * @param storage Storage for rewriting TaskList.
     * @param taskList TaskList that stores Tasks.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.outputMessage("Here are the tasks in your list:\n" + taskList);
    }
}
