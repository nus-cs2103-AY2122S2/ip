package duke.command;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the unmark command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskNum;

    /**
     * Class constructor.
     *
     * @param taskNum The index of the task to be unmarked.
     */
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the unmark command, prints confirmation message and save updated list to file.
     *
     * @param taskList The current list of tasks.
     * @param ui The ui of the program.
     * @param storage The storage of the program.
     * @throws DukeException If updated task list cannot be saved to file, or if the task number given is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int currentTotalTasks = taskList.getTotalTasks();
        if (taskNum <= currentTotalTasks && taskNum > 0) {
            Task taskUnmarked = taskList.unmark(this.taskNum);
            ui.printOutput("Okay, I've marked this task as undone:\n      " + taskUnmarked);
            storage.saveToHardDisk(taskList);
        } else {
            throw new DukeException("Invalid task number. There are " + currentTotalTasks + " in the list.");
        }
    }

    /**
     * Returns whether this is an exit command.
     *
     * @return True.
     */
    public boolean isExit() {
        return false;
    }
}
