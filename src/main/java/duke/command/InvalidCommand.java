package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * An invalid command object.
 */
public class InvalidCommand extends Command {
    public InvalidCommand() {
        super();
    }

    /**
     * Executes nothing. Invalid command to show error messages.
     *
     * @param taskList TaskList input taskList object from Duke.
     * @param ui       Ui input ui object from Duke.
     * @param storage  Storage input storage object from Duke.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("    Invalid Command");
    }
}
