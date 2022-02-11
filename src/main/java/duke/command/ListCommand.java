package duke.command;

import duke.task.TaskList;

public class ListCommand extends Command {

    /**
     * Constructs a {@code ListCommand} object.
     */
    public ListCommand() {}

    /**
     * Asks the UI to display the list of tasks.
     * @param tasks current list of tasks
     */
    @Override
    public String execute(TaskList tasks) {
        if (tasks.size() == 0) {
            return "There are no tasks in your list~";
        }
        String response = "Here are the tasks in your list:";
        for (int i = 1; i <= tasks.size(); i++) {
            response += "\n  " + i + ". " + tasks.get(i);
        }
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
