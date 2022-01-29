package ann.commands;

/**
 * Represents a user command to view their TaskList.
 *
 * @author Hong Anh
 * @version 0.1
 */
public class ListCommand extends Command{

    /**
     * Creates a new ListCommand.
     */
    public ListCommand() {

    }

    /**
     * Executes the command by setting the 'message' field to the user's
     * list of tasks.
     */
    @Override
    public void executeCommand() {
        super.setMessage(super.taskList.getTasksString());
    }
}