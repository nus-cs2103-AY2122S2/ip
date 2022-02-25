public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        storage.updateStorage(tasks);
        ui.showFarewell();
    }
}
