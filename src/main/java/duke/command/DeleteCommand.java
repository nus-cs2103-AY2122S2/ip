package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Delete Command, in charge of delete command
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Instantiates a DeleteCommand object with an index and a commandArray.
     *
     * @param index int Index of Task to be deleted.
     * @param commandArray String[] String array from input command.
     */
    public DeleteCommand(int index, String[] commandArray) {
        super(commandArray);
        this.index = index;
    }

    /**
     * Executes the delete command using TaskList object.
     *
     * @param taskList TaskList input taskList object from Duke.
     * @param ui       Ui input ui object from Duke.
     * @param storage  Storage input storage object from Duke.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(index);
        storage.save(taskList);
    }

    /**
     * Checks the equality of two Command objects based on the index.
     *
     * @param command Object Another command object to be compared to.
     * @return boolean Boolean value to show if this equals command.
     */
    @Override
    public boolean equals(Object command) {
        if (this.index == ((DeleteCommand) command).index) {
            return true;
        } else {
            return false;
        }
    }
}
