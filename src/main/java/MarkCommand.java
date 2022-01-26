
public class MarkCommand extends Command {
    protected int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.index > 0 && this.index <= tasks.size()) {
            ui.showResponseMessage("mark");
            Task task = tasks.get(index - 1);
            task.mark();
            ui.showTaskMessage(task);
        } else {
            ui.showInvalidRange();
        }
    }

}
