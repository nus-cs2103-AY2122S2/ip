public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
