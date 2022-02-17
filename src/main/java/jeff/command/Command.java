package jeff.command;

import jeff.main.JeffException;
import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.ui.Ui;

/**
 * Command class acts as a format for other Command classes.
 */
public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws JeffException;

    public abstract boolean isExit();
}
