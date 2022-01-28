package meep.commands;

import meep.task.ListTask;

/**
 * Finds tasks match given keyword in task list.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_FOUND = "Got it. I've found matched tasks:\n";
    public static final String MESSAGE_NOT_FOUND = "Sorry. There are no matched task";

    private final String keyword;

    /**
     * Constructor for class FindCommand.
     *
     * @param keyword the keyword to search.
     */

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }


    /**
     * Returns successfully found message.
     * If not found any matched task, return not found message.
     *
     * @param tasks task list.
     * @return found/not found message.
     */
    @Override
    public String execute(ListTask tasks) {
        ListTask result = tasks.findTasks(keyword);
        if (result.size() == 0) {
            return MESSAGE_NOT_FOUND;
        } else {
            return MESSAGE_FOUND + result.generateTaskList();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
