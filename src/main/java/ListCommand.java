public class ListCommand extends Command {
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.readFromFile();
        ui.showMessage("YOUR TASKS:" + taskList);
        return true;
    }
}
