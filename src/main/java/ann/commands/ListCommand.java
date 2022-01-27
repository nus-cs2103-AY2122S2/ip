package ann.commands;

public class ListCommand extends Command{

    public ListCommand() {

    }

    @Override
    public void executeCommand() {
        super.setMessage(super.taskList.getTasksString());
    }
}