package ann.commands;

/**
 * Represents a user command to mark a Task as done.
 *
 * @author Hong Anh
 * @version 0.1
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Creates a new MarkCommand with the specified task index.
     *
     * @param i an int representing the index of the task to be marked as done.
     */
    public MarkCommand(int i) {
        index = i;
    }

    /**
     * Marks as done the Task at the specified index in the TaskList
     * and sets the 'message' field to reflect this change.
     */
    @Override
    public void executeCommand() {
        String markTaskMessage = super.taskList.markTaskAtIndex(index);
        super.setMessage(markTaskMessage);
    }
}
