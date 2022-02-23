package sparrow.logic.commands;

import sparrow.model.TaskList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

/**
 * Represents the command to mark a task as done.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private final int index;

    public MarkCommand(int i) {
        index = i;
    }

    /**
     * Executes the command.
     *
     * @param storage  Storage of Duke.
     * @param tasks Task list of Duke.
     * @param ui User interface of Duke.
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.mark(index);
        return ui.markTask(tasks.get(index));
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
