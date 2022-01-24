public class DeleteCommand extends Command {
    private final int targetIndex;

    public DeleteCommand(int i) {
        targetIndex = i;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.deleteTask(targetIndex);

        String s = "No problem, I've deleted that task for you:\n\n" +
                t.toString() + "\n\n" +
                "You now have " + tasks.listSize() + " task(s) remaining on your list.";
        return s;
    }

    public boolean isExit() {
        return false;
    }
}
