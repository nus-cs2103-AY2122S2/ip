package spike.command;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import spike.task.TaskList;

/**
 * Lists tasks according to user requirement.
 */
public class ListCommand extends Command {
    private int action;
    private LocalDateTime ldt;

    /**
     * Default constructor for convenience
     */
    public ListCommand() {
        this.action = 1;
        this.ldt = null;
    }

    /**
     * Constructor for listing task by date
     *
     * @param action type of filtering for listing task
     * @param ldt date and time for filtering
     */
    public ListCommand(int action, LocalDateTime ldt) {
        this.action = action;
        this.ldt = ldt;
    }

    /**
     * Returns tasks satisfying filtering condition in string.
     *
     * @param tasks current task list
     * @return tasks satisfying filtering condition in string
     */
    @Override
    public String execute(TaskList tasks) {
        if (action == 1) {
            return getTaskListText(tasks);
        } else {
            return getTaskListTextByDate(tasks);
        }
    }

    /**
     * Lists all tasks.
     *
     * @param tasks
     * @return all tasks
     */
    private String getTaskListText(TaskList tasks) {
        AtomicInteger i = new AtomicInteger(1);
        StringBuilder result = new StringBuilder("Here are the task(s) in your list:\n");
        tasks.getTasks().stream().forEach(task -> {
            result.append(i + "." + task + "\n");
            i.getAndIncrement();
        });
        return result.toString().trim();
    }

    /**
     * Lists all tasks by date.
     *
     * @param tasks
     * @return all task filtered by date
     */
    private String getTaskListTextByDate(TaskList tasks) {
        AtomicInteger i = new AtomicInteger(1);
        StringBuilder result = new StringBuilder("Here are the task(s) in your list filtered by date:\n");
        tasks.getTasks().stream().forEach(task -> {
            if (task.getDateTime().toLocalDate().equals(ldt.toLocalDate())) {
                result.append(i + "." + task + "\n");
            }
            i.getAndIncrement();
        });
        return result.toString().trim();
    }
}
