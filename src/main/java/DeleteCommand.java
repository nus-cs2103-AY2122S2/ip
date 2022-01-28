public class DeleteCommand extends Command {
    protected String index;

    public DeleteCommand(String index) {
        this.index = index;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (tasks.isEmpty()) {
                ui.printWithDivider("Your list is empty!");
                return;
            }

            int deletionIndex = Parser.parseInt(index) - 1;
            if (deletionIndex < 0 || deletionIndex >= tasks.size()) {
                ui.showError("Invalid entry number!");
            } else {
                Task deletedTask = tasks.removeTask(deletionIndex);
                ui.showDeletion(tasks.size(), deletedTask);
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
