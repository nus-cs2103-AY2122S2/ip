public class ExitCommand extends Command {
    private static final boolean IS_EXIT = true;

    public ExitCommand() {
        super(IS_EXIT);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveFileData(tasks.getTasks());
            ui.appendMessage(Ui.BYE_MESSAGE);
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
}
