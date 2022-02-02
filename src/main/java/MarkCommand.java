public class MarkCommand extends Command {
    private int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task temp = tasks.at(idx);
        tasks.at(idx).mark();
        storage.updateAfterMark(idx);
        ui.markMessage(temp);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
