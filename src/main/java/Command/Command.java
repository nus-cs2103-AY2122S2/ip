package Command;

import DukeUtils.*;

public abstract class Command {
    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException;
}
