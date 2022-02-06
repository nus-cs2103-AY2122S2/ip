package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class TodoCommand extends Command {

    private final String description;

    /**
     * Constructs a {@code TodoCommand} object.
     * @param description the description of the todo task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a todo task into the list of tasks.
     * @param tasks current list of tasks
     */
    @Override
    public String execute(TaskList tasks) {
        Task t = new Todo(description);
        tasks.add(t);
        return "Got it. I've added this task:\n" + t
                    + "\nNow you have " + tasks.size() + " tasks in your list.";
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
