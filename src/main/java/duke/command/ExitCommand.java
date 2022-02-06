package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.ui.ImageType;
import duke.ui.Ui;

/**
 * Represents a Command which, when executed, exits the Duke Program.
 */
public class ExitCommand extends Command {
    private static final boolean IS_EXIT = true;

    /**
     * Creates a new ExitCommand instance.
     */
    public ExitCommand() {
        super(IS_EXIT);
    }

    /**
     * Saves all user tasks into disk and displays an exit message on the Ui.
     * Displays the error message on Ui if the tasks cannot be stored successfully.
     *
     * @param tasks   The TaskList object that contains all user tasks.
     * @param ui      The Ui object used for displaying the Task objects.
     * @param storage The Storage object used for storing tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert ui.hasEmptyMessage() : "Ui has leftover message from previous tasks";
        storage.saveFileData(tasks.getTasks());
        ui.appendMessage(Ui.BYE_MESSAGE);
        ui.setRespondImage(ImageType.GENERAL);
    }
}
