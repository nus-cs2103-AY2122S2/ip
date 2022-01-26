public class UnmarkCommand extends Command {

    int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) {
        Task unmarkedTask = taskList.unmark(index);
        storage.saveUpdatedTask(index, unmarkedTask);
        return true;
    }
}
