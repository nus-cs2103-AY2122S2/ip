package duke.command;
import duke.task.*;
import duke.util.*;

public class ListCommand extends Command {

    public ListCommand() {
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        Ui.list(tasklist);
    }
}
