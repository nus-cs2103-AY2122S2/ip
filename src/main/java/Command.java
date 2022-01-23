public abstract class Command {
    protected boolean exit = false;
    public abstract void execute(TaskList tasks, Ui io, Storage storage) throws DukeException;
    public boolean isExit() {
        return exit;
    }
}
