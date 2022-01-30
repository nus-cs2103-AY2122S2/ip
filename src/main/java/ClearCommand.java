public class ClearCommand extends Command {
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.clearFile();
        taskList.clear();
        ui.showMessage("ALL TASKS CLEARED");
        return true;
    }
}
