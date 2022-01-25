public class ChangeStatusCommand extends Command {
    TaskStatus ts;
    int index;

    public ChangeStatusCommand(TaskStatus ts, int index) {
        this.ts = ts;
        this.index = index;
    }

    @Override
    public void activate() {
        TaskList.markStatus(ts, index);
    }
}
