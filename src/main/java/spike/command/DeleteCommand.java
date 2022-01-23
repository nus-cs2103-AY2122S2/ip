package spike.command;

import spike.task.Task;
import spike.task.TaskList;

public class DeleteCommand extends Command {
    private Task task;

    public DeleteCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.getTasks().remove(task);
        return printDeletedTask(task, tasks);
    }

    /**
     * Returns the message needed for printing when deleting task.
     */
    public String printDeletedTask(Task task, TaskList tasks) {
        String result = " Noted. I've removed this task: \n"
                + String.format("    %s\n", task)
                + String.format("Now you have %s task(s) in the list.", tasks.getTasks().size());
        return result;
    }
}

