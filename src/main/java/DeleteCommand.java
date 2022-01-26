public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(index);
        storage.saveUpdatedTask(index, null);
        return true;
    }
}
