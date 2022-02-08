package spike.command;

import spike.task.Task;
import spike.task.TaskList;

/**
 * Finds task based on keyword given.
 */
public class FindCommand extends Command {
    public static final String MSG_MATCHING_TASK = "Here are the matching tasks in your list:\n";
    private String keyword;

    /**
     * Constructor using keyword string.
     *
     * @param keyword the word used to find tasks
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns tasks matching the keyword supplied.
     *
     * @param tasks current task list
     * @return matching tasks in string
     */
    @Override
    public String execute(TaskList tasks) {
        assert keyword != null : "Keyword should not be null";
        int i = 1;
        String result = MSG_MATCHING_TASK;
        for (Task task : tasks.getTasks()) {
            if (task.toString().contains(keyword)) {
                result = result + i + "." + task + "\n";
            }
            i++;
        }
        return result.trim();
    }
}
