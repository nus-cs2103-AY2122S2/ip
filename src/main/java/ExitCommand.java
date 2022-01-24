public class ExitCommand extends Command {

    public ExitCommand() {}

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printOutput("Bye! Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}
