public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        ui.sayBye();
    }
}
