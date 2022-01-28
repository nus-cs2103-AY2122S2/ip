public class ExitCommand extends Command {

    @Override
    public void execute(TaskMaster tasks, Ui ui, Storage storage) {
        this.startExit();
        ui.goodbye();
    }
}
