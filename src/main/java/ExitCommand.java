public class ExitCommand extends Command {
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
        return false;
    }
}
