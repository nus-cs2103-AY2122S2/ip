public abstract class Command {
    public abstract void execute(TaskList tasks, Ui userInt, Storage storage);

    public abstract boolean isExit();
}
