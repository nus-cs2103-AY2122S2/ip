package duke.command;

import duke.task.TaskList;

public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a {@code DeleteCommand} object.
     * @param index the index of the task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task at the specified index in the list of tasks.
     * @param tasks current list of tasks
     */
    @Override
    public String execute(TaskList tasks) {
        if (index <= tasks.size()) {
            String response = "Noted. I've removed this task:\n  " + tasks.get(index);
            tasks.remove(index);
            return response;
        } else {
            return "Index is invalid";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
