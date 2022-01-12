package duke.command;
import java.io.IOException;
import duke.task.*;
import duke.ui.*;
import duke.storage.*;

public abstract class Command {
    public abstract void execute(TaskList task, Ui ui, Storage storage) throws IOException;

    public abstract boolean isExit();
}
