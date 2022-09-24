package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a command for adding tasks.
 */
public class AddCommand implements Command {
    protected String[] commandAndDetails;

    /**
     * Constructor for an AddCommand object.
     *
     * @param commandAndDetails the specific task command and details of the task.
     */
    public AddCommand(String[] commandAndDetails) {
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
        String details = this.commandAndDetails[1];
        String response = "";
        Task task = null;
        switch (command) {
        case "todo":
            task = new Todo(details.strip());
            response += "Added a to do task.\n";
            break;
        case "deadline":
            String[] deadlineAndTime = details.split("/by");
            task = new Deadline(deadlineAndTime[0].strip(), deadlineAndTime[1]);
            response += "Added a deadline.\n";
            break;
        case "event":
            String[] eventAndTime = details.split("/at");
            task = new Event(eventAndTime[0].strip(), eventAndTime[1]);
            response += "Added an event.\n";
            break;
        default:
            response += "There has been an error. Please try again.\n";
        }

        if (task != null) {
            taskList.addTask(task);
            response += "  " + task.toString() + "\n"
                    + "You have " + taskList.size() + " task(s) in the list.";
            storage.writeToFile(taskList);
            return response;
        }
        return response;
    }
}
