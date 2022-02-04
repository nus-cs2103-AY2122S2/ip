public class ErrorCommand extends Command{
    private final String err;
    ErrorCommand(String err) {
        super();
        this.err = err;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(err);
    }
}
