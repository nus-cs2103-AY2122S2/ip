package karen.command;

import karen.Storage;
import karen.Ui;
import karen.task.Task;

/**
 * To add Task objects into Storage.
 */
public class AddCommand extends Command {
    protected Task commandItem;

    public AddCommand(Task item) {
        commandItem = item;
    }

    /**
     * Adds commandItem to the end of storage's taskList. Saves the current state of taskList to local file directory.
     *
     * @param ui To control outputs related to execution
     * @param storage To access and modify Tasks stored in Storage
     * @return String result of output from successful execution of Command
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        storage.addTask(commandItem);
        storage.saveTasks();
        return ui.displayUserInput(ui.formatCount("added", commandItem, storage.getTaskCount()));
    }
}
