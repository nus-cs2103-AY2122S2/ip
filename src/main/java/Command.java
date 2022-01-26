public abstract class Command {
    public abstract boolean exec(TaskList taskList, Ui ui, Storage storage);
}
