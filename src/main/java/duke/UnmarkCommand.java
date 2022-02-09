package duke;

import java.io.IOException;

/**
 * Represents an unmark command.
 */
public class UnmarkCommand extends Command {

    private final int taskNum;

    /**
     * Constructs an instance of the UnmarkCommand class.
     *
     * @param command A string containing the "unmark" word.
     * @param taskNum An integer representing the task number to unmark.
     */
    public UnmarkCommand(String command, int taskNum) {
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
            tasks.set(taskIdx, tasks.get(taskIdx).unmark());
            ui.showMessage("Nice! I've marked this task as not done yet:\n  "
                    + tasks.get(taskIdx));
            storage.update(tasks);
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
        }
    }
}
