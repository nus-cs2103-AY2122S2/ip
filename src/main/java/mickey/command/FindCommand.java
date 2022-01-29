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
     * @throws MickeyException Exception for invalid commands.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MickeyException {
        List<Task> foundTasks = tasks.findTasks(getDescription());
        if (foundTasks.size() > 0) {
            System.out.println("\tOh boy! I found " + foundTasks.size() + " matching tasks:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println("\t\t" + (i + 1) + ". " + foundTasks.get(i));
            }
        } else {
            System.out.println("\tOops! No task found");
        }
    }
}
