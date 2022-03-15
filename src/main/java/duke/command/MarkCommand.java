package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command that marks a list in the user's task list as completed.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private int index;

    /**
     * Creates a new MarkCommand.
     *
     * @param index The index of the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the appropriate task as completed based on index, updates storage and notifies the user upon
     * successful completion.
     *
     * @param tasks List of user's tasks.
     * @param storage Handles storage of tasks in the user's memory.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            Task task = tasks.mark(index);
            storage.save(tasks);
            return Ui.showMarkTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task " + (index + 1) + " does not exist bro.");
        }
    }
}
