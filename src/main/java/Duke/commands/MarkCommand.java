package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Deals with handling command that mark tasks.
 */
public class MarkCommand extends Command {
    private String taskNum;

    /**
     * Constructor for MarkCommand.
     *
     * @param taskNum the task number to be marked.
     */
    public MarkCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks the task and print out message to inform user.
     *
     * @param tasks List of the tasks.
     * @param ui UI that deals with interactions with the user.
     * @param storage storage handles the saving and writing to file.
     * @return message stating task mark. Else show error message.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int currTaskNum = Integer.parseInt(taskNum);
            if (tasks.getTaskList().size() >= currTaskNum && currTaskNum > 0) {
                Task currTask = tasks.markTask(currTaskNum);
                return ui.showTaskMarked(currTask);
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
