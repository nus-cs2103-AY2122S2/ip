public class ListCommand extends Command {

    ListCommand(String[] cmdArr) {
        super(cmdArr[0]);
    }

    @Override
    public void execute(TaskList taskList, TessUi ui, Storage storage) {
        ui.listTasks(taskList.toString());
    };
}
