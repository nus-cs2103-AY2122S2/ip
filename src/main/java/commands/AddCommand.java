package commands;

import task.ListTask;
import task.Task;

public class AddCommand extends Command {

    public static final String COMMAND_WORD = "task";
    public static final String MESSAGE_ADD = "Got it. I've added this task:\n";

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(ListTask tasks) {
        tasks.addTask(task);

        return MESSAGE_ADD + task.toString() + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    @Override
    public String toString() {
        return COMMAND_WORD;
    }
}
