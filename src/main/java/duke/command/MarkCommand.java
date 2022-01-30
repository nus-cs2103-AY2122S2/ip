package duke.command;

import duke.logic.DukeException;
import duke.logic.Storage;
import duke.task.Task;
import duke.logic.TaskList;
import duke.logic.Ui;

public class MarkCommand extends Command {
    private final int index;
    private final boolean isMark;

    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(index);
        if (isMark) {
            task.markAsDone();
            ui.showMessage("TASK DONE:\n" + task);
        } else {
            task.markAsUndone();
            ui.showMessage("TASK UNDONE:\n" + task);
        }
        storage.writeToFile(taskList);
        return true;
    }
}
