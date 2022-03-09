package duke.command;
import duke.TasksList;
import duke.Storage;
import duke.exception.InvalidArgumentException;
import duke.exception.InvalidIndexException;

public abstract class Command {
    public abstract String execute(TasksList taskList, Storage storage) throws InvalidArgumentException,
            InvalidIndexException;
}
