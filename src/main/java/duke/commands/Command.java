package duke.commands;

import duke.exceptions.InvalidOperationException;

public abstract class Command {

    public abstract void execute() throws InvalidOperationException;

}
