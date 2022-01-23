package duke.command;

import duke.functionality.TaskList;
/**
 * Represents the unmark command. A <code>UnmarkCommand</code> object allows users to set the corresponding task as
 * not done.
 */
public class UnmarkCommand extends Command{

    /**
     * Constructor for the UnmarkCommand class.
     * @param number
     */
    public UnmarkCommand(Integer number) {
        super(null, number);
    }

    /**
     * Returns nothing, but marks the corresponding task in the taskList in TaskList class as not done.
     * @param tasks an Object of TaskList, used to access public methods in TaskList class.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.unMarkTask(super.index);
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
