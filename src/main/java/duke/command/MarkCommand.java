package duke.command;

import duke.helptool.DukeException;
import duke.helptool.Storage;
import duke.helptool.TaskList;
import duke.helptool.Ui;

/**
 * The type Mark command.
 */
public class MarkCommand extends Command {
    private final int currTask;

    /**
     * Instantiates a new Mark command.
     *
     * @param currTask the curr task
     */
    public MarkCommand(int currTask) {
        this.currTask = currTask;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (currTask - 1 < 0 || (currTask > tasks.getSize())) {
                throw new DukeException("☹ OOPS!!! The task of a mark cannot be reached.");
            }
            tasks.getTask(currTask - 1).markAsDone();
            ui.showSuccessMark(tasks.getTask(currTask - 1).toString());
            storage.write(tasks);
        } catch (DukeException e) {
            ui.showExceptionError(e);
        }
    }
}
