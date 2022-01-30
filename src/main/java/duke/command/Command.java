package duke.command;

import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;


public abstract class Command {

    /**
     * Executes a certain function depending on the nature of the subclass.
     * @param taskList a list of the current tasks
     * @param ui user interface
     * @param storage file storage
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns true if the class is an Exit command
     * else false for others
     * @return true for ExitCommand
     */
    public abstract boolean isExit();
}
