package java.duke.command;

import java.duke.ui.Storage;
import java.duke.ui.TaskList;
import java.duke.ui.Ui;


public abstract class Command {

    /**
     * Executes a certain function depending on the nature of the subclass.
     * @param taskList a list of the current tasks
     * @param ui user interface
     * @param storage file storage
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns true if the class is an Exit command
     * else false for others
     * @return true for ExitCommand
     */
    public abstract boolean isExit();
}
