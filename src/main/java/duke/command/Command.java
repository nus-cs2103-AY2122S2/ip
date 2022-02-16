package duke.command;

import duke.DukeException;

public abstract class Command {
    public abstract String execute() throws DukeException;
}
