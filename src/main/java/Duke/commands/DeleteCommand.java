package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.DukeException;
import duke.ui.Ui;

/**
 * Deals with handling command that delete tasks.
 */
public class DeleteCommand extends Command {
    private String taskNum;

    /**
     * Constructor for AddCommand.
     *
     * @param taskNum the task number to be deleted.
     */
    public DeleteCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes task from task list and print out message to inform user.
     *
     * @param tasks List of the tasks.
     * @param ui UI that deals with interactions with the user.
     * @param storage storage handles the saving and writing to file.
     * @return message stating task deleted. If not show error message to user.
     * @throws DukeException if user cannot delete tasks or key in wrong input.
     *
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int currTaskNum = Integer.parseInt(taskNum);
            if (tasks.getTaskList().size() >= currTaskNum && currTaskNum > 0) {
                Task currTask = tasks.getTaskList().get(currTaskNum - 1);
                tasks.deleteTask(currTaskNum - 1);
                return ui.showTaskDeleted(currTask, tasks.getTaskList());
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
