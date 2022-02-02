public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task temp = tasks.at(idx);
        tasks.at(idx).unmark();
        storage.updateAfterUnmark(idx);
        ui.unmarkMessage(temp);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
