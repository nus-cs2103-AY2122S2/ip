package duke.commands;

import duke.admin.Storage;
import duke.admin.TaskList;
import duke.exceptions.DukeException;

/**
 * Command is an abstract class that specifies 2 methods that has to be
 * implemented by all the different commands, namely isExit and execute.
 */
public abstract class Command {

    public abstract boolean isExit();

    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
