public class TodoCommand extends Command {

    private final String description;

    public TodoCommand(String description) {
        super(Keyword.TODO);
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Todo(description);
        tasks.add(t);
        ui.showMessage("Got it. I've added this task:\n" + t
                    + "\nNow you have " + tasks.size() + " tasks in your list.");
    }

}
