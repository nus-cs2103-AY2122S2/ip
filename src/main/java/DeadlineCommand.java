public class DeadlineCommand extends Command {
    private Deadline deadline;

    public DeadlineCommand(String description, String by) {
        deadline = new Deadline(description, by);
    }

    public boolean isExit() {
        return false;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.add(deadline);
        ui.showTaskAdded(deadline);
        ui.showNumberOfTasks(tasks);
    }
}
