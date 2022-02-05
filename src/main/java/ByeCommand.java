public class ByeCommand implements Command {
    public ByeCommand() {}

    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
        return true;
    }
}
