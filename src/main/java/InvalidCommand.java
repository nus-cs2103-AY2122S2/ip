public class InvalidCommand extends Command {
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("Invalid command.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
