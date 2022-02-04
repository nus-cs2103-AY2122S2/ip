package gene.command;

import gene.component.Storage;
import gene.component.TaskList;
import gene.component.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui userInt, Storage storage);

    public abstract boolean isExit();
}
