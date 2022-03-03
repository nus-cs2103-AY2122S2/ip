package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates command to mark task as completed
 */
public class MarkCommand implements Command {
    /**
     * The index of task to be marked as completed.
     */
    private final int index;

    /**
     * Instantiates a new Mark command.
     *
     * @param index the index of the task
     */
    public MarkCommand(int index) {
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
            tasks.markDone(index, true);
            output += "OK. I've marked this task as done:\n" + tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            output = "Please enter a valid task. Task " + this.index + " does not exist.";
        }

        return output;
    }
}
