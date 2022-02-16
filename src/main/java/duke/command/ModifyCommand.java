package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command for modifying tasks.
 */
public class ModifyCommand implements Command {

    protected String[] commandAndDetails;

    /**
     * Constructor for a ModifyCommand object.
     *
     * @param commandAndDetails the specific modify command and index of the task.
     */
    public ModifyCommand(String[] commandAndDetails) {
        this.commandAndDetails = commandAndDetails;
    }

    /**
     * Executes the command.
     *
     * @param taskList the list of the tasks a user has.
     * @param ui an instance of a user interface.
     * @param storage a storage used to save the user's tasks.
     * @return a boolean indicating whether it is an exit command.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String command = this.commandAndDetails[0];
        int taskNumber = Integer.parseInt(this.commandAndDetails[1]);
        assert taskNumber >= 0 : "Task number should not be negative";
        assert taskNumber < taskList.size() : "Invalid task number! You have " + taskList.size() + " task(s).";
        String response = "";
        switch (command) {
        case "mark":
            response += this.markTask(taskNumber, taskList);
            break;
        case "unmark":
            response += this.unmarkTask(taskNumber, taskList);
            break;
        case "delete":
            response += this.deleteTask(taskNumber, taskList);
            break;
        default:
            response += "There has been an error. Please try again.";
        }
        storage.writeToFile(taskList);
        return response;
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskNumber the index of the task in the task list.
     * @param taskList the list of the user's tasks.
     */
    public String markTask(int taskNumber, TaskList taskList) {
        Task task = taskList.getTask(taskNumber - 1);
        task.markAsDone();
        return "Good job! This task is done:\n" + "  " + task;
    }

    /**
     * Marks the specified task as not done.
     *
     * @param taskNumber the index of the task in the task list.
     * @param taskList the list of the user's tasks.
     */
    public String unmarkTask(int taskNumber, TaskList taskList) {
        Task task = taskList.getTask(taskNumber - 1);
        task.unmarkAsDone();
        return "Hurry up and get it done!\n" + "  " + task;
    }

    /**
     * Deletes the specified task from the list.
     *
     * @param taskNumber the index of the task in the task list.
     * @param taskList the list of the user's tasks.
     */
    public String deleteTask(int taskNumber, TaskList taskList) {
        Task task = taskList.getTask(taskNumber - 1);
        taskList.removeTask(taskNumber - 1);
        return "This task has been removed:\n" + "  " + task + "\n" + "Now you have "
                + taskList.size() + " task(s).";
    }
}
