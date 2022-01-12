public class ExitCommand extends Command {
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        ui.fareWell();
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
