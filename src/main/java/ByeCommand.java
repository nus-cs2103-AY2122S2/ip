public class ByeCommand extends Command {
    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(List taskList, Ui ui, Storage storage) {
        ui.byeUser();
    }
}
