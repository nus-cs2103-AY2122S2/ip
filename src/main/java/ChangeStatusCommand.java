public class ChangeStatusCommand extends Command {
    MarkStatus ms;
    int index;

    public ChangeStatusCommand(MarkStatus ms, int index) {
        this.ms = ms;
        this.index = index;
    }

    @Override
    public void activate() {
        TaskList.markStatus(ms, index);
    }
}
