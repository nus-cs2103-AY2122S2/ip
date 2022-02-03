package command;
import exception.DukeException;
import storage.Storage;
import task.TaskList;

/** Abstract class for categorising different Commands. */
public abstract class Command {
    public abstract String execute(Storage storage, TaskList taskList) throws DukeException;
}
