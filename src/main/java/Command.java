public interface Command {
    public void execute(UltoiUi ui, TaskList tasks, Storage storage) throws UltoiException;
}
