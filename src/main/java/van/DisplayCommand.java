package van;

public class DisplayCommand implements Command {
    public boolean executeCommand(Ui ui, TaskList taskList, Storage storage) {
        ui.printList(taskList.getList());
        return false;
    }
}
