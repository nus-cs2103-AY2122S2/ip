public class ListCommand extends Command{

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.print("Here are your tasks:");
        for (int i = 0; i < taskList.tasks.size(); i++) {
            ui.print(i + 1 + "." + taskList.tasks.get(i).toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
