package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.gui.Ui;

/**
 * ExitCommand is a command that handles the exit command.
 *
 * @author yl-ang
 */
public class ExitCommand extends Command {

    private boolean isExit = true;

    /**
     * Executes ExitCommand with the objects supplied.
     *
     * @param taskLst TaskLst object containing the current tasks.
     * @param ui Ui object containing the user interface messages and scanner object.
     * @param storage Storage object containing the methods to load and save.
     */
    @Override
    public String execute(TaskList taskLst, Ui ui, Storage storage) {
        return ui.showExit();
    }

    /**
     * Checks and returns the boolean whether if command is exit.
     *
     * @return Boolean if the command is exit.
     */
    @Override
    public boolean isExit() {
        return isExit;
    }

}
