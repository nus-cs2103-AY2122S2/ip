public abstract class Command {

    abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit() {
        return false;
    }

}
