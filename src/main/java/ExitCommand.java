public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
