package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TasksList;


public abstract class Command {
    public abstract String execute(TasksList taskList, Storage storage) throws DukeException;
}
