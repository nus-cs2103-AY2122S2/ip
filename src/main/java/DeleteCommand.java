public class DeleteCommand extends Command{
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_DELETE = "Noted. I've removed this task:\n";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(ListTask tasks) {
        Task task = tasks.get(index);
        tasks.deleteTask(index);
        return MESSAGE_DELETE+task.toString()+"\nNow you have " + tasks.size() + " tasks in the list.";
    }

    @Override
    public String toString() {
        return COMMAND_WORD;
    }
}
