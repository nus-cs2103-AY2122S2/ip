package duke.command;

import duke.utils.CortanaException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public abstract class Command {
    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException;

    abstract public boolean isExit();
}
