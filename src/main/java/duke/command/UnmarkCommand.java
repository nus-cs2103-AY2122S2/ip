package duke.command;

import duke.functionality.TaskList;
import duke.task.Task;

/**
 * Represents the unmark command. A <code>UnmarkCommand</code> object allows users to set the corresponding task as
 * not done.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructor for the UnmarkCommand class.
     * @param number an indicator to the index of the taskList in TaskList class.
     */
    public UnmarkCommand(Integer number) {
        super(null, number, null);
    }

    /**
     * Returns a string which contains the message after unmarking the task from the list.
     * @param tasks an object of TaskList, used to access public methods in TaskList class.
     * @return crafted message after unmarking task from the list.
     */
    @Override
    public String execute(TaskList tasks) {
        String message = "OK, I've marked this task as not done yet:\n";
        int index = super.index;
        assert index > 0 : "Index provided should be greater then 0";
        Task unMarkedTask = tasks.unMarkTask(index);
        return message + unMarkedTask;
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
