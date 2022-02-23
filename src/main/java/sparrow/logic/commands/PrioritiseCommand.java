package sparrow.logic.commands;

import sparrow.model.Priority;
import sparrow.model.TaskList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

/**
 * Represents the command to mark a task as done.
 */
public class PrioritiseCommand extends Command {
    public static final String COMMAND_WORD = "prioritise";
    private final int index;
    private final Priority priority;
    /**
     * Executes the command.
     *
     * @param i  The task index.
     * @param p The task priority.
     */
    public PrioritiseCommand(int i, Priority p) {
        index = i;
        priority = p;
    }

    /**
     * Executes the command.
     *
     * @param storage  Storage of Duke.
     * @param tasks Task list of Duke.
     * @param ui User interface of Duke.
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.prioritise(index, priority);
        return ui.prioritiseTask(tasks.get(index));
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
