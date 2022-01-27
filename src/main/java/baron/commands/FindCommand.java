package baron.commands;

import baron.exceptions.BaronException;
import baron.tasks.TaskManager;

/**
 * Represents the find command and filter the task list by keyword.
 */
public class FindCommand extends Command {
    private final TaskManager taskManager;
    private final String keyword;

    /**
     * Constructs a find command with the specified {@code TaskManager} and a keyword.
     *
     * @param taskManager the {@code TaskManager} for the command execution.
     * @param keyword the keyword to filter the task list by.
     */
    public FindCommand(TaskManager taskManager, String keyword) {
        this.taskManager = taskManager;
        this.keyword = keyword;
    }

    /**
     * Executes the find command by filtering the task list in {@code TaskManager} with
     * the given keyword.
     *
     * @return the string representation of the filtered task list.
     */
    @Override
    public String execute() {
        if (this.keyword.isBlank()) {
            return (new BaronException("Please provide a keyword!")).toString();
        }
        return "Here are the matching tasks in your list:\n"
                + (new TaskManager(this.taskManager.filterDescByKeyword(this.keyword))).toString();
    }
}
