public class DeadlineCommand extends Command {
    protected Task deadline;

    public DeadlineCommand(String description, String by) {
        this.deadline = new Deadline(description, by);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.add(deadline);
            storage.store(tasks);
            ui.showResponseMessage("deadline");
            ui.showTaskMessage(deadline);
            ui.printTasksCountMessage(tasks);
        } catch (DukeException e) {
            // e.printStackTrace();
        }
    }
}
