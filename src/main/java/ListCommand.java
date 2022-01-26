
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasksCountMessage(tasks);
        ui.showListOfTasksMessage(tasks);
    }
}
