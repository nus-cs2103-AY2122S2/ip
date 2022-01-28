public class ListCommand extends Command {

    @Override
    public void execute(TaskMaster tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks.get_tasks());
    }
}
