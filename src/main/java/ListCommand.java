public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public void activate() {
        TaskList.viewTasks();
    }

}
