public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = taskList.deleteTask(index);
        ui.displayDeletedTask(deletedTask);
        ui.displayNumberOfTasks(taskList.getTasks());
    }
}
