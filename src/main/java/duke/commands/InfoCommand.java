package duke.commands;

/**
 * Provides information about the task list.
 */
public class InfoCommand extends Command {
    /**
     * Executes the info command.
     *
     * @return response from the execution.
     */
    public String execute() {
        String infoResponse = super.taskList.listTasks();
        return infoResponse;
    }
}
