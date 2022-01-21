public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_MARK = "Nice! I've marked this task as done:\n";

    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(ListTask tasks) {
        Task task = tasks.get(index);
        task.markDone();
        return MESSAGE_MARK+task.toString();
    }

    @Override
    public String toString() {
        return COMMAND_WORD;
    }
}
