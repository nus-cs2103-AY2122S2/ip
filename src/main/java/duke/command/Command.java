package duke.command;

import duke.helptool.Storage;
import duke.helptool.TaskList;
import duke.helptool.Ui;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
