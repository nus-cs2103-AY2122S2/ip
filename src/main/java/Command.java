public abstract class Command {

    public abstract void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException;

    public abstract boolean isExit();
}
