public abstract class Command {
    public abstract void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException;
    public boolean isBye() {
        return false;
    }
}
