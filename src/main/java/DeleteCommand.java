public class DeleteCommand extends Command {
    private final int deleteIndex;

    DeleteCommand(int deleteIndex) {
        super();
        this.deleteIndex = deleteIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task delete = tasks.getByIndex(deleteIndex);
        tasks = tasks.remove(deleteIndex);
        storage.saveTaskList(tasks);
        ui.showMessage("Noted. I've removed this task: \n        "
                + delete + "\n    Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
