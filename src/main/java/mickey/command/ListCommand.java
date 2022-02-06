package mickey.command;

import mickey.app.MickeyException;
import mickey.app.Storage;
import mickey.app.Ui;
import mickey.task.TaskList;

/**
 * List command to display tasks.
 */
public class ListCommand extends Command {
    /**
     * Constructor.
     *
     * @param fullCommand User input command.
     */
    public ListCommand(String fullCommand) {
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
        StringBuilder result = new StringBuilder();
        if (tasks.size() > 0) {
            result.append("Oh boy! You have ").append(tasks.size()).append(" tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                result.append("\n\t").append(i + 1).append(". ").append(tasks.get(i));
            }
        } else {
           result.append("Hooray! You have no tasks");
        }
        return result.toString();
    }
}
