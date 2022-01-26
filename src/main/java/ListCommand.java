public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLongLine();
        tasks.sortTaskList();
        ui.printTaskList(tasks);
    }
}
