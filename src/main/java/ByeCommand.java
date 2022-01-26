public class ByeCommand extends Command {
    public ByeCommand(String[] commandArray) {
        super(commandArray);
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.showUiForBye();
    }
}
