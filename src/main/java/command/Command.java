package command;
import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/** Abstract class for categorising different Commands. */
public abstract class Command {
    public abstract void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException;
}
