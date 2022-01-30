package athena.commands;

import athena.exceptions.InputException;
import athena.tasks.TaskList;
import athena.ui.Ui;

public abstract class Command {
    public abstract void execute(Ui ui, TaskList tasklist) throws InputException;
}
