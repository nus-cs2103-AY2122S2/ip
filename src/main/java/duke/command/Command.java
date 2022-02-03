package src.main.java.duke.command;

import src.main.java.duke.DukeException;
import src.main.java.duke.Storage;
import src.main.java.duke.Ui;
import src.main.java.duke.TaskList;

/**
 * Command is an abstract class that specifies 2 methods that has to be
 * implemented by all the different commands, namely isExit and execute.
 */
public abstract class Command {

    public abstract boolean isExit();

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
