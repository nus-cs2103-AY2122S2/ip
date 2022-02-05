package ann.commands;

/**
 * Represents a user command to mark a Task as not done.
 *
 * @author Hong Anh
 * @version 0.1
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Creates a new UnmarkCommand with the specified task index.
     *
     * @param i an int representing the index of the task to be marked as not done.
     */
    public UnmarkCommand(int i) {
        index = i;
    }

    /**
     * Marks as not done the Task at the specified index in the TaskList
     * and sets the 'message' field to reflect this change.
     */
    @Override
    public void executeCommand() {
        String unmarkTaskMessage = super.taskList.unmarkTaskAtIndex(index);
        super.setMessage(unmarkTaskMessage);
    }
}
