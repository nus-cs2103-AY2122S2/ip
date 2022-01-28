public class ByeCommand extends Command {
    public boolean isExit() {
        return true;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        ui.showExit(storage, tasks);
    }
}
