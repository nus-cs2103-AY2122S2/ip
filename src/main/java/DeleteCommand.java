public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskIndexException {
        ui.showDelete(taskList.getTask(this.index));
        taskList.delete(this.index);
        storage.updateList(taskList);
    }
}
