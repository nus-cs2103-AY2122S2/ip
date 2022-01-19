package duke.command;
import java.io.IOException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public abstract class Command {
    public abstract void execute(TaskList task, Ui ui, Storage storage) throws IOException;

    public abstract boolean isExit();
}
