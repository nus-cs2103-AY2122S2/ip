public class ExitCommand extends Command {
    public ExitCommand(String command, String[] tokenizedCommand) {
        super(command, tokenizedCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        this.isRun = false;
        ui.showGoodbye();
    }
}
