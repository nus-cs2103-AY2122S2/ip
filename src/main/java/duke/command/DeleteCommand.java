package duke.command;

import duke.functionality.TaskList;

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

        super(null, number);
    }

    /**
     * Returns nothing, but deletes the specified task in the taskList in TaskList class.
     * @param tasks an object of TaskList, used to access public methods in TaskList class.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.deleteTask(super.index);
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
