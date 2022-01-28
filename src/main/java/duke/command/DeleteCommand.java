package duke.command;

import duke.helptool.DukeException;
import duke.helptool.Storage;
import duke.helptool.TaskList;
import duke.helptool.Ui;

/**
 * The type Delete command.
 */
public class DeleteCommand extends Command {
    private final int currTask;

    public DeleteCommand(int currTask) {
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
                throw new DukeException("☹ OOPS!!! The task of a delete cannot be reached.");
            }
            String taskDeleted = tasks.getTask(currTask - 1).toString();
            tasks.delete(currTask - 1);
            ui.showDelete(taskDeleted, tasks.getSize());
            storage.write(tasks);
        } catch (DukeException e) {
            ui.showExceptionError(e);
        }
    }
}
