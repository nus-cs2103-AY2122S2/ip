public class ExitCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.print("Hope to see you again!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
