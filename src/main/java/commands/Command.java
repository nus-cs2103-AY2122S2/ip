package commands;

import tasklist.TaskList;
import ui.Ui;

public abstract class Command {
    public abstract void execute(Ui ui, TaskList taskList);
    public abstract boolean isExit();
}
