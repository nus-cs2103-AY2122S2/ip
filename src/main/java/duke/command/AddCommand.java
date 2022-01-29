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
     * Returns nothing, but adds the specified task in the taskList in TaskList class.
     * @param tasks an object of TaskList, used to access public methods in TaskList class.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.addToList(super.task);
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
