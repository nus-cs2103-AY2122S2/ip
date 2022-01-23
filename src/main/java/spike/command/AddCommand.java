package spike.command;

import spike.task.Task;
import spike.task.TaskList;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.addTask(task);
        return printAddedTask(task, tasks);
    }

    /**
     * Returns the message needed for printing when adding task.
     */
    public String printAddedTask(Task task, TaskList tasks) {
        String result = "Got it. I've added this task:\n"
                + String.format("    %s\n", task.toString())
                + String.format("Now you have %s task(s) in the list.", tasks.getTasks().size());
        return result;
    }
}

