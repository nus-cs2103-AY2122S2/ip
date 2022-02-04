package spike.command;

import java.time.LocalDateTime;

import spike.task.Task;
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
        int i = 1;
        String result = "Here are the task(s) in your list:\n";
        for (Task task : tasks.getTasks()) {
            result += i + "." + task + "\n";
            i++;
        }
        return result.trim();
    }

    /**
     * Lists all tasks by date.
     *
     * @param tasks
     * @return all task filtered by date
     */
    private String getTaskListTextByDate(TaskList tasks) {
        int i = 1;
        String result = "Here are the task(s) in your list filtered by date:\n";
        for (Task task : tasks.getTasks()) {
            if (task.getDateTime().toLocalDate().equals(ldt.toLocalDate())) {
                result = result + i + "." + task + "\n";
            }
            i++;
        }
        return result.trim();
    }
}
