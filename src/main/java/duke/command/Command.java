package duke.command;
import duke.task.*;
import duke.util.*;

public abstract class Command {
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage);
}
