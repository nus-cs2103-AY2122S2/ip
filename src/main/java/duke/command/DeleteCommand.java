package duke.command;

import duke.functionality.TaskList;
import duke.task.Task;

/**
 * Represents the delete command. A <code>DeleteCommand</code> object corresponds to deleting that specified task
 * to the taskList in the TaskList class.
 */
public class DeleteCommand extends Command {

    /**
     * Constructor for DeleteCommand class.
     * @param number an indicator to the index of the taskList in TaskList class.
     */
    public DeleteCommand(Integer number) {
        super(null, number, null);
    }

    /**
     * Returns a string which contains the message after deleting the task from the list.
     * @param tasks an object of TaskList, used to access public methods in TaskList class.
     * @return crafted message after deleting task from list.
     */
    @Override
    public String execute(TaskList tasks) {
        int index = super.index;
        assert index > 0 : "Index provided should be greater then 0";
        Task deletedTask = tasks.deleteTask(index);
        String message = "Noted. I've removed this task:\n";
        return message + deletedTask.toString() + "\n"
                + "Now you have " + tasks.getListSize() + " tasks in the list.";
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
