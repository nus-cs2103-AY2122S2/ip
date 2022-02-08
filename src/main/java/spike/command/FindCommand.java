package spike.command;

import java.util.concurrent.atomic.AtomicInteger;

import spike.task.TaskList;

/**
 * Finds task based on keyword given.
 */
public class FindCommand extends Command {
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
        AtomicInteger i = new AtomicInteger(1);
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
        tasks.getTasks().stream().forEach(task -> {
            if (task.toString().contains(keyword)) {
                result.append(i + "." + task + "\n");
            }
            i.getAndIncrement();
        });
        return result.toString().trim();
    }
}
