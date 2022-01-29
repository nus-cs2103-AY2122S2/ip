package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * A class that encapsulates a command.
 */
public class Command {
    protected String keyword;

    /**
     * Default constructor for a Command.
     *
     * @param keyword Name of the command.
     */
    public Command(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the actions of the command.
     *
     * @param tasks TaskList to act on.
     * @param ui Ui to use when printing messages.
     * @param storage Storage to call for loading and saving tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    /**
     * Returns boolean that states if a command is and exit command.
     *
     * @return True if command is and exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
