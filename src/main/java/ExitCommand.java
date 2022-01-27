public class ExitCommand extends Command{
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayFarewell();
    }
    public boolean isExit() {
        return true;
    }
}
