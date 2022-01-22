public class DeleteCommand extends Command{
    public DeleteCommand(Task task) {
        super(task, null);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.deleteFromList(super.task);
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
