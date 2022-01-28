public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.showDelete(tasks.delete(index));
        ui.showNumberOfTasks(tasks);
    }
}
