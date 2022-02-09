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
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        String command = this.commandAndDetails[0];
        int taskNumber = Integer.parseInt(this.commandAndDetails[1]);
        switch (command) {
        case "mark":
            this.markTask(taskNumber, taskList);
            break;
        case "unmark":
            this.unmarkTask(taskNumber, taskList);
            break;
        case "delete":
            this.deleteTask(taskNumber, taskList);
            break;
        default:
            System.out.println("There has been an error. Please try again.");
        }
        storage.writeToFile(taskList);
        return false;
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskNumber the index of the task in the task list.
     * @param taskList the list of the user's tasks.
     */
    public void markTask(int taskNumber, TaskList taskList) {
        Task task = taskList.getTask(taskNumber - 1);
        task.markAsDone();
        System.out.println("Good job! This task is done:");
        System.out.println("  " + task);
    }

    /**
     * Marks the specified task as not done.
     *
     * @param taskNumber the index of the task in the task list.
     * @param taskList the list of the user's tasks.
     */
    public void unmarkTask(int taskNumber, TaskList taskList) {
        Task task = taskList.getTask(taskNumber - 1);
        task.unmarkAsDone();
        System.out.println("Hurry up and get it done!");
        System.out.println("  " + task);
    }

    /**
     * Deletes the specified task from the list.
     *
     * @param taskNumber the index of the task in the task list.
     * @param taskList the list of the user's tasks.
     */
    public void deleteTask(int taskNumber, TaskList taskList) {
        Task task = taskList.getTask(taskNumber - 1);
        taskList.removeTask(taskNumber - 1);
        System.out.println("This task has been removed:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.size() + " task(s).");
    }
}
