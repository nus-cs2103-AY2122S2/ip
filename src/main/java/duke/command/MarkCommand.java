package duke.command;

import duke.functionality.TaskList;
import duke.task.Task;

/**
 * Represents the mark command. A <code>MarkCommand</code> object allows users to set the corresponding task as done.
 */
public class MarkCommand extends Command {

    /**
     * Constructor for MarkCommand class.
     * @param number an indicator to the index of the taskList in TaskList class.
     */
    public MarkCommand(Integer number) {
        super(null, number, null);
    }

    /**
     * Returns a string which contains the message after marking the task from the list.
     * @param tasks an object of TaskList, used to access public methods in TaskList class.
     * @return crafted message after marking task from the list.
     */
    @Override
    public String execute(TaskList tasks) {
        int index = super.index;
        assert index > 0 : "Index provided should be greater then 0";
        Task markedTask = tasks.markTask(index);
        String message = "Nice! I've marked this task as done:\n";
        return message + markedTask;
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
