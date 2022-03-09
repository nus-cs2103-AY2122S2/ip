package duke.command;

import duke.Storage;
import duke.TasksList;
import duke.exception.InvalidArgumentException;
import duke.exception.InvalidIndexException;

public abstract class Command {
    public abstract String execute(TasksList taskList, Storage storage) throws InvalidArgumentException,
            InvalidIndexException;
}
