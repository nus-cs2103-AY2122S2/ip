public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task selectedTask = taskList.getTask(index);
        selectedTask.markAsIncomplete();
        ui.displayUnmarkedTask(selectedTask);
    }
}
