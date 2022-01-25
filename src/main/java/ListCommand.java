public class ListCommand extends Command {
    public ListCommand(String[] commandArray) {
        super(commandArray);
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.showUiForTaskList(taskList);
    }
}
