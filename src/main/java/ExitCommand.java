public class ExitCommand extends Command {
    ExitCommand() {
        super();
    }

    @Override public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    @Override public boolean isExit() {
        return true;
    }
}
