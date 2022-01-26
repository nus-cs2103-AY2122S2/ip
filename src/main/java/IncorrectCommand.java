public class IncorrectCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // TODO Auto-generated method stub
        ui.showIncorrectMessage();
    }

}
