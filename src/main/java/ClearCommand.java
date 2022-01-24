public class ClearCommand extends Command {
    public ClearCommand() {}

    @Override
    public void activate() {
        TaskList.clearTasks();
    }
}
