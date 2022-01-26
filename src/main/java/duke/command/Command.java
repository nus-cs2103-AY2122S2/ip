package duke.command;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public abstract class Command {
    public abstract boolean exec(TaskList taskList, Ui ui, Storage storage);
}
