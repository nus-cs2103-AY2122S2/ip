package taskie.command;

import taskie.storage.Storage;
import taskie.tasklist.TaskList;
import taskie.ui.Ui;

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
     * @param response StringBuilder object to append results to.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage, StringBuilder response) {
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
