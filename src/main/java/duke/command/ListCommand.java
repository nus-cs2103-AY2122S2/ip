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
        String res = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            res += "\n  " + (i + 1) + ". " + tasks.get(i);
        }
        return res;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
