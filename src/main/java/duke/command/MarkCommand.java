package duke.command;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the mark command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int taskNum;

    /**
     * Class constructor.
     *
     * @param taskNum The index of the task to be marked.
     */
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the mark command, prints confirmation message and saves updated list to file.
     *
     * @param taskList The current list of tasks.
     * @param ui The ui of the program.
     * @param storage The storage of the program.
     * @throws DukeException If updated task list cannot be saved to file, or if the task number given is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int currentTotalTasks = taskList.getTotalTasks();
        if (taskNum <= currentTotalTasks && taskNum > 0) {
            Task taskMarked = taskList.mark(this.taskNum);
            ui.printOutput("Nice! You've completed this task:\n      " + taskMarked);
            storage.saveToHardDisk(taskList);
        } else {
            throw new DukeException("Invalid task number. There are " + currentTotalTasks + " in the list.");
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
