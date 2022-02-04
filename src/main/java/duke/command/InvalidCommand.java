package duke.command;

import duke.exception.DukeCommandException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * An invalid command object.
 */
public class InvalidCommand extends Command {
    private String message;

    /**
     * Instantiates an invalid command that throws a DukeCommandException on execution.
     */
    public InvalidCommand() {
        super();
        message = "";
    }

    /**
     * Instantiates an invalid command that throws a DukeCommandException on execution with error message.
     *
     * @param message String Error message for an invalid command.
     */
    public InvalidCommand(String message) {
        super();
        this.message = message;
    }



    /**
     * Executes nothing. Invalid command to show error messages.
     *
     * @param taskList TaskList input taskList object from duke.Duke.
     * @param ui       Ui input ui object from duke.Duke.
     * @param storage  Storage input storage object from duke.Duke.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws DukeCommandException {
        throw new DukeCommandException(message);
    }
}
