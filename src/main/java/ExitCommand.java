public class ExitCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
    }

    @Override
    public boolean isExit() {
        return !super.isExit();
    }
}
