package duke.command;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;
import duke.manager.Ui;

public class MarkCommand extends Command {
    private boolean isMark;
    private int taskNo;

    public MarkCommand(boolean isMark, int taskNo) {
        super();
        this.isMark = isMark;
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (isMark) {
            taskList.markDone(taskNo);
            ui.print("Nice! I've marked this task as done:");
            ui.print(taskList.getTask(taskNo).toString());
        } else {
            taskList.markUndone(taskNo);
            ui.print("OK, I've marked this task as not done yet:");
            ui.print(taskList.getTask(taskNo).toString());
        }
        try {
            storage.save(taskList);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
