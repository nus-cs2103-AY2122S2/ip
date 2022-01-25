public class InvalidCommand extends Command {
    public InvalidCommand() {
        super();
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("    Invalid Command!");
    }
}
