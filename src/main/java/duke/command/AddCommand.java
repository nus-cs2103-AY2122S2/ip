package duke.command;

import duke.functionality.TaskList;
import duke.task.Task;

/**
 * Represents the add command. A <code>AddCommand</code> object corresponds to adding that specified task
 * to the taskList in the TaskList class.
 */
public class AddCommand extends Command {


    /**
     * Constructor of AddCommand class.
     * @param task task object created from user input.
     */
    public AddCommand(Task task) {
        super(task, null, null);
    }

    /**
     * Returns a string which contains the message after adding the task to the list.
     * @param tasks an object of TaskList, used to access public methods in TaskList class.
     * @return crafted message after adding task to list.
     */
    @Override
    public String execute(TaskList tasks) {
        String message = "Got it. I've added this task:\n";
        tasks.addToList(super.task);
        return message + super.task.toString() + "\nNow you have " + tasks.getListSize() + " tasks in the list.";
    }

    /**
     * Returns false as the Command is not an ExitCommand.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
