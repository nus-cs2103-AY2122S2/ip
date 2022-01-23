package duke.command;

import duke.task.Task;
import duke.functionality.TaskList;
/**
 * Represents the add command. A <code>AddCommand</code> object corresponds to adding that specified task
 * to the taskList in the TaskList class.
 */
public class AddCommand extends Command {

    /**
     * Constructor of AddCommand class.
     * @param t task object created from user input.
     */
    public AddCommand(Task t){
        super(t, null);
    }

    /**
     * Returns nothing, but adds the specified task in the taskList in TaskList class.
     * @param tasks an Object of TaskList, used to access public methods in TaskList class.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.addToList(super.task);
    }

    /**
     * Returns false as the command is not an ExitCommand.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
