public class UnmarkCommand extends Command {
    private final int unmarkIndex;

    UnmarkCommand(int unmarkIndex) {
        super();
        this.unmarkIndex = unmarkIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks = tasks.set(unmarkIndex, tasks.getByIndex(unmarkIndex).unmark());
            storage.saveTaskList(tasks);
            ui.showMessage("Nice! I've marked this task as not done yet:\n        "
                    + tasks.getByIndex(unmarkIndex));
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            ui.showInvalidIndex();
        }
    }
}
