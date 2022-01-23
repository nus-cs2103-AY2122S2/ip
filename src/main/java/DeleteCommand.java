public class DeleteCommand extends Command {

    private final int index;

    DeleteCommand(int index) {
        super(Keyword.DELETE);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index <= tasks.size()) {
            ui.showMessage("Noted. I've removed this task:\n" + tasks.get(index - 1));
            tasks.remove(index - 1);
        } else {
            ui.showMessage("Index is invalid");
        }
    }
}
