package spike.command;

import spike.task.Task;
import spike.task.TaskList;

/**
 * Finds task based on keyword given.
 */
public class FindCommand extends Command {
    private String keyWord;

    /**
     * Constructor using keyword string.
     *
     * @param keyWord the word used to find tasks
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Returns tasks matching the keyword supplied.
     *
     * @param tasks current task list
     * @return matching tasks in string
     */
    @Override
    public String execute(TaskList tasks) {
        int i = 1;
        String result = "Here are the matching tasks in your list:\n";
        for (Task task : tasks.getTasks()) {
            if (task.toString().contains(keyWord)) {
                result = result + i + "." + task + "\n";
            }
            i++;
        }
        return result.trim();
    }
}
