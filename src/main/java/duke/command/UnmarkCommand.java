package duke.command;
import duke.task.*;
import duke.util.*;

public class UnmarkCommand extends Command {

    protected int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        Task tsk = tasklist.get(index);
        tsk.unmark();
        Ui.unmark(tsk);
    }
}
