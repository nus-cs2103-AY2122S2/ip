public class ListCommand implements Command {

    public ListCommand() {}

    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listTasks();
        return false;
    }
}
