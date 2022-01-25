package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public abstract class Command {
    abstract void execute(TaskList tasks, Ui ui, Storage storage);
    abstract boolean isExit();
}
