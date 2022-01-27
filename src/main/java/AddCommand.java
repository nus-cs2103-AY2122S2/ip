public class AddCommand extends Command{
    private final Task t;

    AddCommand(Task task) {
        super();
        this.t = task;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(t);
        storage.record(t + "\n");
        ui.respond("Got it. I've added this task:\n  " +
                    t + "\nNow you have " + tasks.size() + " tasks in the list.");

    }

}
