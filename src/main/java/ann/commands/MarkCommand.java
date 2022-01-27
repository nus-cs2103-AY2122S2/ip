package ann.commands;

public class MarkCommand extends Command{
    private int index;

    public MarkCommand(int i) {
        index = i;
    }

    @Override
    public void executeCommand() {
        String markTaskMessage = super.taskList.markTaskAtIndex(index);
        super.setMessage(markTaskMessage);
    }
}