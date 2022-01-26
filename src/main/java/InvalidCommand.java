public class InvalidCommand extends Command {

    InvalidCommand(){}

    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) {
        ui.printInvalidCommand();
        return true;
    }
}
