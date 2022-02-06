/**
 * Command class acts as a format for other Command classes.
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException;

    public abstract boolean isExit();
}
