public class ListCommand extends Command {
    public boolean isExit() {
        return false;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.showTaskList(tasks);
    }
}
