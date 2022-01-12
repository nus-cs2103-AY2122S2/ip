package duke.command;

import duke.task.*;
import duke.ui.*;
import duke.storage.*;

import java.io.IOException;

public class MarkCommand extends Command{
    protected boolean mark;
    protected Integer index;

    public MarkCommand(Integer index, boolean mark) {
        this.mark = mark;
        this.index = index-1;
    }
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) throws IOException {
        task.mark(this.index, this.mark);
        ui.markList(task, this.mark, this.index);
        storage.overWriteFile(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
