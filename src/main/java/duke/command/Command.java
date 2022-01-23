package duke.command;

import duke.helpTool.Storage;
import duke.helpTool.TaskList;
import duke.helpTool.Ui;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
