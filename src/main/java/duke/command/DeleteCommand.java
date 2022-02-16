package duke.command;
import duke.task.*;
import duke.util.*;

public class DeleteCommand extends Command {

    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        Task tsk = tasklist.get(index);
        Ui.delete(tsk, tasklist.size()-1);
        tasklist.delete(index);
    }
}
