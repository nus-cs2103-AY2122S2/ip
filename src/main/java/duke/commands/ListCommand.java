package duke.commands;

/**
 * Command that displays all Task in TaskList.
 */
public class ListCommand extends Command {

    /**
     * Checks if TaskList is empty, otherwise displays all Task.
     *
     * @return All Task in TaskList and message for completing the command which is displayed to user.
     */
    @Override
    public String execute() {
        return taskList.toString();
    }
}
