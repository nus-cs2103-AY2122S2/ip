package van;

public class ExitCommand implements Command {

    public boolean executeCommand(Ui ui, TaskList taskList, Storage storage) {
        ui.printDivider();
        storage.saveTasks(taskList.getList());
        ui.exitMessage();
        return true;
    }
}
