public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task selectedTask = taskList.getTask(index);
        selectedTask.markAsComplete();
        ui.displayMarkedTask(selectedTask);
    }
}
