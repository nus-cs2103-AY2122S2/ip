package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * List Command, in charge of showing the list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Instantiates a ListCommand using input commandArray.
     *
     * @param commandArray String[] String array from input command.
     */
    public ListCommand(String[] commandArray) {
        super(commandArray);
    }

    /**
     * Executes the list command using TaskList object.
     *
     * @param taskList TaskList input taskList object from duke.Duke.
     * @param ui       Ui input ui object from duke.Duke.
     * @param storage  Storage input storage object from duke.Duke.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.showUiForTaskList(taskList);
    }

    /**
     * Checks the equality of two Command objects based on commandArray index 0.
     *
     * @param command Object Another command object to be compared to.
     * @return boolean Boolean value to show if this equals command.
     */
    @Override
    public boolean equals(Object command) {
        return this.getCommandArray()[0].equals(((ListCommand) command).getCommandArray()[0]);
    }
}
