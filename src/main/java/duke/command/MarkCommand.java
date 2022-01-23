package duke.command;

import duke.functionality.TaskList;

/**
 * Represents the mark command. A <code>MarkCommand</code> object allows users to set the corresponding task as done.
 */
public class MarkCommand extends Command {

    /**
     * Constructor for MarkCommand class.
     * @param number An indicator to the index of the ArrayList in TaskList class.
     */
    public MarkCommand(Integer number) {
        super(null, number);
    }

    /**
     * Returns nothing, but marks the corresponding task in the taskList in TaskList class as done.
     * @param tasks an Object of TaskList, used to access public methods in TaskList class.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.markTask(super.index);
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
