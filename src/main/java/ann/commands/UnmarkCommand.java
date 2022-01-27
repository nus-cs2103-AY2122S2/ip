package ann.commands;

public class UnmarkCommand extends Command{
    private int index;

    public UnmarkCommand(int i) {
        index = i;
    }

    @Override
    public void executeCommand() {
        String unmarkTaskMessage = super.taskList.unmarkTaskAtIndex(index);
        super.setMessage(unmarkTaskMessage);
    }
}