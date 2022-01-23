package duke.command;

import duke.helpTool.DukeException;
import duke.helpTool.Storage;
import duke.helpTool.TaskList;
import duke.helpTool.Ui;

public class UnmarkCommand extends Command {
    private final int currTask;

    public UnmarkCommand(int currTask){
        this.currTask = currTask;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (currTask - 1 < 0 || (currTask > tasks.getSize())){
                throw new DukeException("☹ OOPS!!! The task of a unmark cannot be reached.");
            }
            tasks.getTask(currTask - 1).markAsNotDone();
            ui.showSuccessUnmark(tasks.getTask(currTask - 1).toString());
            storage.write(tasks);
        } catch (DukeException e){
            ui.showExceptionError(e);
        }
    }
}