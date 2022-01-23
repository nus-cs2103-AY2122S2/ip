public class MarkCommand extends Command {

    private final int index;

    public MarkCommand(int index) {
        super(Command.Keyword.MARK);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index <= tasks.size()) {
            tasks.set(index - 1, tasks.get(index - 1).mark());
            ui.showMessage("Nice! I've marked this task as done:\n"
                    + tasks.get(index - 1));
        } else {
            ui.showMessage("Index is invalid");
        }
    }

}
