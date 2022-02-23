package sparrow.logic.commands;

import sparrow.model.TaskList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;


/**
 * Represents the command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     *
     * @param storage  Storage of Duke.
     * @param tasks Task list of Duke.
     * @param ui User interface of Duke.
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.unmark(index);
        return ui.unmarkTask(tasks.get(index));
    }

    /**
     * Indicates whether the program should stop after this command.
     *
     * @return Boolean indicating whether the program should stop after this command.
     */
    public boolean isExit() {
        return false;
    }
}
