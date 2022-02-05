public class ListCommand extends Command {

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.showTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
