public class DeleteCommand extends Command {
    private final int task;

    public DeleteCommand(int task) {
        super();
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        try {
            Task t = tasks.remove(this.task);
            ui.showMessage("Noted. I've removed the task:");
            ui.showMessage(t.toString());
            storage.updateStorage(tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Could not delete task as it was not found.");
        }
    }
}
