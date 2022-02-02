package duke.command;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the add command to add a task to the list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Class constructor.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command, prints confirmation message and saves the updated task list to file.
     *
     * @param taskList The current list of tasks.
     * @param ui The ui of the program.
     * @param storage The storage of the program.
     * @throws DukeException If updated task list cannot be saved to file.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.add(this.task);
        ui.printOutput("Got it! I've added this task:\n  "
                + ui.showIndent() + this.task + "\n"
                + ui.showIndent() + taskList.getListStatus());

        storage.saveToHardDisk(taskList);
    }

    /**
     * Returns true if command is an ExitCommand, else returns false.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
