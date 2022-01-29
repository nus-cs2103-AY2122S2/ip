public abstract class Command {
    public abstract void execute(Ui ui, TaskList tasklist) throws InputException;
}
