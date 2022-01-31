package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Deals with handling command that unmark tasks.
 */
public class UnmarkCommand extends Command {
    private String taskNum;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param taskNum the task number to be marked.
     */
    public UnmarkCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks the task and print out message to inform user.
     *
     * @param tasks List of the tasks.
     * @param ui UI that deals with interactions with the user.
     * @param storage storage handles the saving and writing to file.
     * @return message stating task unmark. Else show error message.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int currTaskNum = Integer.parseInt(taskNum);
            if (tasks.getTaskList().size() >= currTaskNum && currTaskNum > 0) {
                Task currTask = tasks.unmarkTask(currTaskNum);
                return ui.showTaskUnmarked(currTask);
            } else {
                return ui.showError("You don't have such task");
            }
        } catch (NumberFormatException e) {
            return ui.showError("Error! Please input a task number");
        }

    }

    /**
     * Checks if the user is exiting the program.
     *
     * @return false that user not exiting.
     */
    public boolean isExit() {
        return false;
    }
}
