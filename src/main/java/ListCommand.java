public class ListCommand extends Command {
    private static final boolean IS_EXIT = false;

    public ListCommand() {
        super(IS_EXIT);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String message = "Charizard's burning wish list:\n";
            for (int i = 0; i < tasks.getSize(); i++) {
                message += String.format("%d. %s", i + 1, tasks.getTask(i).toString());
                if (i < tasks.getSize() - 1) {
                    message += "\n";
                }
            }
            ui.appendMessage(message);
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
}
