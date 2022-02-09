package duke;

import java.io.IOException;

/**
 * Represents a mark command
 */
public class MarkCommand extends Command {

    private final int taskNum;

    /**
     * Constructs an instance of the MarkCommand class.
     *
     * @param command A string containing the "mark" word.
     * @param taskNum An integer representing the task number to mark.
     */
    public MarkCommand(String command, int taskNum) {
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
        tasks.set(taskIdx, tasks.get(taskIdx).mark());
        ui.showMessage("Nice! I've marked this task as done:\n  "
                + tasks.get(taskIdx));
        storage.update(tasks);
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
        }
    }
}
