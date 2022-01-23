package spike.command;

import spike.task.Task;
import spike.task.TaskList;

public class ToggleMarkCommand extends Command {
    private Task task;
    private int action;

    public ToggleMarkCommand(int action, Task task) {
        this.action = action;
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks) {
        if (action == 1) {
            task.markAsDone();
            return "Great! One more task done:\n" + task.toString();
        } else {
            task.markAsNotDone();
            return "Oops, I've marked this task as not done yet:\n" + task.toString();
        }
    }
}
