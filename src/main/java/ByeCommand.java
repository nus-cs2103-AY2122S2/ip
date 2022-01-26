public class ByeCommand extends Command {
    private final static String BYE = "See ya!";

    public ByeCommand(String key) {
        super(key);
    }

    @Override
    public void execute(String input, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        ui.printResponse(BYE);
    }
}
