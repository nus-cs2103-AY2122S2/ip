public class ListCommand extends Command {
    ListCommand() {
        super("List");
    }

    public void execute(Storage storage, TaskList taskList, Ui ui) {
        taskList.viewTaskList();
    }
}
