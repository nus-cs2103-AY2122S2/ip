package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Represents command to mark a task in the task list.
 *
 */
public class MarkCommand extends Command {
    protected boolean isMark;
    protected Integer index;

    /**
     * Constructor for MarkCommand class
     * @param index Index of the Task that is marked.
     * @param mark Whether the Task is marked as completed or uncompleted.
     */
    public MarkCommand(Integer index, boolean mark) {
        this.isMark = mark;
        this.index = index - 1;
    }
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) throws IOException {
        task.mark(this.index, this.isMark);
        ui.markList(task, this.isMark, this.index);
        storage.overWriteFile(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
