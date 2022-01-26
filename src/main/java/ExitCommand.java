public class ExitCommand extends Command {
    private static final boolean IS_EXIT = true;

    public ExitCommand() {
        super(IS_EXIT);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.saveAllTasks(tasks);
        ui.showExecutionMessage(Messages.MESSAGE_GOOD_BYE);
    }
}
