package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates command to mark a task as incomplete.
 */
public class UnmarkCommand implements Command {
    /**
     * The index of the task to be marked as incompleted.
     */
    private final int index;

    /**
     * Instantiates a new Unmark command.
     *
     * @param index the index of the task
     */
    public UnmarkCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        String output = "";
        try {
            tasks.markDone(index, false);
            output += "OK. I've marked this task as not-yet-done:\n" + tasks.get(index).toString();
        } catch (IndexOutOfBoundsException e) {
            output = "Please enter a valid task. Task " + this.index + " does not exist.";
        }

        return output;
    }
}
