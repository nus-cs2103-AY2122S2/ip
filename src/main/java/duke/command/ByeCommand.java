package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Bye Command, in charge of bye command.
 */
public class ByeCommand extends Command {
    /**
     * Instantiates a ByeCommand with a commandArray.
     * @param commandArray String[] String array of input command.
     */
    public ByeCommand(String[] commandArray) {
        super(commandArray);
    }

    /**
     * Shows UI for bye command. Duke and Parser handles the termination.
     *
     * @param taskList TaskList input taskList object from Duke.
     * @param ui Ui input ui object from Duke.
     * @param storage Storage input storage object from Duke.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.showUiForBye();
    }

    /**
     * Checks the equality of two Command objects based on first index of commandArrays.
     *
     * @param command Object Another command object to be compared to.
     * @return boolean Boolean value to show if this equals command.
     */
    @Override
    public boolean equals(Object command) {
        return this.getCommandArray()[0].equals(((ByeCommand) command).getCommandArray()[0]);
    }
}
