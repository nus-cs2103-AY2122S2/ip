public class ByeCommand extends Command{


    @Override
    void runCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.outputMessage("Thank you for using Duke App. Have a nice day");
        System.exit(1);
    }


}
