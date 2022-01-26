public class ListCommand extends Command {
    ListCommand(){}

    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) {
        taskList.list();
        return true;
    }
}
