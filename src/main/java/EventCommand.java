public class EventCommand extends Command {

    private final String description;
    private final DukeDateTime by;

    public EventCommand(String description, DukeDateTime by) {
        super(Keyword.EVENT);
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Event(description, by);
        tasks.add(t);
        ui.showMessage("Got it. I've added this task:\n" + t
                + "\nNow you have " + tasks.size() + " tasks in your list.");
    }

}
