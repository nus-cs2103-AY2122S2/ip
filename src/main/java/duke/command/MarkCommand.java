package duke.command;

import duke.task.TaskList;

public class MarkCommand extends Command {

    private final int index;

    /**
     * Constructs a {@code MarkCommand} object.
     * @param index the index of the task to mark as done
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the specified index in the list of tasks as done.
     * @param tasks current list of tasks
     */
    @Override
    public String execute(TaskList tasks) {
        if (index > tasks.size() || index <= 0) {
            return "Invalid index";
        }
        tasks.set(index - 1, tasks.get(index - 1).mark());
        return "Nice! I've marked this task as done:\n"
                + tasks.get(index - 1);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
