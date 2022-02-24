package duke;

/**
 * Abstract Class from which all commands in Duke inherit from.
 */
public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isEnd();
}
