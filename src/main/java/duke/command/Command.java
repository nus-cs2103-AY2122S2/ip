package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isExit();
}
