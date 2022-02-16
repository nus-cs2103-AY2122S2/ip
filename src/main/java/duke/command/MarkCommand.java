package duke.command;
import duke.task.*;
import duke.util.*;

public class MarkCommand extends Command {

    protected int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        Task tsk = tasklist.get(index);
        tsk.mark();
        Ui.mark(tsk);
    }
}
