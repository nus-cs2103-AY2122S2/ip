package duke.command;

import duke.DukeException;
import duke.common.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * A class that exit the Duke programme.
 */
public class ExitCommand extends Command {
    private static final boolean IS_EXIT = true;

    /**
     * Creates a ExitCommand instance.
     */
    public ExitCommand() {
        super(IS_EXIT);
    }

    /**
     * Exit Duke and show the execution message on the GUI.
     *
     * @param tasks The current task list.
     * @param storage The current storage of Duke.
     * @return The string of the GUI message.
     * @throws DukeException If the storage fail to save all tasks,
     * it will throw a DukeException.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            storage.saveAllTasks(tasks);
            return TextUi.showExecutionMessage(Messages.MESSAGE_GOOD_BYE);
        } catch (DukeException e) {
            return TextUi.showError(e.getMessage());
        }
    }
}
