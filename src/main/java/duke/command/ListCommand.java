package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.ui.ImageType;
import duke.ui.Ui;

/**
 * Represents a Command which, when executed, lists all Task objects in the TaskList instance.
 */
public class ListCommand extends Command {
    private static final String BEGINNING_MESSAGE = "Charizard's burning wish list:";
    private static final boolean IS_EXIT = false;

    /**
     * Creates a new ListCommand instance.
     */
    public ListCommand() {
        super(IS_EXIT);
    }

    /**
     * Displays all Task objects from the given TaskList instance.
     *
     * @param tasks The TaskList instance that contains the Task objects to be displayed.
     * @param ui The Ui object used for displaying the Task objects.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            ui.appendMessage(BEGINNING_MESSAGE);
            ui.appendBorder();
            String message = "";
            for (int i = 0; i < tasks.getSize(); i++) {
                message += String.format("%d. %s", i + 1, tasks.getTask(i).toString());
                if (i < tasks.getSize() - 1) {
                    message += "\n";
                }
            }
            ui.appendMessage(message);
            ui.setRespondImage(ImageType.GENERAL);
        } catch (DukeException e) {
            throw new DukeException(DukeException.ERROR_INTERNAL);
        }
    }
}
