package pac.command;

import pac.task.TaskList;
import pac.ui.Ui;
import pac.storage.Storage;

import java.io.IOException;

/**
 * Abstract class Command
 */
public abstract class Command {

    /**
     * Executes command
     * @param tasks
     * @param ui
     * @param storage
     * @return
     * @throws IOException
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     *
     * @return boolean value that indicates the program to exit
     */
    public boolean isExit() {
        return false;
    }
}
