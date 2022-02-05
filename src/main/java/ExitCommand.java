public class ExitCommand extends Command {

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        ui.showExit(storage, tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
