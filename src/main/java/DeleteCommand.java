public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task temp = tasks.at(idx + 1);
        tasks.delete(idx);
        storage.updateAfterDelete(idx);
        ui.deleteMessage(temp, tasks.length());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
