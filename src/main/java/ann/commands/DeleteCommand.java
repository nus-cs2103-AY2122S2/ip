package ann.commands;

public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int i) {
        index = i;
    }

    @Override
    public void executeCommand() {
        String deleteTaskMessage = super.taskList.removeTaskAtIndex(index);
        super.setMessage(deleteTaskMessage);
    }
}