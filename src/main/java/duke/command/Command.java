package duke.command;

import duke.exception.InvalidArgumentException;
import duke.exception.InvalidIndexException;
import duke.util.Storage;
import duke.util.TasksList;


public abstract class Command {
    public abstract String execute(TasksList taskList, Storage storage) throws InvalidArgumentException,
            InvalidIndexException;
}
