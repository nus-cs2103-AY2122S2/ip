package duke.command;

import duke.ui.Ui;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents the command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
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
        return ui.deleteTask(tasks.delete(index)) + ui.countTasks(tasks);
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
