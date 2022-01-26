public class ExitCommand extends Command {

    ExitCommand(){

    }

    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) {
        ui.printBye();
        ui.close();
        return false;
    }
}
