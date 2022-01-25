abstract public class Command {
    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    public boolean isExit() {
        return false;
    }
}
