public class ListCommand extends Command {

    String[] listCommand;

    public ListCommand(String[] listCommand) {
        this.listCommand = listCommand;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.printListOfTasks(taskList);
    }
}
