public class ListCommand extends Command {
    public ListCommand(String command, String[] tokenizedCommand) {
        super(command, tokenizedCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printMsg(tasks.toString());
    }
}
