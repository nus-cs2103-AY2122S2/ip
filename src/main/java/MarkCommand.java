public class MarkCommand extends Command {

    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(index);
        task.markAsDone();
        ui.showTaskMarked(task);
        storage.saveData(taskList, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
