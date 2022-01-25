public interface Command {

    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws Exception;
    public abstract boolean isExit();
}
