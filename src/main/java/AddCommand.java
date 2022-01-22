public class AddCommand extends Command{
    public AddCommand(Task task) {
        super(task,null);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addToList(super.task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
