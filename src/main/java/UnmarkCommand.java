public class UnmarkCommand extends Command {

    private final int index;

    public UnmarkCommand(int index) {
        super(Command.Keyword.UNMARK);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index <= tasks.size()) {
            tasks.set(index - 1, tasks.get(index - 1).unmark());
            ui.showMessage("OK, I've marked this task as not done yet:\n"
                    + tasks.get(index - 1));
        } else {
            ui.showMessage("Index is invalid");
        }
    }

}
