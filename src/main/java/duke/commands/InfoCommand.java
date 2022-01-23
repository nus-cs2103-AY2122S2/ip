package duke.commands;

/**
 * Lists all tasks in the task list.
 */
public class InfoCommand extends Command {
    public String execute() {
        String response = super.taskList.listTasks();
        return response;
    }
}
