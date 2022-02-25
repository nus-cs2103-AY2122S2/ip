abstract class Command {

    abstract public boolean isExit();

    abstract public void execute(TaskList<Task> tasks, Ui ui, Storage storage);
}
