public class ExitCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.farewell();  //includes closing scanner
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
