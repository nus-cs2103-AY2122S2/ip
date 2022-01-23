public class UnrecognizedCommand extends Command {

    public UnrecognizedCommand() {
        super(Keyword.UNRECOGNIZED);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Sorry, but I don't know what that means :(");
    }

}
