public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public void execute(Ui ui, Storage storage) {
        ui.displayTaskList(storage.getTaskList());
    }
}
