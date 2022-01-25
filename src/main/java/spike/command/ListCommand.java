package spike.command;

import spike.task.Task;
import spike.task.TaskList;

import java.time.LocalDateTime;

/**
 * Lists tasks according to user requirement.
 */
public class ListCommand extends Command{
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
            return listTask(tasks);
        } else {
            return listTaskByDate(tasks);
        }
    }

    private String listTask(TaskList tasks) {
        int i = 1;
        String result = "Here are the task(s) in your list:\n";
        for (Task task : tasks.getTasks()) {
            result += i + "." + task;
            if (i != tasks.getTasks().size()) {
                result += "\n";
            }
            i++;
        }
        return result;
    }

    private String listTaskByDate(TaskList tasks) {
        int i = 1;
        String result = "Here are the task(s) in your list filtered by date:\n";
        for (Task task : tasks.getTasks()) {
            if (task.getDateTime().toLocalDate().equals(ldt.toLocalDate())) {
                result = result + i + "." + task;
                if (i != tasks.getTasks().size()) {
                    result += "\n";
                }
            }
            i++;
        }
        return result;
    }
}
