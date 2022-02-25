package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command parsed from a user input
 */
public interface Command {

    /**
     * Checks whether the command prompts Duke program exit
     *
     * @return value indicating if command prompts Duke program exit
     */
    boolean isExit();

    /**
     * Executes command
     *
     * @param tasks   the list of all user tasks
     * @param ui      the controller for UI interactions
     * @param storage the encapsulated storage object handling local memory
     */
    void execute(TaskList<Task> tasks, Ui ui, Storage storage);
}
