package duke.command;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the update command to update a task's description.
 */
public class UpdateCommand extends Command {
    private static final String TWO_SPACES = "  ";
    private final int taskNum;
    private final String updatedDescription;

    /**
     * Class constructor
     *
     * @param taskNum The index of the task to be updated.
     * @param updatedDescription The new description of the task.
     */
    public UpdateCommand(int taskNum, String updatedDescription) {
        this.taskNum = taskNum;
        this.updatedDescription = updatedDescription;
    }

    /**
     * Executes the update command, prints confirmation message and saves updated list to file.
     *
     * @param taskList The current list of tasks.
     * @param ui The ui of the program.
     * @param storage The storage of the program.
     * @throws DukeException If updated task list cannot be saved to file, or if the task number given is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int currentTotalTasks = taskList.getTotalTasks();
        if (taskNum <= currentTotalTasks && taskNum > 0) {
            Task updatedTask = taskList.update(this.taskNum, this.updatedDescription);
            ui.setResponse("Done! This is the updated task:\n" + Ui.showIndent() + TWO_SPACES + updatedTask);
            storage.saveToHardDisk(taskList);
        } else {
            throw new DukeException("Invalid task number. There are " + currentTotalTasks + " tasks in the list.");
        }
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
