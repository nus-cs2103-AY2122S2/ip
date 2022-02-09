package duke;

import java.io.IOException;

/**
 * Represents a delete command, which deletes tasks from the task list.
 */
public class DeleteCommand extends Command {

    private final int taskNum;

    /**
     * Constructs an instance of the AddCommand class.
     *
     * @param command A string containing the word "delete".
     * @param taskNum An integer representing the task number to delete.
     */
    public DeleteCommand(String command, int taskNum) {
        super(command);
        this.taskNum = taskNum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            int taskIdx = taskNum - 1;
            if (taskIdx >= tasks.size() || taskIdx < 0) {
                throw new DukeException("Please choose a valid task! (Your list has "
                        + tasks.size() + " tasks)");
            }
            Task deletedTask = tasks.remove(taskIdx);
            ui.showMessage("Noted. I've removed this task:\n  "
                    + deletedTask
                    + "\n Now you have " + tasks.size() + " tasks in the list.");
            storage.update(tasks);
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
        }
    }
}
