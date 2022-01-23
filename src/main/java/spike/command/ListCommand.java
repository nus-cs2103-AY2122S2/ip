package spike.command;

import spike.Spike;
import spike.task.Task;
import spike.task.TaskList;

import java.time.LocalDateTime;

/**
 *
 */
// TODO need to put this with other as a package then I can assess the list in Spike class
public class ListCommand extends Command{
    private String command;
    private int action;
    private LocalDateTime ldt;

    public ListCommand() {
        this.action = 1;
        this.ldt = null;
    }

    public ListCommand(int action, LocalDateTime ldt) {
        this.action = action;
        this.ldt = ldt;
    }

    @Override
    public String execute(TaskList tasks) {
        if (action == 1) {
            return listTask(tasks);
        } else {
            return listTaskByDate(tasks);
        }
    }

    /**
     * Lists all tasks.
     *
     * @param tasks
     * @return
     */
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
