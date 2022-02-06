package duke.command;

import duke.task.TaskList;

public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Constructs an {@code UnmarkCommand} object.
     * @param index the index of the task to unmark
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Unmarks the task at the specified index in the list of tasks.
     * @param tasks current list of tasks
     */
    @Override
    public String execute(TaskList tasks) {
        if (index > tasks.size() || index <= 0) {
            return "Invalid index";
        }
        tasks.set(index - 1, tasks.get(index - 1).unmark());
        return "OK, I've marked this task as not done yet:\n"
                + tasks.get(index - 1);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
