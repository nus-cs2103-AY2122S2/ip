package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public abstract class Command {

    abstract public boolean isExit();

    abstract public void execute(TaskList<Task> tasks, Ui ui, Storage storage);
}
