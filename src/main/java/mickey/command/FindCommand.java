package mickey.command;

import mickey.app.MickeyException;
import mickey.app.Storage;
import mickey.app.Ui;
import mickey.task.Task;
import mickey.task.TaskList;

import java.util.List;

/**
 * Find command to find task.
 */
public class FindCommand extends Command {
    /**
     * Constructor.
     *
     * @param fullCommand User input command.
     */
    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Execute command.
     *
     * @param tasks List of tasks.
     * @param ui Ui to print feedback.
     * @param storage Storage to store tasks.
     * @return Response after executing command.
     * @throws MickeyException Exception for invalid commands.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MickeyException {
        List<Task> foundTasks = tasks.findTasks(getDescription());
        StringBuilder result = new StringBuilder();
        if (foundTasks.size() > 0) {
            result.append("Oh boy! I found ").append(foundTasks.size()).append(" matching tasks:");
            for (int i = 0; i < foundTasks.size(); i++) {
                result.append("\t\t").append(i + 1).append(". ").append(foundTasks.get(i));
            }
        } else {
            result.append("Oops! No task found");
        }
        return result.toString();
    }
}
