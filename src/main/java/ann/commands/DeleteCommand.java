package ann.commands;

/**
 * Represents a user command to remove a specified Task from the user's TaskList.
 *
 * @author Hong Anh
 * @version 0.1
 */
public class DeleteCommand extends Command{
    private int index;

    /**
     * Creates a new DeleteCommand with the specified task index.
     *
     * @param i an int representing the index of the task to be deleted.
     */
    public DeleteCommand(int i) {
        index = i;
    }

    /**
     * Removes the Task at the specified index from the TaskList
     * and sets the 'message' field to reflect this change.
     */
    @Override
    public void executeCommand() {
        String deleteTaskMessage = super.taskList.removeTaskAtIndex(index);
        super.setMessage(deleteTaskMessage);
    }
}