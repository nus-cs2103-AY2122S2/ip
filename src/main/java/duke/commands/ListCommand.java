package duke.commands;

/**
 * Command that displays all Task in TaskList.
 */
public class ListCommand extends Command {
    private String displayTaskList() {
        StringBuilder numberedTaskList = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            numberedTaskList.append(String.format("%d. %s \n", i + 1, taskList.getTask(i)));
        }
        return numberedTaskList.toString().trim();
    }

    /**
     * Checks if TaskList is empty, otherwise displays all Task.
     *
     * @return All Task in TaskList and message for completing the command which is displayed to user.
     */
    @Override
    public String execute() {
        if (taskList.size() == 0) {
            return "Your current task list is empty";
        }

        return String.format("These are the current tasks in your list:\n%s", displayTaskList());
    }
}
